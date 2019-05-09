package top.ssxxlive;

public class PublicFire implements Fire {
	TankFrame tf;
	Tank t;
	
	@Override
	public void fire(Tank t, TankFrame tf) {
		this.t = t;
		this.tf = tf;
		
		tf.bullets.add(new Bullet(t, tf));
	}
	
}
