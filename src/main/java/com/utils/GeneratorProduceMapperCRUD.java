package com.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 13:37</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 根据实体生产相关对应的mapper.xml文件中的sql(crud文件)
 * 请注意:这些项目是有一个公共模块的父类，所以有的字段需要固定，（这些一般是需要记录操作人，
 * 就是通过c#来直接获取的相关操作）
 *
 * 不会排除 父类的一些属性
 *
 * @author zhengyu
 */
public class GeneratorProduceMapperCRUD {

    //自定义排除属性(就是在mapper 中生成sql中不需要显示的字段)
    public static String[] REMOVE = {"createUserName", "editUserName", "serialVersionUID"};

    //生成oracle的sql语句的时候，需要排除的属性 （这些属性都是固定的），排除它是为了保证它的位置在最前面
    public static String[] ORACLEREMOVE = {"ID", "NAME", "CODE","CREATE_USER_ID", "CREATE_DATE",
            "EDIT_USER_ID","EDIT_DATE", "DELETE_FLAG", "MEMO"};

    //schema 指定数据库
    public static String SCHEMANAME = "GZDTNEW";
    //schema 指定数据库
    public static String TABLENAME = "YJ_MANAGER_PERSON";

    //schema 指定数据库
    public static String CLASSNAME = "manager.ManagerPerson";

    public static void main(String[] args) throws Exception {
        //创建sql和xml文件
        //提前出来为一个方法，方便批处理使用
        createSql(CLASSNAME,SCHEMANAME,TABLENAME);
    }

    /**
     * 生成时间： 2019/5/8 19:40
     * 方法说明：
     * 开发人员：zhengyu
     * @Param: className 类名称  包名.类名 eg:manager.ManagerPerson
     * @Param: schemaName 数据库名称
     * @Param: tableName 表名称
     * @return void
     */

    public static void createSql(String className,String schemaName,String tableName)
            throws Exception {

        if(StringUtils.isNotEmpty(schemaName)){
            SCHEMANAME = schemaName;
        }
        if(StringUtils.isNotEmpty(tableName)){
            TABLENAME = tableName;
        }
        Class cmc = Class.forName(className);
        //获取所有属性(包括父类的属性值)
        Field[] allFields = FieldUtils.getAllFields(cmc);

        //用List保存数据，排除掉不需要的属性值
        List<String> set = new ArrayList<>();
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
        String str = GeneratorProduceMapperCRUD.createMapperXML(TABLENAME, set);
        System.out.println(str);
    }

    //创建 xml文件
    public static String createMapperXML(String tableName, List<String> fields) throws Exception{
        StringBuffer str = new StringBuffer();
        //创建crud
        //insert
        String common = GeneratorProduceMapperCRUDSingleClass.selectCommon(tableName, fields);
        str.append("\n--------------common start------------------\n");
        str.append(common);
        str.append("\n--------------common   end------------------\n");

        //insert
        String insert = GeneratorProduceMapperCRUDSingleClass.insertMethod(tableName, fields);
        str.append("\n--------------insert start------------------\n");
        str.append(insert);
        str.append("\n--------------insert   end------------------\n");

        //创建update
        String update = GeneratorProduceMapperCRUDSingleClass.updateMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------update  start------------------\n");
        str.append(update);
        str.append("\n--------------update   end------------------\n");

        //创建select
        String select = GeneratorProduceMapperCRUDSingleClass.selectMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select  start------------------\n");
        str.append(select);
        str.append("\n--------------select   end------------------\n");

        //创建select list
        String selectList = GeneratorProduceMapperCRUDSingleClass.selectListMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select list start------------------\n");
        str.append(selectList);
        str.append("\n--------------select list  end------------------\n");

        //创建select byId
        String selectById = GeneratorProduceMapperCRUDSingleClass.selectByIdMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select byId start------------------\n");
        str.append(selectById);
        str.append("\n--------------select byId end------------------\n");

        //创建delete
        String delete = GeneratorProduceMapperCRUDSingleClass.deleteMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------delete  start------------------\n");
        str.append(delete);
        str.append("\n--------------delete   end------------------\n");

        //创建sql语句 (需要使用自己的)
        String sql = produceSqlMethod(SCHEMANAME, tableName, fields);
        System.err.println("-- "+tableName+" sql为:");
        System.err.println(sql);
        str.append("\n");
        str.append("\n--------------createsql  start------------------\n");
        str.append(sql);
        str.append("\n--------------createsql   end------------------\n");


        //创建xml尾部
        //str.append("</mapper>");
        //创建xml文件
        CreateControllerOther.mapperXMLTemple(common,insert,update,selectList,selectById,delete);
        //创建xml文件

        return str.toString();
    }

    public static String produceSqlMethod(String schema,String tableName,List<String> fields){
        StringBuffer str = new StringBuffer();
        //这里为什么要单独提出来这里是为了在创建sql的时候，方便查询，因为目前该表这些
        //字段都是公共的，并且都是位置一致，为了方便，所以需要固定住
        //其他的字段的长度需要自己去手动定义数据，因为长度这些是需要自定义的，不能固定死
        str.append(" CREATE TABLE \""+schema+"\".\""+tableName+"\"  ( \n");
        str.append(" \"ID\" VARCHAR2(20) NOT NULL ENABLE,    \n");
        str.append(" \"NAME\" VARCHAR2(4000),    \n");
        str.append(" \"CODE\" VARCHAR2(200),   \n");
        str.append(" \"CREATE_USER_ID\" VARCHAR2(20),    \n");
        str.append(" \"CREATE_DATE\" DATE,    \n");
        str.append(" \"EDIT_USER_ID\" VARCHAR2(20),    \n");
        str.append(" \"EDIT_DATE\" DATE,    \n");
        str.append(" \"DELETE_FLAG\" NUMBER(1,0),     \n");
        str.append(" \"MEMO\" VARCHAR2(4000),    \n");

        //循环迭代数据
       for(int i=0;i<fields.size();i++){
            String[] split = fields.get(i).split("-");
            boolean isflag = false;
            for (int j = 0; j < ORACLEREMOVE.length; j++) {
                if (ORACLEREMOVE[j].equals(OneStringUtils.camelToUnderline(split[0]).toUpperCase())) {
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
        shows += "\n );";

        return shows;
    }

}
