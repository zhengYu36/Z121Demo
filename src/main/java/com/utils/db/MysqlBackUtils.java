package com.utils.db;

import java.io.*;

/**
 * <ul>
 * <li>文件包名 : com.utils.db</li>
 * <li>创建时间 : 2019/8/19 16:16</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明:  mysql 数据库的备份工具
 *
 * @author zhengyu
 */
public class MysqlBackUtils {

    public static void main(String[] args) {

        String hostIp = "192.168.1.10";
        //String hostIp = "127.0.0.1";
        String userName = "root";
        String password = "root";
        String databaseName = "xby_design_review";
        //String databaseName = "zy";

       /* backup(hostIp, userName, password,
                "E:", "mydb6", databaseName);*/

        //测试导入到本地数据库
        String path = "E:\\mydb6.sql";
        //restore("E:\\mysqlback\\back.sql", hostIp, "zy", userName, password);
        restore(path, hostIp, "szyj", userName, "root");

    }


    /**
     * @param hostIP       ip地址，可以是本机也可以是远程
     * @param userName     数据库的用户名
     * @param password     数据库的密码
     * @param savePath     备份的路径
     * @param fileName     备份的文件名
     * @param databaseName 需要备份的数据库的名称
     * @return
     */
    public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName,
                                 String databaseName) {
        fileName += ".sql";
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        //拼接命令行的命令
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump").append(" --opt").append(" -h").append(hostIP);
        stringBuilder.append(" --user=").append(userName).append(" --password=").append(password)
                .append(" --lock-all-tables=true");
        stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ")
                .append(databaseName);
        try {
            System.out.println(stringBuilder.toString());
            //调用外部执行exe文件的javaAPI
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @param filepath 要恢复的文件地址
     * @param ip       ip地址
     * @param database 要恢复到的数据库名称
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @return
     */
    public static void restore(String filepath, String ip, String database, String userName, String password) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("mysql -h" + ip + " -u" + userName + " -p" + password + " --default-character-set=utf8 "
                            + database);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filepath), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            // System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
            System.out.println("restore success...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
