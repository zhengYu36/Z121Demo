package cn.design.pattern.op;

public class XX implements Observer {
 
	@Override
	public void feed(String info) {
		System.out.println("xx  "+info+" 喂饲料！");
	}
	
}