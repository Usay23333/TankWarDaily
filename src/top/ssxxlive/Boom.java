package top.ssxxlive;

import java.awt.Graphics;

public class Boom {
	private int x,y;
	private int boomStep = 0;
	private TankFrame tf;
	Boom(int x, int y, TankFrame tf){
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
			g.drawImage(Resource.booms[boomStep++], x, y, null);
			if (boomStep >= 16) {
				tf.booms.remove(this);
			}
	}
	
}
