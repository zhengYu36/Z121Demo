package cn.design.pattern.op.pull;

public class WY implements Observer2 {

	@Override
	public void feed(Observable2 observer) {
		if(observer.isNeedFeed()){
			System.out.println("wy 喂食");
		}
	}

	
}