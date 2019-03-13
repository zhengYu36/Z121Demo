package cn.design.pattern.op;

public class WY implements Observer {

	@Override
	public void feed(String info) {
		System.out.println("wy 喂饲料！");
	}
	
}