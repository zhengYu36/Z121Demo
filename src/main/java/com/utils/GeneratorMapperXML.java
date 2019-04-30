package com.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 13:37</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 在公司由于不存在这么一个工具模块，so，我自己来弄一个
 * 方便以后开发，而且很多字段其实我都是可以自定义生产或者说是不生产的
 * 根据实体生产相关对应的xml文件中的sql(crud文件)
 * 不是特别的完善
 *
 * @author zhengyu
 */
public class GeneratorMapperXML {

    //自定义排除属性(就是在mapper 中生成sql中不需要显示的字段)
    //这里是为了避免改动到以前的父类实体，所以我说比较麻烦，嗯，就是这个道理，不是太好
    public static String[] REMOVE = {"createUserName", "editUserName", "serialVersionUID"};

    //生成oracle的sql语句的时候，需要排除的属性
    public static String[] ORACLEREMOVE = {"ID", "NAME", "CODE","CREATEUSER_ID", "CREATE_DATE",
            "EDITUSER_ID","EDIT_DATE", "DELETE_FLAG", "MEMO"};

    //schema 指定数据库
    public static String SCHEMANAME = "GZDTNEW";

    public static void main(String[] args) throws Exception {
        Class cmc = Class.forName("peccancy.CheckStandard");
        //获取所有属性(包括父类的属性值)
        Field[] allFields = FieldUtils.getAllFields(cmc);
        //防止属性名称有重复的，利用set过滤一次
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < allFields.length; i++) {
            Field allField = allFields[i];
            boolean isflag = false;
            for (int j = 0; j < REMOVE.length; j++) {
                if (REMOVE[j].equals(allField.getName())) {
                    isflag = true;
                    break;
                }
            }
            if (isflag) continue;

            //这里可以指定需要排除的字段，例如序列号id字段等
            String str = OneStringUtils.parsePageClass(allField.getName(), allField.getType().toString());
            set.add(str);
        }
       /* Iterator it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }*/
        //包名字段暂时不用
        String str = GeneratorMapperXML.createMapperXML("", "yj_aaaaa", set);
        System.out.println(str);

    }

    //创建 xml文件
    public static String createMapperXML(String path, String tableName, Set<String> fields) {
        StringBuffer str = new StringBuffer();
        //创建xml头  (我这边只是用来生成insert 或是其他语句好了)
/*        str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        str.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis" +
                ".org/dtd/mybatis-3-mapper.dtd\">");
        //自己指定对应的 dao
        str.append("<mapper namespace=\"自定义dao的路径\">");*/

        //创建crud
        String ss = insertMethod(path, tableName, fields);
        str.append("\n--------------insert start------------------\n");
        str.append(ss);
        str.append("\n--------------insert   end------------------\n");
        //创建select
        str.append("\n");
        str.append("\n--------------select  start------------------\n");
        str.append(selectMethod(tableName,fields));
        str.append("\n--------------select   end------------------\n");
        //创建update
        str.append("\n");
        str.append("\n--------------update  start------------------\n");
        str.append(updateMethod(tableName,fields));
        str.append("\n--------------update   end------------------\n");

        //创建delete
        str.append("\n");
        str.append("\n--------------delete  start------------------\n");
        str.append(deleteMethod(tableName,fields));
        str.append("\n--------------delete   end------------------\n");

        //创建sql语句
        str.append("\n");
        str.append("\n--------------createsql  start------------------\n");
        str.append(produceSqlMethod(SCHEMANAME,tableName,fields));
        str.append("\n--------------createsql   end------------------\n");


        //创建xml尾部
        //str.append("</mapper>");

        return str.toString();
    }
    public static String produceSqlMethod(String schema,String tableName,Set<String> fields){
        StringBuffer str = new StringBuffer();
        //这里为什么要单独提出来这里是为了在创建sql的时候，方便查询，因为目前该表这些
        //字段都是公共的，并且都是位置一致，为了方便，所以需要固定住
        //其他的字段的长度需要自己去手动定义数据，因为长度这些是需要自定义的，不能固定死
        str.append(" CREATE TABLE \""+schema+"\".\""+tableName+"\"   \n");
        str.append(" \"ID\" VARCHAR2(20) NOT NULL ENABLE,    \n");
        str.append(" \"NAME\" VARCHAR2(4000),    \n");
        str.append(" \"CODE\" VARCHAR2(200),   \n");
        str.append(" \"CREATEUSER_ID\" VARCHAR2(20),    \n");
        str.append(" \"CREATE_DATE\" DATE,    \n");
        str.append(" \"EDITUSER_ID\" VARCHAR2(20),    \n");
        str.append(" \"EDIT_DATE\" DATE,    \n");
        str.append(" \"DELETE_FLAG\" NUMBER(1,0),     \n");
        str.append(" \"MEMO\" VARCHAR2(4000),    \n");

        //循环迭代数据
        Iterator it = fields.iterator();
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");

            boolean isflag = false;
            for (int j = 0; j < ORACLEREMOVE.length; j++) {
                if (ORACLEREMOVE[j].equals(split[0].toUpperCase())) {
                    isflag = true;
                    break;
                }
            }
            if (isflag) continue;

            String sb = OneStringUtils.camelToUnderline(split[0]);
            str.append(" \""+sb.toUpperCase()+"\" " +
                    " "+OneStringUtils.produceTypeToOracleType(split[1])+", \n");
        }
        //去掉最后一个 ，
        String shows = str.toString();
        shows = shows.substring(0, shows.toString().lastIndexOf(","));
        shows += ");";

        return shows;
    }


    public static String deleteMethod(String tableName,Set<String> fields){
        StringBuffer str = new StringBuffer();
        str.append(" delete from "+tableName+" where id=#{id} ");
        return str.toString();
    }

    public static String updateMethod(String tableName,Set<String> fields){

        StringBuffer str = new StringBuffer();
        str.append("update "+tableName+" set  \n");
        Iterator it = fields.iterator();
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");
            //转换为下划线格式字符串
            String sb = OneStringUtils.camelToUnderline(split[0].toString());
            String yxStr = split[0];
            str.append("<if test=\""+yxStr+" != null and "+yxStr+" != ''\">"+sb+"=#{"+yxStr+"},</if>  \n");
        }
        str.append(" editUser_Id = #{editUserId},  \n");
        str.append(" edit_date = #{editDate}  \n");
        str.append(" where id=#{id} ");

        return str.toString();
    }

    public static String selectMethod(String tableName,Set<String> fields){
        StringBuffer str = new StringBuffer();

        //显示字段
        StringBuffer shows = new StringBuffer();
        Iterator it = fields.iterator();
        //这里会把实体的名称转换为驼峰的属性
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");
            String sb = OneStringUtils.camelToUnderline(split[0].toString());
            if(sb.contains("_")){
                sb = "t."+sb + " as " + split[0];
            }
            sb = sb + ",";
            shows.append(sb);
        }
        //去掉最后一个 ，
        String showsa = shows.toString();
        showsa = showsa.substring(0, showsa.toString().lastIndexOf(","));

        str.append(" select  "+showsa+" from  "+tableName+" t ");
        return str.toString();
    }

    private static String insertMethod(String path, String tableName, Set<String> fields) {
        //创建insert
        //str.append("<insert id=\"save\" " + "parameterType=\""+path+"\">");
        StringBuffer str = new StringBuffer();
        str.append("insert into " + tableName + " ");
        Iterator it = fields.iterator();
        str.append(" ( ");
        //这里会把实体的名称转换为驼峰的属性
        while (it.hasNext()) {
            String ss = it.next().toString();
            String[] split = ss.split("-");
            str.append(OneStringUtils.camelToUnderline(split[0].toString()) + ",");
        }
        //去掉最后一个 " ,"
        String values = str.toString();
        values = values.substring(0, values.toString().lastIndexOf(","));
        str = new StringBuffer("");
        str.append(values);
        str.append(" ) ");


        //插入值
        Iterator itv = fields.iterator();
        str.append(" values ( ");

        while (itv.hasNext()) {
            String ss = itv.next().toString();
            String[] split = ss.split("-");
            str.append("#{" + split[0] + ",jdbcType=" + OneStringUtils.parseTypeToOracleType(split[1]) + "},");
        }
        //去掉最后一个 " ,"
        String vas = str.toString();
        vas = vas.substring(0, vas.toString().lastIndexOf(","));
        str = new StringBuffer("");
        str.append(vas);
        str.append(" ) ");

        return str.toString();
    }
}
