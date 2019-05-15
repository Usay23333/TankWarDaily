package top.ssxxlive;

import java.awt.*;

public class Boom extends GameObject {
	private int x,y;
	private int boomStep = 0;
	private GameModel gm;
	private ResourceMgr rm = ResourceMgr.getInstance();
	Boom(int x, int y, GameModel gm){
		this.x = x;
		this.y = y;
		this.gm = gm;
	}
	
	public void paint(Graphics g) {
			g.drawImage(rm.getBooms()[boomStep++], x, y, null);
			if (boomStep >= 16) {
				gm.objects.remove(this);
			}
	}

}
