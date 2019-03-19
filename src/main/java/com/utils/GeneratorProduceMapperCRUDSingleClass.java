package com.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 13:37</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 一个类生成，不需要排除
 *
 * @author zhengyu
 */
public class GeneratorProduceMapperCRUDSingleClass {

    //schema 指定数据库
    public static String SCHEMANAME = "GZDTNEW";
    //schema 指定数据库
    public static String TABLENAME = "YJ_CLASSES_TIME_DETAIL";

    public static void main(String[] args) throws Exception {
        Class cmc = Class.forName("peccancy.ClassesTimeDetail");
        //获取所有属性(包括父类的属性值)
        Field[] allFields = FieldUtils.getAllFields(cmc);

        //用List保存数据
        List<String> set = new ArrayList<>();
        for (int i = 0; i < allFields.length; i++) {
            Field allField = allFields[i];
            String str = OneStringUtils.parsePageClass(allField.getName(), allField.getType().toString());
            set.add(str);
        }
        String str = GeneratorProduceMapperCRUDSingleClass.createMapperXML("", TABLENAME, set);
        System.out.println(str);
    }

    //创建 xml文件
    public static String createMapperXML(String path, String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();

        //创建crud
        String ss = insertMethod(path, tableName, fields);
        str.append("\n--------------insert start------------------\n");
        str.append(ss);
        str.append("\n--------------insert   end------------------\n");
        //创建select
        str.append("\n");
        str.append("\n--------------select  start------------------\n");
        str.append(selectMethod(tableName, fields));
        str.append("\n--------------select   end------------------\n");
        //创建update
        str.append("\n");
        str.append("\n--------------update  start------------------\n");
        str.append(updateMethod(tableName, fields));
        str.append("\n--------------update   end------------------\n");

        //创建delete
        str.append("\n");
        str.append("\n--------------delete  start------------------\n");
        str.append(deleteMethod(tableName, fields));
        str.append("\n--------------delete   end------------------\n");

        //创建sql语句
        str.append("\n");
        str.append("\n--------------createsql  start------------------\n");
        str.append(produceSqlMethod(SCHEMANAME, tableName, fields));
        str.append("\n--------------createsql   end------------------\n");


        //创建xml尾部
        //str.append("</mapper>");

        return str.toString();
    }

    public static String produceSqlMethod(String schema, String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        //循环迭代数据
        for (int i = 0; i < fields.size(); i++) {
            String[] split = fields.get(i).split("-");
            boolean isflag = false;
            String sb = OneStringUtils.camelToUnderline(split[0]);
            str.append(" \"" + sb.toUpperCase() + "\" " +
                    " " + OneStringUtils.produceTypeToOracleType(split[1]) + ", \n");
        }
        //去掉最后一个 ，
        String shows = str.toString();
        shows = shows.substring(0, shows.toString().lastIndexOf(","));
        shows += "\n );";

        return shows;
    }


    public static String deleteMethod(String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        str.append(" delete from " + tableName + " where id=#{id} ");
        return str.toString();
    }

    public static String updateMethod(String tableName, List<String> fields) {

        StringBuffer str = new StringBuffer();
        str.append("update " + tableName + " \n");
        str.append("<set>\n");
        int num = 0;
        boolean sym = false;
        for (int i = 0; i < fields.size(); i++) {
            String ss = fields.get(i);
            String[] split = ss.split("-");
            //转换为下划线格式字符串
            String sb = OneStringUtils.camelToUnderline(split[0].toString());
            String yxStr = split[0];
            num++;
            if (num == fields.size()) {
                sym = true;
            }
            if (sym) {
                str.append("<if test=\"" + yxStr + " != null\">" +
                        "" + sb + "=#{" + yxStr + "}</if>  \n");
            } else {
                str.append("<if test=\"" + yxStr + " != null\">" +
                        "" + sb + "=#{" + yxStr + "},</if>  \n");
            }

        }
        str.append("</set>\n");
        str.append(" where id=#{id} ");

        return str.toString();
    }

    public static String selectMethod(String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();

        //显示字段
        StringBuffer shows = new StringBuffer();
        Iterator it = fields.iterator();
        //这里会把实体的名称转换为驼峰的属性
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");
            String sb = OneStringUtils.camelToUnderline(split[0].toString());
            if (sb.contains("_")) {
                sb = sb + " as " + split[0];
            }
            sb = sb + ", \n";
            shows.append(sb);
        }
        //去掉最后一个 ，
        String showsa = shows.toString();
        showsa = showsa.substring(0, showsa.toString().lastIndexOf(","));

        str.append("select   \n" + showsa + " \nfrom  " + tableName + " ");
        return str.toString();
    }

    private static String insertMethod(String path, String tableName, List<String> fields) {
        //创建insert
        StringBuffer str = new StringBuffer();
        str.append("insert into " + tableName + "  \n");
        Iterator it = fields.iterator();
        str.append("( ");
        //这里会把实体的名称转换为驼峰的属性
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");
            str.append(OneStringUtils.camelToUnderline(split[0].toString()) + ",\n");
        }
        //去掉最后一个 " ,"
        String values = str.toString();
        values = values.substring(0, values.toString().lastIndexOf(","));
        str = new StringBuffer("");
        str.append(values);
        str.append(" ) \n");
        //插入值
        Iterator itv = fields.iterator();
        str.append("values (  \n");

        while (itv.hasNext()) {
            String ss = itv.next().toString();
            String[] split = ss.split("-");
            String sa = OneStringUtils.parseTypeToOracleType(split[1]);
            str.append("#{" + split[0] + ",jdbcType=" + OneStringUtils.parseTypeToOracleType(split[1]) + "},\n");

        }
        //去掉最后一个 " ,
        String vas = str.toString();
        vas = vas.substring(0, vas.toString().lastIndexOf(","));
        str = new StringBuffer("");
        str.append(vas);
        str.append(" ) \n");

        return str.toString();
    }
}
