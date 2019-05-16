package top.ssxxlive;

import java.awt.*;

public class Boom extends GameObject {
	private int x,y;
	private int boomStep = 0;
	private ResourceMgr rm = ResourceMgr.getInstance();
	Boom(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
			g.drawImage(rm.getBooms()[boomStep++], x, y, null);
			if (boomStep >= 16) {
				GameModel.getInstance().remove(this);
			}
	}

}
