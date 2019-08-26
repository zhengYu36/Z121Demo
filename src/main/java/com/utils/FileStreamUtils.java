package com.utils;

import java.io.*;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 15:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：通过写入流的方式来保存数据,这样不用通过StringBuilder来中转一次
 *
 * @author zhengyu
 */
public class FileStreamUtils {

    private final FileOutputStream fos;
    private final OutputStreamWriter osw;
    private final BufferedWriter bw;

    public FileStreamUtils(String path, String fileName) throws Exception {
        File filePath = new File(path);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File file = new File(path + File.separator + fileName);
       /* if (!file.exists()) {
            file.createNewFile();
        }*/

        this.fos = new FileOutputStream(file,true);
        this.osw = new OutputStreamWriter(this.fos, "UTF-8");
        //初始化大小200M
        this.bw = new BufferedWriter(osw, 1020 * 1024 * 200);
    }

    public void appeand(String str) throws IOException {
        bw.write(str);
    }

    public void close() throws IOException {

        //在关闭的时候自动更新流
        bw.flush();
        bw.close();
        osw.close();
        fos.close();
    }


}
