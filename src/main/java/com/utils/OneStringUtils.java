package com.utils;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 14:32</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 字符串处理工具类
 *
 * @author zhengyu
 */
public class OneStringUtils {

    public static final char UNDERLINE = '_';

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 处理包装类，把包装类转换为自己需要的格式
     * 属性名-属性类型
     * eg:
     * name-string
     */
    public static String parsePageClass(String fieldName, String typeName) {
        StringBuilder str = new StringBuilder();
        str.append(fieldName);
        str.append("-");
        String ss = typeName;
        //处理包装类
        if ("class java.lang.String".equals(typeName)) {
            ss = "string";
        }
        if ("class java.lang.Integer".equals(typeName)) {
            ss = "int";
        }
        if ("class java.lang.Double".equals(typeName)) {
            ss = "double";
        }
        if ("class java.lang.Boolean".equals(typeName)) {
            ss = "boolean";
        }
        if ("class java.util.Date".equals(typeName)) {
            ss = "date";
        }
        if ("class java.lang.Float".equals(typeName)) {
            ss = "float";
        }
        str.append(ss);

        return str.toString();
    }

    /**
     *  类型和oracle的数据关系对象
     */
    public static String parseTypeToOracleType(String jdbcType) {
       String oracleType ="";
       //string的其实是可以不用生成的，这个会默认生成的
       if("string".equals(jdbcType)){
           oracleType = "VARCHAR";
       }
       if("int".equals(jdbcType) || "double".equals(jdbcType)
               || "float".equals(jdbcType)|| "boolean".equals(jdbcType)){
            oracleType = "NUMERIC";
       }
       if("date".equals(jdbcType)){
           oracleType = "TIMESTAMP";
       }

       return oracleType;
    }

    /**
     *  生成数据类型
     *  目前该方法只适用于oracle
     */
    //有的长度都需要自定义，所以我用?代替，string 可以默认使用255
    public static String produceTypeToOracleType(String jdbcType) {
        String oracleType ="";
        if("string".equals(jdbcType)){
            oracleType = "VARCHAR2(255)";
        }
        if("int".equals(jdbcType) || "double".equals(jdbcType) || "float".equals(jdbcType)){
            //整数部位有几位，小数部位有几位
            //如果默认没有指定，那么就会获取最大值，
            // 所有目前我们可以暂时设定一些数据的方式
            oracleType = "NUMBER(9,3)";
        }
        if("date".equals(jdbcType)){
            oracleType = "DATE";
        }

        if("boolean".equals(jdbcType)){
            oracleType = "NUMBER(1,0)";
        }

        return oracleType;
    }

    public static void main(String[] args) {
        String str = "isHavePhotoHH";
        //拆分为 is_Have_Photo的方式
        String stx = OneStringUtils.camelToUnderline(str);
        System.out.println(stx);
    }
}
