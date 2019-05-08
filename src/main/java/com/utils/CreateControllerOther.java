package com.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/4/30 10:24</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：创建Controller类和其他需求类
 *
 * @author zhengyu
 */
public class CreateControllerOther {

    public static String PATH = "D:/javaTemplate/";
    public static String name = "ManagerPerson";

    public static void main(String[] args) throws Exception {
        CreateFile(PATH,name,true);
    }

    /**
     * 生成时间： 2019/5/8 19:48
     * 方法说明：
     * 开发人员：zhengyu
     * @Param: path 存放的路径，需要保存该文件目录存在
     * @Param: className 类名称，只需要类名称
     * @Param: isok 该类的service，是否需要创建和编辑等额外属性
                 true 是需要， false 不需要 ，默认是需要
     * @return void
     */

    public static void CreateFile(String path,String className,boolean isok) throws Exception {
        if(StringUtils.isNotEmpty(path)){
            PATH = path;
        }
        if(StringUtils.isNotEmpty(className)){
            name = className;
        }
        //创建模板
        System.out.println("创建模板开始......");
        controllerTemple();
        serviceTemple(isok);
        mapperTemple();
        //mapperXMLTemple();
        System.out.println("模板创建完成......");
    }

    /**
     * 生成时间： 2019/4/30 10:31
     * 方法说明：生产controller模板
     * 开发人员：zhengyu
     */
    public static String controllerTemple() throws Exception {

        String str1 = name + "Controller";
        String str2 = name + "Service";

        StringBuilder sb = new StringBuilder();
        sb.append(" import com.yanjoy.framework.util.sys.State;\n\t");
        sb.append(" import com.yanjoy.framework.base.BaseController;\n\t");
        sb.append(" \n\n");
        sb.append(" ﻿@Controller\n\t");
        sb.append(" @RequestMapping(\"/{projectId}/server/" + name.toLowerCase() + "\")\n");
        sb.append(" public class " + str1 + "  extends BaseController{\n\t");
        sb.append(" \n\t");
        sb.append(" 	@Autowired\n\t");
        sb.append(" 	private " + str2 + " service;\n\t");
        sb.append(" 	\n\t");

        //保存和更新
        sb.append(" 	@RequestMapping(value=\"/saveOrUpdateEntry\")\n\t");
        sb.append(" 	public ModelAndView saveOrUpdateEntry(String recordInfo) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         " + name + " record = service.saveOrUpdateEntry(recordInfo);\n\t");
        sb.append("         result.addObject(\"data\", record);\n\t");
        sb.append("         result.addObject(\"state\", state);\n\t");
        sb.append(" 		return result;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //查询全部返回一个list
        sb.append(" 	@RequestMapping(value=\"/queryList\")\n\t");
        sb.append(" 	public ModelAndView queryList(" + name + " record) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         List<" + name + "> recordList = service.queryList(record);\n\t");
        sb.append("         result.addObject(\"data\", recordList);\n\t");
        sb.append("         result.addObject(\"state\", state);\n\t");
        sb.append(" 		return result;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");


        //根据id查询数据(一条数据)
        sb.append(" 	@RequestMapping(value=\"/selectRecordById\")\n\t");
        sb.append(" 	public ModelAndView selectRecordById(String id) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         " + name + " record = service.queryRecordById(id);\n\t");
        sb.append("         result.addObject(\"data\", record);\n\t");
        sb.append("         result.addObject(\"state\", state);\n\t");
        sb.append(" 		return result;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //根据id删除数据
        sb.append(" 	@RequestMapping(value=\"/deleteRecord\")\n\t");
        sb.append(" 	public ModelAndView deleteRecord(String id) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         service.deleteRecord(id);\n\t");
        sb.append("         result.addObject(\"state\", state);\n\t");
        sb.append(" 		return result;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" }\n\t");
        sb.append(" 	\n\t");

        BufferedWriter bw = null;
        File con = new File(PATH + "" + str1 + ".java");
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        return sb.toString();

    }

    /**
     * 生成时间： 2019/4/30 10:31
     * 方法说明：生产 service
     * 开发人员：zhengyu
     * @param isok true表示又创建和编辑时间，需要创建，false表示不用创建
     */
    public static String serviceTemple(boolean isok) throws Exception {

        StringBuilder sb = new StringBuilder();

        String str2 = name + "Service";
        String str3 = name + "Mapper";

        sb = new StringBuilder();
        sb.append(" import org.json.JSONObject;\n\t");
        sb.append(" import java.text.SimpleDateFormat;\n\t");
        sb.append(" import java.util.List;\n\t");
        sb.append(" import org.apache.commons.lang.StringUtils;\n\t");
        sb.append(" \n\n");
        sb.append(" ﻿@Service\n");
        sb.append(" public class " + str2 + "  extends BaseService{\n\t");
        sb.append(" 	\n\t");
        sb.append(" 	@Autowired\n\t");
        sb.append(" 	private " + str3 + " mapper;\n\t");
        sb.append(" \n\t");

        //保存和修改
        sb.append(" 	public " + name + " saveOrUpdateEntry(String recordStr) throws Exception {\n\t");
        sb.append("         objectMapper.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\"));\n\t");
        sb.append("         " + name + " record = objectMapper.readValue(new JSONObject(recordStr).toString(), " +
                "" + name + ".class);\n\t");
        sb.append("         if (StringUtils.isNotBlank(record.getId())) {\n\t");
        if(isok){
            sb.append("             record.setCreateUserId(getUserId());\n\t");
            sb.append("             record.setCreateDate(new Date());\n\t");
        }
        sb.append("             mapper.updateRecord(record);\n\t");
        sb.append("         } else {\n\t");
        if(isok){
            sb.append("             record.setEditUserId(getUserId());\n\t");
            sb.append("             record.setEditDate(new Date());\n\t");
        }
        sb.append("             mapper.saveRecord(record);\n\t");
        sb.append("         }\n\t");
        sb.append(" 		return record;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //查询全部
        sb.append(" 	public List<" + name + "> queryList(" + name + " record) {\n\t");
        sb.append(" 		return mapper.queryList(record);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //根据查询某个数据
        sb.append(" 	public " + name + " queryRecordById(String id) {\n\t");
        sb.append(" 		return mapper.queryRecordById(id);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //删除
        sb.append(" 	public void deleteRecord(String id) {\n\t");
        sb.append(" 		mapper.deleteRecord(id);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" }\n\t");

        BufferedWriter bw = null;
        File con = new File(PATH + "" + str2 + ".java");
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        return sb.toString();
    }

    /**
     * 生成时间： 2019/4/30 10:31
     * 方法说明：生产 mapper
     * 开发人员：zhengyu
     */
    public static String mapperTemple() throws Exception {

        StringBuilder sb = new StringBuilder();

        //String name = name + "Mapper";
        String str3 = name + "Mapper";
        sb = new StringBuilder();
        sb.append(" public interface " + name + "Mapper {\n\t");
        sb.append(" 	\n\t");

        //保存
        sb.append(" 	void saveRecord(" + name + " record);\n\t");
        sb.append(" \n\t");
        //修改
        sb.append(" 	void updateRecord(" + name + " record);\n\t");
        sb.append(" \n\t");

        //查询全部
        sb.append(" 	List<" + name + "> queryList(" + name + " record);\n\t");
        sb.append(" \n\t");

        //根据查询某个数据
        sb.append(" 	" + name + " queryRecordById(String record);\n\t");
        sb.append(" \n\t");

        //删除
        sb.append(" 	void deleteRecord(String id);\n\t");
        sb.append(" \n\t");
        sb.append(" }\n\t");


        BufferedWriter bw = null;
        File con = new File(PATH + "" + str3 + ".java");
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        return sb.toString();
    }

    /**
     * 生成时间： 2019/5/7 16:46
     * 方法说明：
     * 开发人员：zhengyu
     * @Param: commonField
     * @Param: insert
     * @Param: update
     * @Param: selectList
     * @Param: selectById
     * @Param: delete
     * @return java.lang.String
     */
    public static String mapperXMLTemple(String commonField, String insert,
                                         String update, String selectList,
                                         String selectById, String delete) throws Exception {

        StringBuilder sb = new StringBuilder();
        String str4 = name + "Mapper";

        sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis" +
                ".org/dtd/mybatis-3-mapper.dtd\">\n\t");
        sb.append(" \n\n");
        sb.append("<mapper namespace=\"" + name + "Mapper\">");
        sb.append(" \n\t");
        sb.append(" \n\t");

        //查询公共模块
        sb.append(" 	<sql id=\"record_column\">\n\t");
        sb.append(" 	    "+commonField+"\n\t");
        sb.append(" 	</sql>\n\t");
        sb.append(" \n\t");

        //新增
        sb.append(" 	<insert id=\"saveRecord\" parameterType=\"" + name + "\">\n\t");
        sb.append(" 	    "+insert+"\n\t");
        sb.append(" 	</insert>\n\t");
        sb.append(" \n\t");

        //更新
        sb.append(" 	<update id=\"updateRecord\" parameterType=\"" + name + "\">\n\t");
        sb.append(" 	    "+update+"\n\t");
        sb.append(" 	</update>\n\t");
        sb.append(" \n\t");

        //查询全部
        sb.append(" 	<select id=\"queryList\" resultType=\"" + name + "\">\n\t");
        sb.append(" 	    "+selectList+"\n\t");
        sb.append(" 	</select>\n\t");
        sb.append(" \n\t");

        //根据id查询具体数据
        sb.append(" 	<select id=\"queryRecordById\" resultType=\"" + name + "\">\n\t");
        sb.append(" 	    "+selectById+"\n\t");
        sb.append(" 	</select>\n\t");
        sb.append(" \n\t");

        //删除
        sb.append(" 	<delete id=\"deleteRecord\">\n\t");
        sb.append(" 	    "+delete+"\n\t");
        sb.append(" 	</delete>\n\t");
        sb.append(" \n");
        sb.append("</mapper>\n\t");

        BufferedWriter bw = null;
        File con = new File(PATH + "" + str4 + ".xml");
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        return sb.toString();
    }

    /**
     * 将给定的数据装换成首字母小写
     *
     * @param str
     * @return
     */
    public static String firstLetterToLowerCase(String str) {
        String firstLetter = str.substring(0, 1).toLowerCase();
        return firstLetter + str.substring(1, str.length());
    }
}
