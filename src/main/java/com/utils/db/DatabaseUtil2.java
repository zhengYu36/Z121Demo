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
 * 参考url:
 *
 * @author zhengyu
 */

public class DatabaseUtil2 {
/*    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/zy?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static String INSERT = "INSERT INTO";//插入sql
    private static String VALUES = "VALUES";//values关键字*/

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil2.class);
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://192.168.1.10:3306/digitalhandover?useUnicode=true&characterEncoding=utf8";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private static String INSERT = "INSERT INTO";//插入sql
    private static String VALUES = "VALUES";//values关键字

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
        //利用!x.contains(finalExc)获取不包含的字符串
        List<String> list = tables.stream().filter(x -> !x.contains(finalExc)).collect(Collectors.toList());

        return list;

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

    /**
     * 生成时间： 2019/8/21 9:36
     * 方法说明：根据表名来获取创建该表的创建sql
     * 开发人员：zhengyu
     *
     * @param tableName 表名称
     * @return 创建表sql
     */
    public static String getCreateTableSql(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = "SHOW CREATE TABLE " + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            ResultSet resultSet = pStemt.executeQuery();
            while (resultSet.next()) {
                //创建创建表的语句
                //System.out.println(resultSet.getString(2));
                return resultSet.getString("Create Table");
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
        return "";
    }

    /**
     * 生成时间： 2019/8/21 9:36
     * 方法说明：根据表名获取该表insert的字符串
     * 开发人员：zhengyu
     *
     * @param tableName 表名称
     * @return insert表的字符串
     */
    public static String getInsertTableData(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            //获取executeQuery()
            pStemt = conn.prepareStatement(tableSql);
            ResultSet rs = pStemt.executeQuery();

            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            StringBuilder insertSql = new StringBuilder();

            while (rs.next()) {
                StringBuffer ColumnName = new StringBuffer();
                StringBuffer ColumnValue = new StringBuffer();
                //拼接columnName 和 columnValue
                getColumnNameAndValue(rs, rsmd, columnCount, ColumnName, ColumnValue);
                insertSql.append(insertSQL(ColumnName, ColumnValue, tableName) + "\n");
            }

            return insertSql.toString();
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
        return "";
    }

    //得到类型和类型对应的值
    private static void getColumnNameAndValue(ResultSet rs, ResultSetMetaData rsmd, int columnCount, StringBuffer columnName, StringBuffer columnValue) throws SQLException {
        for (int i = 1; i <= columnCount; i++) {
            String value = rs.getString(i);

            if ("".equals(value)) {
                value = "";
            }
            if (i == 1 || i == columnCount) {
                if (i == columnCount) {
                    columnName.append(",");
                }
                columnName.append(rsmd.getColumnName(i));
                if (i == 1) {
                    if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {

                        if (value == null) {
                            columnValue.append(value).append(",");
                        } else {
                            columnValue.append("'").append(value).append("',");
                        }

                    } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                        columnValue.append(value).append(",");
                    } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                        if (value == null) {
                            columnValue.append(value).append(",");
                        } else {
                            columnValue.append("'").append(value).append("',");
                        }

                    } else {
                        columnValue.append(value).append(",");

                    }
                } else {
                    if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {

                        if (value == null) {
                            columnValue.append(value);
                        } else {
                            columnValue.append("'").append(value).append("'");
                        }

                    } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                        columnValue.append(value);
                    } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                        if (value == null) {
                            columnValue.append(value).append(",");
                        } else {
                            columnValue.append("'").append(value).append("',");
                        }
                    } else {
                        columnValue.append(value);
                    }
                }

            } else {
                columnName.append("," + rsmd.getColumnName(i));
                if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
                    if (value == null) {
                        columnValue.append(value).append(",");
                    } else {
                        columnValue.append("'").append(value).append("',");
                    }

                } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                    columnValue.append(value).append(",");
                } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                    if (value == null) {
                        columnValue.append(value).append(",");
                    } else {
                        columnValue.append("'").append(value).append("',");
                    }
                } else {
                    columnValue.append(value).append(",");
                }
            }
        }
    }

    //拼接sql
    private static String insertSQL(StringBuffer ColumnName, StringBuffer ColumnValue, String tableName) {
        StringBuffer insertSQL = new StringBuffer();
        insertSQL.append(INSERT).append(" ")
                .append(tableName).append("(").append(ColumnName.toString()).append(")").append(VALUES).append("(").append(ColumnValue.toString()).append(");");
        //insertList.add(insertSQL.toString());
        //System.out.println(insertSQL.toString());
        return insertSQL.toString();
    }

    public static void main(String[] args) {
        //查询数据表
        List<String> tableNames = getTableNames();
        //System.out.println("tableNames:" + tableNames);

        //排除指定的表
        List<String> exceptTable = getExceptTable(tableNames, "act_*");
        for (String tableName : exceptTable) {
            System.out.println("tableName is:" + tableName);
            List<String> columnNames = getColumnNames(tableName);

            for (int i = 0; i < columnNames.size(); i++) {
                if (columnNames.get(i).toUpperCase().equals("PROJECT_ID")) {
                    System.out.println("该表存在PROJECT_ID字段 "+columnNames);
                }
            }
            System.out.println();
        }

        System.out.println("\n");

    }
}