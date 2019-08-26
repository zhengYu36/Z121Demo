package com.utils;

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
 * 类说明： mysql 数据库的备份工具类 通过jdbc获取到并创建相关sql,并
 * 保存到指定路径
 *
 * @author zhengyu
 */

public class MysqlSqlBackUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(MysqlSqlBackUtils.class);
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/zy?useUnicode=true&characterEncoding=utf8";
    private static String USERNAME = "root";
    private static String PASSWORD = "123456";
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

    //初始化字段
    public static void initDriverInfo(String url, String userName, String password) {
        URL = url;
        USERNAME = userName;
        PASSWORD = password;
    }

    /**
     * @param path            文件路径
     * @param fileName        文件名称
     * @param exceptionTables 指定排除的表
     *                        eg: 如果是data* 表示排除以data开头的表
     * @param filterColumn    过滤的字段
     * @param condition       过滤字段的条件
     */
    public static void produceSqlFile(String path, String fileName, String[] exceptionTables, String filterColumn,
                                      String condition) {

        if (path == null || fileName == null) {
            throw new IllegalArgumentException("path or fileName can't null");
        }

        //查询数据表
        List<String> tableNames = getTableNames();

        if (exceptionTables.length > 0) {
            //排除指定表
            for (int i = 0; i < exceptionTables.length; i++) {
                tableNames = getExceptTable(tableNames, exceptionTables[i]);
            }
        }

        for (String tableName : tableNames) {

            //创建表sql
            StringBuilder preSql = new StringBuilder();
            preSql.append("DROP TABLE IF EXISTS `" + tableName + "`;\n");
            preSql.append(getCreateTableSql(tableName) + ";\n");
            preSql.append("LOCK TABLES `" + tableName + "` WRITE;" + "\n");
            FileUtils.writeFile(path, fileName, preSql.toString());

            //写入insert语句
            getInsertTableData(path, fileName, tableName, filterColumn, condition);

            StringBuilder tailSql = new StringBuilder();
            tailSql.append("UNLOCK TABLES;" + "\n\n");
            FileUtils.writeFile(path, fileName, tailSql.toString());


        }
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
            LOGGER.error("getCreateTableSql failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getCreateTableSql close pstem and connection failure", e);
                }
            }
        }
        return "";
    }

    /**
     * 生成时间： 2019/8/21 9:36
     * 方法说明：根据表名获取该表insert的字符串
     * 开发人员：zhengyu
     * 由于会有这么一个问题,就是该表如果数据量很大那么就会导致String的内存一出,
     * 所有责任insert一句就写一句sql
     *
     * @param tableName    表名称
     * @param filterColumn 过滤的字段
     * @param condition    过滤字段的条件
     * @return insert表的字符串
     */
    public static void getInsertTableData(String path, String fileName, String tableName, String filterColumn,
                                          String condition) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;

        //获取所有字段
        List<String> columnNames = getColumnNames(tableName);
        //判断是否有该字段
        columnNames = columnNames.stream().filter(x -> x.equals(filterColumn)).collect(Collectors.toList());
        if (columnNames.size() > 0) {
            //如果存在,则可以拼接该条件,否则则不拼接该条件
            String whereSql = " where " + filterColumn + " = '" + condition + "'";
            tableSql = tableSql + whereSql;
        }

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

            FileUtils.writeFile(path, fileName, insertSql.toString());
        } catch (SQLException e) {
            LOGGER.error("getInsertTableData failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getInsertTableData close pstem and connection failure", e);
                }
            }
        }

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
                            columnValue.append(value);
                        } else {
                            columnValue.append("'").append(value).append("'");
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
        return insertSQL.toString();
    }


}