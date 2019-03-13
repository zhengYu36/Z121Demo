package cn.design.pattern.pp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.pp</li>
 * <li>创建时间 : 2018/12/14 15:17</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class PDFFile implements File {
    @Override
    public void openFile() {
        System.out.println("PDFFile");
    }
}
