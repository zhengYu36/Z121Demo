package cn.design.pattern.dip;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.dip</li>
 * <li>创建时间 : 2018/12/13 17:12</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class GunTest {

    public static void main(String[] args) {

        System.out.println("xxx");
        Soldier soldier = new WYSoldier();
        soldier.fireEnermy(new SniperRifle());
        soldier.fireEnermy(new AK47Gun());

    }
}
