package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x, y, speed;
	private Direction bulletDir;
	private Group group;
	private boolean living = true;
	private TankFrame tf;
	private ResourceMgr rm = ResourceMgr.getInstance();
	private int bulletWidth = rm.getBulletU().getWidth();
	private int bulletHeight = rm.getBulletU().getHeight();

	Rectangle rect = new Rectangle();

	public Bullet(int x, int y, int speed, Direction bulletDir, Group group, TankFrame tf,Tank t) {
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
		this.tf = tf;

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

		if (x < 0 || y < 0 || x > tf.getWidth() || y > tf.getHeight())
			setLive(false);
		if (!tf.aiTanks.isEmpty()) {
			for (int i = 0; i < tf.aiTanks.size(); i++) {
				this.collideWith(tf.aiTanks.get(i));
			}
		}
		if (!tf.bullets.isEmpty()) {
			for (int i = 0; i < tf.bullets.size(); i++) {
				tf.bullets.get(i).collideWith(tf.mainTank1);
			}
		}

	}

	public void collideWith(Tank t) {
		if (this.rect.intersects(t.rect) && this.getGroup() != t.getGroup()) {
			this.setLive(false);
			t.setLiving(false);
			if (t.getGroup() == Group.BAD) {
				tf.aiTanks.remove(t);
			} else {
				tf.mainTankBoom += 1;
			}		
			tf.booms.add(new Boom(t.getX(), t.getY(), tf));
			
		}
	}

}
