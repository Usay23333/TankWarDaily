package top.ssxxlive;

public class FourFire implements Fire {
	TankFrame tf;
	Tank t;
	
	@Override
	public void fire(Tank t, TankFrame tf) {
		this.t = t;
		this.tf = tf;
		/*
		 * for(int i = 0; i < Direction.values().length; i++) {
		 * t.setTankDir(Direction.values()[i]); }
		 */
		tf.bullets.add(new Bullet(t, tf));
	}
	
}
