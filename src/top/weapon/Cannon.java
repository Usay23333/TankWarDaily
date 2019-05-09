package top.weapon;

/**
 * 炮，实现武器接口
 * @author pero.yan
 *
 */
public class Cannon implements Weapon {

	@Override
	public void shoot() {
		System.out.println("发射炮弹。。。");
	}

}
