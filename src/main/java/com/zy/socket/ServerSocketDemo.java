package com.zy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <ul>
 * <li>文件包名 : com.zy.socket</li>
 * <li>创建时间 : 2019/5/11 11:06</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：serviceSocket的学习
 *
 * @author zhengyu
 */
public class ServerSocketDemo {
    public static void main(String[] args) throws Exception {
        String ip = "localhost";
        int port = 9090;
        ServerSocket serverSocket = new ServerSocket(port, 1, InetAddress.getByName(ip));
        Socket accept = serverSocket.accept();
        //得到socket对应的输入流
        InputStream input = accept.getInputStream();

        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j=0; j<i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.print(request.toString());


    }
}
