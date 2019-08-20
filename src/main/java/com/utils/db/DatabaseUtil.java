package com.utils.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <ul>
 * <li>文件包名 : com.utils.db</li>
 * <li>创建时间 : 2019/8/20 14:55 </li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：获取数据库下的所有表
 *
 * @author zhengyu
 */

public class DatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 排除指定的表
     *
     * @param tables     表
     * @param exceptions 排除的表名
     *                   可以利用 *表示等相关表
     *                   eg: exceptions ="act_*" 排除 以act_开头的表 如果没有 *则是排除具体的表
     */
    public static List<String> getExceptTable(List<String> tables, String exceptions) {

        String exc = "";
        if (exceptions.contains("*")) {
            exc = exceptions.substring(0, exceptions.indexOf("*"));
        } else {
            exc = exceptions;
        }
        String finalExc = exc;
        tables.stream().filter(x -> x.equals("databasechangelog")).collect(Collectors.toList());

        return  tables;

    }


    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }

    public static void main(String[] args) {
        List<String> tableNames = getTableNames();
        System.out.println("tableNames:" + tableNames);

        List<String> exceptTable = getExceptTable(tableNames, "data*");
        System.out.println("excNames:"+exceptTable);
       /* for (String tableName : tableNames) {
            System.out.println("table name:" + tableName);
            System.out.println("ColumnNames:" + getColumnNames(tableName));
            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
            System.out.println("ColumnComments:" + getColumnComments(tableName));
        }*/
    }
}