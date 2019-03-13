package cn.design.pattern.lsp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.lsp</li>
 * <li>创建时间 : 2018/12/13 16:42</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class CalcTest {

    public static void main(String[] args) {
        Calc cal = new Calc();
        cal.calc(10, 20);

        /**
         * 根据里氏替换原则，当父类替换为子类的时候，使用父类的时候的行为不应该
         * 发生变化，那么下面的这段代码，显然发生了变化，这样显然违反了里氏替换
         * 原则。
         */
        /*Calc calcSon = new CalcSon();
        calcSon.calc(10, 20);*/

    }
}
