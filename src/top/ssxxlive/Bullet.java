package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	private int x, y, speed;
	private Direction bulletDir;
	private Group group;
	private boolean living = true;
	private GameModel gm;
	private ResourceMgr rm = ResourceMgr.getInstance();
	private int bulletWidth = rm.getBulletU().getWidth();
	private int bulletHeight = rm.getBulletU().getHeight();

	Rectangle rect = new Rectangle();

	public Bullet(int x, int y, int speed, Direction bulletDir, Group group, GameModel gm,Tank t) {
		if (bulletDir == Direction.UP) {
			this.x = x + t.getTankWidth() / 2 - bulletWidth / 2;
			this.y = y - bulletHeight;
		}
		if (bulletDir == Direction.DOWN) {
			this.x = x + t.getTankWidth() / 2 - bulletWidth / 2;
			this.y = y + t.getTankHeight();
		}
		if (bulletDir == Direction.LEFT) {
			this.x = x - bulletWidth;
			this.y = y + t.getTankHeight() / 2 - bulletHeight / 2;
		}
		if (bulletDir == Direction.RIGHT) {
			this.x = x + t.getTankWidth();
			this.y = y + t.getTankHeight() / 2 - bulletHeight / 2;
		}
		this.speed = speed;
		this.bulletDir = bulletDir;
		this.group = group;
		this.gm = gm;
		this.gm.objects.add(this);

		rect.x = x;
		rect.y = y;
		rect.width = bulletWidth;
		rect.height = bulletHeight;

	}

	public void setLive(boolean isLive) {
		this.living = isLive;
	}

	public boolean isLive() {
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
	
	public void die() {
		this.living = false;
		gm.remove(this);
	}
	
	public void paint(Graphics g) {
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

		if (x < 0 || y < 0 || x > gm.getGameRightStart() || y > gm.getGameDownStart())
			die();

	}

	public void collideWith(Tank t) {
		if (this.rect.intersects(t.rect) && this.getGroup() != t.getGroup()) {
			this.die();
			t.die();
			if (t.getGroup() != Group.GOOD) {
				gm.objects.remove(t);
			} else {
				//mainTankBoom += 1;
			}		
		}
	}

	public void collideWith(Bullet b) {
		if (this.rect.intersects(b.rect) && this.getGroup() != b.getGroup()) {
			this.die();
			b.die();
			gm.objects.remove(this);
			gm.objects.remove(b);
		}
	}
}
