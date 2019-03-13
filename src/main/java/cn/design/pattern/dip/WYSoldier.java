package cn.design.pattern.dip;

public class WYSoldier implements Soldier{
 
	@Override
	public void fireEnermy(Gun gun) {
		gun.shoot();
	}
}