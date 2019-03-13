package com.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/***
 *
 * @author zhengyu 把text 解析成 sql
 */
public class ParserSql {

    public static void main(String[] args) {
        try {
            FileInputStream sql = new FileInputStream("E:\\sql.txt");
            FileInputStream param = new FileInputStream("E:\\param.txt");

            // 读取sql中的到sqlBuffer中
            StringBuffer sqlBuffer = readTextInfo(sql);
            String sqlinfo = sqlBuffer.toString();

            StringBuffer paramBuffer = readTextInfo(param);

            // 替换sql中的?的数据
            String[] params = paramBuffer.toString().split(",");
            for (int i = 0; i < params.length; i++) {
                String index = params[i];
                String tail = index.substring(index.indexOf("(") + 1, index.lastIndexOf("") - 1);
                String head = index.substring(0, index.indexOf("("));

                // 是否为数值类型
                if ("Integer".equals(tail)) {

                } else {
                    head = "\"" + head.trim() + "\"";
                }

                sqlinfo = sqlinfo.replaceFirst("[?]", head);
            }

            System.out.println(sqlinfo.trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuffer readTextInfo(FileInputStream sql)
            throws IOException, UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        int i = 0;
        while ((i = sql.read(bytes)) != -1) {
            buffer.append(new String(bytes, "utf-8"));
        }

        return buffer;
    }

}
