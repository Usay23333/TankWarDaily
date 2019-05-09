package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements Fire {
	
	@Override
	public void fire(Tank t, TankFrame tf) {
		
		Iterator<Tank> i = tf.aiTanks.iterator();
		while (i.hasNext()) {
			Tank aT = i.next();
			aT.die();
			i.remove();
		}
	}

}
