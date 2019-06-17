package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	private int x, y, speed;
	private Direction bulletDir;
	private Group group;
	private boolean living = true;
	private ResourceMgr rm = ResourceMgr.getInstance();

	Rectangle rect = new Rectangle();

	public Bullet(int x, int y, int speed, Direction bulletDir, Group group, Tank t) {

        rect.width = rm.getBulletU().getWidth();
        rect.height = rm.getBulletU().getHeight();

		if (bulletDir == Direction.UP) {
			this.x = x + t.getRect().width / 2 - rect.width / 2;
			this.y = y - rect.height;
		}
		if (bulletDir == Direction.DOWN) {
			this.x = x + t.getRect().width / 2 - rect.width / 2;
			this.y = y + t.getRect().height;
		}
		if (bulletDir == Direction.LEFT) {
			this.x = x - rect.width;
			this.y = y + t.getRect().height / 2 - rect.height / 2;
		}
		if (bulletDir == Direction.RIGHT) {
			this.x = x + t.getRect().width;
			this.y = y + t.getRect().height / 2 - rect.height / 2;
		}
		this.speed = speed;
		this.bulletDir = bulletDir;
		this.group = group;

		rect.x = x;
		rect.y = y;

        GameModel.getInstance().add(this);

	}

	public void setLiving(boolean isLiving) {
		this.living = isLiving;
	}

	public boolean isLiving() {
		return this.living;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getBulletDir() {
		return bulletDir;
	}

	public void setBulletDir(Direction bulletDir) {
		this.bulletDir = bulletDir;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void die() {
		this.living = false;
	}
	
	public void paint(Graphics g) {
		if (!isLiving()) GameModel.getInstance().remove(this);
		switch (bulletDir) {
		case UP:
			g.drawImage(rm.getBulletU(), x, y, null);
			break;
		case DOWN:
			g.drawImage(rm.getBulletD(), x, y, null);
			break;
		case LEFT:
			g.drawImage(rm.getBulletL(), x, y, null);
			break;
		case RIGHT:
			g.drawImage(rm.getBulletR(), x, y, null);
			break;
		}

		bulletGo();

		rect.x = x;
		rect.y = y;

	}

	public void bulletGo() {
		if (bulletDir == Direction.UP) {
			y -= speed;
		}
		if (bulletDir == Direction.DOWN) {
			y += speed;
		}
		if (bulletDir == Direction.LEFT) {
			x -= speed;
		}
		if (bulletDir == Direction.RIGHT) {
			x += speed;
		}

		if (x < 0 || y < 0 || x > GameModel.getInstance().getGameRightStart() || y > GameModel.getInstance().getGameDownStart())
			die();
	}

}
