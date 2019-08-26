package com.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;

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
public class FileUtils {
    public static void main(String[] args) {
        //writeFile();

        //ReadFile(null);

        //把64转换为图片
        // WordUtil.Base64ToImage(str,"E:\\a3.png");
        //writeFile("E:\\ABC\\abc\\xx11\\2323", "abcb.txt", "aaa1123141");

        //复制文件
        try {
            boolean isok = copyFileUsingFileChannels("E:\\abc32", "back.sql",
                    "E:\\abc3", "back2.sql");
            System.out.println("filecopy  status is:" + isok);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeFile(String path, String name, String str) {

        try {
            //创建文件夹
            File makerFile = new File(path);
            if (!makerFile.exists()) {
                makerFile.mkdirs();
            }

            //如果存在,删除已经存在的
            File file = new File(path + File.separator + name);
            if (file.exists()) {
                file.delete();
            }

            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(str);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成时间： 2019/8/22 10:10
     * 方法说明：文件复制
     * 开发人员：zhengyu
     *
     * @param sourcePath     源文件路径
     * @param sourceFileName 源文件名称
     * @param destPath       目标文件路径
     * @param destFileName   目标文件名称
     * @return true 复制成功, false复制失败
     */
    public static boolean copyFileUsingFileChannels(String sourcePath, String sourceFileName, String destPath,
                                                    String destFileName) throws IOException {

        boolean isok = false;

        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(sourceFileName)
                || StringUtils.isEmpty(destPath) ||
                StringUtils.isEmpty(destFileName)) {
            throw new IllegalArgumentException("argument must not can't");
        }

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {

            //判断要拷贝的目标文件目录是否存在,如果不存在则创建
            File makerFile = new File(destPath);
            if (!makerFile.exists()) {
                makerFile.mkdirs();
            }

            File source = new File(sourcePath + File.separator + sourceFileName);
            File dest = new File(destPath + File.separator + destFileName);

            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            isok = true;
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            inputChannel.close();
            outputChannel.close();
        }

        return isok;
    }


    public static String ReadFile(String path) {
        //读取文件数据
        try {
            if (path == null || path.equals("")) {
                path = "E:\\file.txt";
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String stra = "";
            StringBuffer strb = new StringBuffer();
            while ((stra = in.readLine()) != null) {
                strb.append(stra);
            }

            //System.out.println(strb.toString());
            return strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
