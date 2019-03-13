package cn.design.pattern.pp;

import java.util.Scanner;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.pp</li>
 * <li>创建时间 : 2018/12/14 15:20</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Test2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str;
        while(true){
            str = sc.next();
            str = str.replace("吗","");
            str = str.replace("?","!");
            str = str.replace("?","!");
            System.out.println(str);
        }
    }
}
