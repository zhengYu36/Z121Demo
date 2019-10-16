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
 * 请注意:这些项目是有一个公共模块的父类，所以有的字段需要固定，
 * （这些一般是需要记录操作人，
 * 就是通过c#来直接获取的相关操作） 这样做是为了兼容一起的，可能稍有些繁杂，
 * 但是这样也是为了更好 good luck
 * <p>
 * 不会排除 父类的一些属性
 *
 * @author zhengyu
 */
public class GeneratorProduceMapperCRUD {

    /**
     * 自定义排除属性(就是在mapper 中生成sql中不需要显示、生成的字段)
     * 排除的原因是因为在scbim的n项目中，有一个公共父类，里面有类似于创建用户名称，但是在创建sql
     * 的时候又没有，so，为了和以前的一样所有这样自定义了一个
     */
    //public static String[] REMOVE = {"createUserName", "editUserName", "serialVersionUID"};
    public static String[] REMOVE = { "serialVersionUID"};

    //生成oracle的sql语句的时候，需要固定在前面的属性 （这些属性都是固定的），排除它是为了保证它的位置在最前面,
    //提高可读性
    public static String[] ORACLEREMOVE = {"ID", "NAME", "CODE", "CREATE_USER_ID", "CREATE_DATE","CREATE_USER_NAME",
            "EDIT_USER_ID","EDIT_USER_NAME", "EDIT_DATE", "DELETE_FLAG", "MEMO"};

    //schema 指定数据库
    public static String SCHEMANAME = "GZDTNEW";
    //schema 指定数据库
    public static String TABLENAME = "YJ_MANAGER_PERSON";

    //schema 指定数据库
    public static String CLASSNAME = "manager.ManagerProjectPlan";

    public static boolean ISOK = false;


    public static void main(String[] args) throws Exception {
        //创建sql和xml文件
        //提前出来为一个方法，方便批处理使用
        createSql(CLASSNAME, SCHEMANAME, TABLENAME);
    }

    /**
     * 生成时间： 2019/5/8 19:40
     * 方法说明：
     * 开发人员：zhengyu
     *
     * @param className  类名称  包名.类名 eg:manager.ManagerProjectPlan
     * @param schemaName 数据库名称
     * @param tableName  表名称
     * @return void
     */


    public static void createSql(String className, String schemaName, String tableName)
            throws Exception {

        if (StringUtils.isNotEmpty(schemaName)) {
            SCHEMANAME = schemaName;
        }
        if (StringUtils.isNotEmpty(tableName)) {
            TABLENAME = tableName;
        }

        Class cmc = Class.forName(className);

        //获取所有属性(包括父类的属性值)
        String superName = cmc.getSuperclass().getSimpleName();
        if(superName.equals("BaseEntity")){
            ISOK = true;
        }
        Field[] allFields = FieldUtils.getAllFields(cmc);
        //// 判断是否是继承的,如果是,则需要排除默写字段 start
        //ISOK = judgeExtend(allFields);

        //用List保存数据，排除掉不需要的属性值
        List<String> set = new ArrayList<>();
        for (int i = 0; i < allFields.length; i++) {
            Field allField = allFields[i];

            /**
             * 这里排除的意思是说,在创建实体的时候在 createUserName
             * 但是在数据库中又不存在,我也不知道他们以前为什么要这样来
             * 设计,但是我最好和他们保持统一,但是这里写起还是有点坑,
             * 有点傻叉
             */
            if (ISOK) {
                boolean isflag = false;
                for (int j = 0; j < REMOVE.length; j++) {
                    if (REMOVE[j].equals(allField.getName())) {
                        isflag = true;
                        break;
                    }
                }
                if (isflag) continue;
            }

            //这里可以指定需要排除的字段，例如序列号id字段等
            String str = OneStringUtils.parsePageClass(allField.getName(), allField.getType().toString());
            set.add(str);
        }
        String str = GeneratorProduceMapperCRUD.createMapperXML(TABLENAME, set);
        //System.out.println(str);
    }


    //创建 xml文件
    public static String createMapperXML(String tableName, List<String> fields) throws Exception {
        StringBuffer str = new StringBuffer();
        //创建crud
        //insert
        String common = selectCommon(tableName, fields);
        str.append("\n--------------common start------------------\n");
        str.append(common);
        str.append("\n--------------common   end------------------\n");

        //insert
        String insert = insertMethod(tableName, fields);
        str.append("\n--------------insert start------------------\n");
        str.append(insert);
        str.append("\n--------------insert   end------------------\n");

        //创建update
        String update = updateMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------update  start------------------\n");
        str.append(update);
        str.append("\n--------------update   end------------------\n");

        //创建select
        String select = selectMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select  start------------------\n");
        str.append(select);
        str.append("\n--------------select   end------------------\n");

        //创建select list
        String selectList = selectListMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select list start------------------\n");
        str.append(selectList);
        str.append("\n--------------select list  end------------------\n");

        //创建select byId
        String selectById = selectByIdMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------select byId start------------------\n");
        str.append(selectById);
        str.append("\n--------------select byId end------------------\n");

        //创建delete
        String delete = deleteMethod(tableName, fields);
        str.append("\n");
        str.append("\n--------------delete  start------------------\n");
        str.append(delete);
        str.append("\n--------------delete   end------------------\n");

        //创建sql语句 (需要使用自己的)
        String sql = "";
        if (ISOK) {
            sql = produceSqlMethod(SCHEMANAME, tableName, fields);
        } else {
            sql = produceSqlMethodByOrig(SCHEMANAME, tableName, fields);
        }

        System.err.println("-- " + tableName + " sql为:");
        System.err.println(sql);
        str.append("\n");
        str.append("\n--------------createsql  start------------------\n");
        str.append(sql);
        str.append("\n--------------createsql   end------------------\n");


        //创建xml尾部
        //str.append("</mapper>");
        //创建xml文件
        CreateControllerOther.mapperXMLTemple(common, insert, update, selectList, selectById, delete);
        //创建xml文件

        return str.toString();
    }

    public static String produceSqlMethod(String schema, String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        //这里为什么要单独提出来这里是为了在创建sql的时候，方便查询，因为目前该表这些
        //字段都是公共的，并且都是位置一致，为了方便，所以需要固定住
        //其他的字段的长度需要自己去手动定义数据，因为长度这些是需要自定义的，不能固定死
        str.append(" CREATE TABLE \"" + schema + "\".\"" + tableName + "\"  ( \n");
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
        for (int i = 0; i < fields.size(); i++) {
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
            str.append(" \"" + sb.toUpperCase() + "\" " +
                    " " + OneStringUtils.produceTypeToOracleType(split[1]) + ", \n");
        }

        //去掉最后一个 ，
        String shows = str.toString();
        shows = shows.substring(0, shows.toString().lastIndexOf(","));
        shows += "\n );";

        return shows;
    }

    public static String produceSqlMethodByOrig(String schema, String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        str.append(" CREATE TABLE \"" + schema + "\".\"" + tableName + "\"  ( \n");
        //循环迭代数据
        for (int i = 0; i < fields.size(); i++) {
            String[] split = fields.get(i).split("-");
            String sb = OneStringUtils.camelToUnderline(split[0]);
            str.append(" \"" + sb.toUpperCase() + "\" " +
                    " " + OneStringUtils.produceTypeToOracleType(split[1]) + ", \n");
        }
        //去掉最后一个 ，
        String shows = str.toString();
        shows = shows.substring(0, shows.lastIndexOf(","));
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

    public static String selectCommon(String tableName, List<String> fields) {
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
        return showsa;
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

    public static String insertMethod(String tableName, List<String> fields) {
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

    public static String selectListMethod(String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        str.append(" select \n ");
        str.append(" <include refid=\"record_column\"/> \n ");
        str.append(" from " + tableName + "  \n ");
        str.append(" where 1=1 \n ");
        return str.toString();
    }

    public static String selectByIdMethod(String tableName, List<String> fields) {
        StringBuffer str = new StringBuffer();
        str.append(" select \n ");
        str.append(" <include refid=\"record_column\"/> \n ");
        str.append(" from " + tableName + " \n ");
        str.append(" where id=#{id}  \n ");
        return str.toString();
    }

}
