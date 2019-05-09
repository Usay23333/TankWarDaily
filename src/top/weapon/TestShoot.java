package top.weapon;

/**
 * 测试发射装置
 * @author pero.yan
 *
 */
public class TestShoot {
	public static void main(String[] args) {
		//发射装置中 安装枪
		Ejector e = new Ejector(new Gun());
		e.shoot();
		//发射装置中 安装炮
		e = new Ejector(new Cannon());
		e.shoot();
	}
}
