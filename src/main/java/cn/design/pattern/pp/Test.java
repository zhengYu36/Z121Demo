package cn.design.pattern.pp;

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
public class Test {
    public static void main(String[] args) {
        // File file = new PDFProxy();
        //file.openFile();

        PDFFile pdf = new PDFFile();
        File proxy = (File) PDFProxy2.getProxy(pdf);
        proxy.openFile();
    }
}
