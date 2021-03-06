package com.utils;

import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/5/8 19:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * 代码生成工具，通过扫描实体对应的报来批次创建
 * 由于我喜欢首先创建实体，然后来通过该实体来创建表，创建controller,service,mapper等
 * 但是如果通过扫描一个包都来生成，是不是就更酷了，
 * 所有我这样来做 ,good luck.
 * @author zhengyu
 */
public class GeneratorUtils {
    //private static String packName = "com.yanjoy.scbim.mp.entity.monitor";
    private static String packName = "measure.management";
    public static String SCHEMANAME = "GZDT11BETA";

    public static void main(String[] args) throws Exception{

        //0.通过包来扫描获取所属类
        List<String> className = PackageUtil.getClassName(packName);
        System.out.println("创建模板开始......");
        className.forEach(i->{
            //System.out.println(i);
            //获取实体名，需要有截取
            i = i.substring(i.lastIndexOf("\\")+1);
            try {
                /**
                 * 创建相关 java文件
                 *      创建相关java类(包含，controller,service,mapper等)
                 */
                CreateControllerOther.CreateFile(null,i,true);

                //拼装表名
                String tableName = "";
                tableName = OneStringUtils.camelToUnderline(i);
                tableName = "YJ"+tableName;
                tableName = tableName.toUpperCase();

                //2.创建xml (xml需要额外创建) 和 sql输出
                //默认不排除额外的属性,
                GeneratorProduceMapperCRUD.createSql(packName+"."+i,SCHEMANAME,tableName);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        System.out.println("模板创建结束......");


    }
}
