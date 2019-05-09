package top.weapon;

/**
 * 枪，实现武器接口类
 * @author pero.yan
 *
 */
public class Gun implements Weapon {

	@Override
	public void shoot() {
		System.out.println("发射子弹。。。");
	}

}
