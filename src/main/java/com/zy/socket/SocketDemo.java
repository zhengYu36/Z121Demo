package com.zy.socket;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * <ul>
 * <li>文件包名 : com.zy.socket</li>
 * <li>创建时间 : 2019/5/11 14:09</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：socket客户端，其实该客户端就类似于浏览器通过url访问一样
 *
 * @author zhengyu
 */
public class SocketDemo {
    public static void main(String[] args) throws Exception{
        String ip = "localhost";
        int port = 9090;
        Socket socket = new Socket(InetAddress.getByName(ip),port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(121);
        //PrintStream out = new PrintStream(socket.getOutputStream());
        //out.println("xxxx");

    }
}
