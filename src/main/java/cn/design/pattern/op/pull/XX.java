package cn.design.pattern.op.pull;

public class XX implements Observer2 {

	@Override
	public void feed(Observable2 observer) {
		if(observer.isNeedFeed()){
			System.out.println("xx 喂食");
		}
	}

	
}