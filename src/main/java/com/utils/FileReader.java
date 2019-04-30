package com.utils;

import java.io.*;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 15:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class FileReader {
    public static void main(String[] args) {
        //writeFile();

        ReadFile(null);

        //把64转换为图片
       // WordUtil.Base64ToImage(str,"E:\\a3.png");

    }

    public static void writeFile(String path,String name,String str) {
        //把图片转换为base64,然后保存
        //String str = WordUtil.ImageToBase64ByLocal("E:\\a1.png");
        //保存到本地
        try {
            String pathAndName ="";
            if(path ==null || path.equals("")){
                pathAndName = "E:/"+name;
            }
            FileOutputStream fos = new FileOutputStream(new File(pathAndName));
            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
            osw.write(str);
            osw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String ReadFile(String path) {
        //读取文件数据
        try{
            if(path ==null || path.equals("")){
                path = "E:\\file.txt";
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String stra = "";
            StringBuffer strb = new StringBuffer();
            while((stra = in.readLine())!=null){
                strb.append(stra);
            }

            //System.out.println(strb.toString());
            return strb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
