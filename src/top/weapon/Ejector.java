package top.weapon;

/**
 * 发射装置
 * @author pero.yan
 *
 */
public class Ejector {
	//可以安装各种武器（实现了武器接口类就可以）
	private Weapon w;
	
	public Ejector(Weapon w1){
		w = w1;
	}
	public void shoot(){
		w.shoot();
	}
}
