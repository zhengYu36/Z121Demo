package com.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * 生成各种繁琐而又有规律的代码
 * eg:sql代码等，反正还是挺实用的
 * @author w4155
 *
 */
@SuppressWarnings("rawtypes")
public class AutoCreateJavaFile {
	
	/**
	 *	主方法
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		//System.out.println(Math.round(-8.5));
		generateSql();
	}
	
	private static void generateSomeThingWithClassField(Class clazz) {
		Class clz = null;
		clz = clazz;
		String v1 = "list4";

		try {
			Field[] declaredFields = clz.getDeclaredFields();
			for (Field field : declaredFields) {
				System.out.println(v1+".add(\""+field.getName()+"\");");
			}
			
		} catch (Exception e) {

		}
		
		
	}


	

	/**
	 * 生成SQL语句
	 */
	public static void generateSql(){
		String name = "sql";
		String theFileOfRawSql = "D:\\sql.txt";
		try {
			BufferedReader br = openAStream(theFileOfRawSql);
			String str  = "";
			System.out.println("StringBuilder "+name+" = new StringBuilder();");
			while((str=br.readLine()) != null){
				System.out.println(name+".append(\" "+str+"\" ); ");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成model类的字段
	 */
	public static void generateModel(){
		String name = "sql";
		String theFileOfRawSql = "D:\\sql.txt";
		try {
			BufferedReader br = openAStream(theFileOfRawSql);
			String str  = "";
			while((str=br.readLine()) != null){
				System.out.println("/* "+str.split("	")[1]+" */"); 
				String type= str.split("	")[2].trim();
				type = firstLetterToUpperCase(type);
				System.out.println("private "+type+" "+changeTo(str.split("	")[0])+";"); 
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成model类的字段
	 */
	public static void generateModel2(){
		String name = "sql";
		String theFileOfRawSql = "D:\\sql.txt";
		try {
			BufferedReader br = openAStream(theFileOfRawSql);
			String str  = "";
			while((str=br.readLine()) != null){
				str.trim();
				if(str.startsWith("//")){
					
				}else{
					String string = str.split(" ")[2];
					string.trim();
					String substring = string.substring(0,string.length()-1);
					System.out.println("list3.add(\""+substring+"\");"); 
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateJavaFile() throws Exception{
		String name = "Message";
		String str1 = name+"Controller";
		String str2 = name+"Service";
		String str3 = name+"Dao";
		
		File con = new File("D:\\java\\"+str1+".java");
		File service = new File("D:\\java\\"+str2+".java");
		File dao = new File("D:\\java\\"+str3+".java");
		
		con.createNewFile();
		service.createNewFile();
		dao.createNewFile();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ﻿@Controller\r" );
		sb.append(" public class "+str1+" {\r\t" );
		sb.append(" \r\t" );
		sb.append(" 	@Resource\r\t" );
		sb.append(" 	private "+str2+" "+firstLetterToLowerCase(str2)+";\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	@ResponseBody\r\t" );
		sb.append(" 	@RequestMapping(value=\"/\", method=RequestMethod.POST)\r\t" );
		sb.append(" 	public Map<String, Object> getFoo(ReqParam reqParam) throws IOException{\r\t" );
		sb.append(" 		Map<String, Object> ret = new HashMap<String, Object>();\r\t" );
		sb.append(" 		return ret;\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	@ResponseBody\r\t" );
		sb.append(" 	@RequestMapping(value=\"/\", method=RequestMethod.POST)\r\t" );
		sb.append(" 	public Map<String, Object> addFoo(ReqParam reqParam) throws IOException{\r\t" );
		sb.append(" 		Map<String, Object> ret = new HashMap<String, Object>();\r\t" );
		sb.append(" 		return ret;\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	@ResponseBody\r\t" );
		sb.append(" 	@RequestMapping(value=\"/\", method=RequestMethod.POST)\r\t" );
		sb.append(" 	public Map<String, Object> updateFoo(ReqParam reqParam) throws IOException{\r\t" );
		sb.append(" 		Map<String, Object> ret = new HashMap<String, Object>();\r\t" );
		sb.append(" 		return ret;\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	@ResponseBody\r\t" );
		sb.append(" 	@RequestMapping(value=\"/\", method=RequestMethod.POST)\r\t" );
		sb.append(" 	public Map<String, Object> deleteFoo(ReqParam reqParam) throws IOException{\r\t" );
		sb.append(" 		Map<String, Object> ret = new HashMap<String, Object>();\r\t" );
		sb.append(" 		return ret;\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" }\r\t" );
		BufferedWriter bw = null;
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(con)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
		System.out.println("con");
		
		sb = new StringBuilder();
		sb.append(" ﻿@Service\r" );
		sb.append(" public class "+str2+" {\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	@Resource\r\t" );
		sb.append(" 	private "+str3+" "+firstLetterToLowerCase(str3)+";\r\t" );
		sb.append(" \r\t" );
		sb.append(" 	public List<"+name+"> get"+name+"(Object object) {\r\t" );
		sb.append(" 		return "+firstLetterToLowerCase(str3)+".select(object);\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void add"+name+"("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		"+firstLetterToLowerCase(str3)+".insert("+firstLetterToLowerCase(name)+");\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void update"+name+"("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		"+firstLetterToLowerCase(str3)+".update("+firstLetterToLowerCase(name)+");\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void delete"+name+"("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		"+firstLetterToLowerCase(str3)+".delete("+firstLetterToLowerCase(name)+");\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" }\r\t" );
		
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(service)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		System.out.println("service");
		
		sb = new StringBuilder();
		sb.append(" ﻿@Service\r" );
		sb.append(" public class "+str3+" {\r\t" );
		sb.append(" \r\t" );
		sb.append(" 	@Resource\r\t" );
		sb.append(" 	private JdbcTemplate jdbcTemplate;\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public List<"+name+"> select(Object obj){\r\t" );
		sb.append(" 		StringBuilder sql = new StringBuilder();\r\t" );
		sb.append(" 		return jdbcTemplate.query(sql);\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void insert("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		StringBuilder sql = new StringBuilder();\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void update("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		StringBuilder sql = new StringBuilder();\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" 	\r\t" );
		sb.append(" 	public void delete("+name+" "+firstLetterToLowerCase(name)+"){\r\t" );
		sb.append(" 		StringBuilder sql = new StringBuilder();\r\t" );
		sb.append(" 	}\r\t" );
		sb.append(" }\r\t" );

		
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dao)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		System.out.println("dao");

	}
	
	
	/***********分割线工具方法**************************************************************************************************/
	
	/**
	 * 将给定的数据装换成首字母大写
	 * @param str
	 * @return
	 */
	public static  String firstLetterToUpperCase(String str){
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter+str.substring(1, str.length());
	}
	
	/**
	 * 将给定的数据装换成首字母小写
	 * @param str
	 * @return
	 */
	public static  String firstLetterToLowerCase(String str){
		String firstLetter = str.substring(0, 1).toLowerCase();
		return firstLetter+str.substring(1, str.length());
	}
	
	/**
	 * 
	 * @return
	 */
	public static BufferedReader openAStream(String theFileOfRawSql){
		BufferedReader br = null;
		try {
			//br = new BufferedReader(new InputStreamReader(new FileInputStream(theFileOfRawSql)));
			//因为我是英文的操作系统，所以这里要转换成GBK，如果不是英文系统就不需要转换哈
			br = new BufferedReader(new InputStreamReader(new FileInputStream(theFileOfRawSql),"GBK"));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br;
	}
	
	/**
	 * 变成java驼峰命名
	 * @param str
	 * @return
	 */
	public static String changeTo(String str){
		str = str.toLowerCase();
		boolean flag = false;
		List<String> array = new ArrayList<String>();
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='_'){
				flag = true;
			}else{
				if(flag){
					array.add(String.valueOf(str.charAt(i)).toUpperCase());
					flag=false;
				}else{
					array.add(String.valueOf(str.charAt(i)));
				}
				
			}
		}
		String tmp ="";
		for (String string : array) {
			tmp+=string;
		}
		
		return tmp;
	}
	
}
