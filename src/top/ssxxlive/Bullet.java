package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x, y, speed;
	private Direction bulletDir;
	private Group group;
	private boolean living = true;
	private TankFrame tf;
	private static int bulletWidth = Resource.bulletU.getWidth();
	private static int bulletHeight = Resource.bulletU.getHeight();
	
	Rectangle rect = new Rectangle();
	
	public Bullet(int x, int y, int speed, Direction bulletDir, Group group, TankFrame tf) {
		if (bulletDir == Direction.UP) {
			this.x = x + 15;
			this.y = y - 30;
		}
		if (bulletDir == Direction.DOWN) {
			this.x = x + 15;
			this.y = y + 60;
		}
		if (bulletDir == Direction.LEFT) {
			this.x = x - 30;
			this.y = y + 15;
		}
		if (bulletDir == Direction.RIGHT) {
			this.x = x + 60;
			this.y = y + 15;
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
			g.drawImage(Resource.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(Resource.bulletD, x, y, null);
			break;
		case LEFT:
			g.drawImage(Resource.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(Resource.bulletR, x, y, null);
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
				tf.bullets.get(i).collideWith(tf.mainTank);
			}
		}

	}
	
	public void collideWith(Tank t) {
		if (this.rect.intersects(t.rect) && this.getGroup() != t.getGroup()) {
			t.setLiving(false);
			if (t.getGroup() == Group.BAD) {
				tf.aiTanks.remove(t);
			}
			this.setLive(false);
			tf.booms.add(new Boom(t.getX(), t.getY(), tf));
		}
	}

}
