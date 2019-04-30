package com.utils;

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

    public static String PATH ="D:/javaTemplate/";
    public static String name ="Peccancy";
    public static void main(String[] args) throws Exception{
        //创建模板
        System.out.println("创建模板开始......");
        controllerTemple();
        serviceTemple();
        mapperTemple();
        mapperXMLTemple();
        System.out.println("模板创建完成......");
    }

    /**
     * 生成时间： 2019/4/30 10:31
     * 方法说明：生产controller模板
     * 开发人员：zhengyu
     *
     */
    public static String controllerTemple() throws Exception {

        String str1 = name + "Controller";
        String str2 = name + "Service";

        StringBuilder sb = new StringBuilder();
        sb.append(" ﻿@Controller\n");
        sb.append(" @RequestMapping(\"/{projectId}/server/"+name+"\")\n");
        sb.append(" public class " + str1 + " {\n\t");
        sb.append(" \n\t");
        sb.append(" 	@Resource\n\t");
        sb.append(" 	private " + str2 + " " + AutoCreateJavaFile.firstLetterToLowerCase(str2) + ";\n\t");
        sb.append(" 	\n\t");

        //保存和更新
        sb.append(" 	@RequestMapping(value=\"/saveOrUpdateEntry\")\n\t");
        sb.append(" 	public ModelAndView saveOrUpdateEntry(String recordInfo) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         " + str1 + " record = service.saveOrUpdateEntry(recordInfo);\n\t");
        sb.append("         result.addObject(\"data\", record);\n\t");
        sb.append("         result.addObject(\"state\", state);\n\t");
        sb.append(" 		return result;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //查询全部返回一个list
        sb.append(" 	@RequestMapping(value=\"/queryList\")\n\t");
        sb.append(" 	public ModelAndView queryList(" + str1 + " record) throws Exception{\n\t");
        sb.append("         ModelAndView result = new ModelAndView();\n\t");
        sb.append("         State state = new State(\"0\");\n\t");
        sb.append("         List<" + str1 + "> recordList = service.queryList(recordInfo);\n\t");
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
        sb.append("         " + str1 + " record = service.queryRecordById(id);\n\t");
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
        sb.append(" }\n\t" );
        sb.append(" 	\n\t");

        BufferedWriter bw = null;
        File con = new File(PATH+""+str1+".java");
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
     *
     */
    public static String serviceTemple() throws Exception {

        StringBuilder sb = new StringBuilder();

        String str2 = name + "Service";
        String str3 = name + "Dao";

        sb = new StringBuilder();
        sb.append(" ﻿@Service\n");
        sb.append(" public class " + str2 + "  extends BaseService{\n\t");
        sb.append(" 	\n\t");
        sb.append(" 	@Resource\n\t");
        sb.append(" 	private " + str3 + " " + firstLetterToLowerCase(str3) + ";\n\t");
        sb.append(" \n\t");

        //保存和修改
        sb.append(" 	public "+name+" saveOrUpdateEntry(String recordStr) throws Exception {\n\t");
        sb.append("         objectMapper.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\"));\n\t");
        sb.append("         "+name+" record = objectMapper.readValue(new JSONObject(recordStr).toString(), " +
                ""+name+".class);\n\t");
        sb.append("         if (StringUtils.isNotBlank(record.getId())) {\n\t");
        sb.append("             record = updateRecord(record);\n\t");
        sb.append("         } else {\n\t");
        sb.append("             record = saveRecord(record);\n\t");
        sb.append("         }\n\t");
        sb.append(" 		return record;\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //查询全部
        sb.append(" 	public List<"+name+"> queryList("+name+" record) {\n\t");
        sb.append(" 		return mapper.queryList(record);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //根据查询某个数据
        sb.append(" 	public "+name+" queryRecordById(String id) {\n\t");
        sb.append(" 		return mapper.queryRecordById(id);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" 	\n\t");

        //删除
        sb.append(" 	public void deleteRecord(String id) {\n\t");
        sb.append(" 		mapper.deleteRecord(id);\n\t");
        sb.append(" 	}\n\t");
        sb.append(" }\n\t");

        BufferedWriter bw = null;
        File con = new File(PATH+""+str2+".java");
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
        sb.append(" public interface "+name+"Mapper {\n\t");
        sb.append(" 	\n\t");

        //保存
        sb.append(" 	void saveRecord("+name+" record);\n\t");
        sb.append(" \n\t");
        //修改
        sb.append(" 	void updateRecord("+name+" record);\n\t");
        sb.append(" \n\t");

        //查询全部
        sb.append(" 	List<"+name+"> queryList("+name+" record);\n\t");
        sb.append(" \n\t");

        //根据查询某个数据
        sb.append(" 	"+name+" queryRecordById(String record);\n\t");
        sb.append(" \n\t");

        //删除
        sb.append(" 	void deleteRecord(String id);\n\t");
        sb.append(" \n\t");
        sb.append(" }\n\t" );


        BufferedWriter bw = null;
        File con = new File(PATH+""+str3+".java");
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        return sb.toString();
    }

    /**
     * 生成时间： 2019/4/30 10:31
     * 方法说明：生产 xml
     * 开发人员：zhengyu
     */
    public static String mapperXMLTemple() throws Exception {

        StringBuilder sb = new StringBuilder();
        String str4 = name + "Mapper";

        sb = new StringBuilder();
        sb.append(" <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(" <!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis" +
                        ".org/dtd/mybatis-3-mapper.dtd\">\n\t");
        sb.append(" \n\n");
        sb.append(" <mapper namespace=\""+name+"Mapper\">");
        sb.append(" \n\t");
        sb.append(" \n\t");

        //查询公共模块
        sb.append(" 	<sql id=\"record_column\">\n\t");
        sb.append(" 	    xxxxxx\n\t");
        sb.append(" 	</sql>\n\t");
        sb.append(" \n\t");

        //新增
        sb.append(" 	<insert id=\"saveRecord\" parameterType=\""+name+"Entry\">\n\t");
        sb.append(" 	    xxxxxx\n\t");
        sb.append(" 	</insert>\n\t");
        sb.append(" \n\t");

        //更新
        sb.append(" 	<update id=\"updateRecord\" parameterType=\""+name+"Entry\">\n\t");
        sb.append(" 	    xxxxxx\n\t");
        sb.append(" 	</update>\n\t");
        sb.append(" \n\t");

        //查询全部
        sb.append(" 	<select id=\"queryList\" resultType=\""+name+"Entry\">\n\t");
        sb.append(" 	    xxxxxx\n\t");
        sb.append(" 	</select>\n\t");
        sb.append(" \n\t");

        //根据id查询具体数据
        sb.append(" 	<select id=\"queryRecordById\" resultType=\""+name+"Entry\">\n\t");
        sb.append(" 	    xxxxxx\n\t");
        sb.append(" 	</select>\n\t");
        sb.append(" \n\t");

        //删除
        sb.append(" 	<delete id=\"deleteRecord\">\n\t");
        sb.append(" 	    delete from YJ_PECCANCY_ENTRY where id=#{id}\n\t");
        sb.append(" 	</delete>\n\t");
        sb.append(" \n");
        sb.append(" </mapper>\n\t" );

        BufferedWriter bw = null;
        File con = new File(PATH+""+str4+".xml");
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
