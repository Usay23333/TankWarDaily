package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {

	private int x, y, speed;
	private Direction tankDir = Direction.UP;
	private Group group;
	private boolean living = true;
	private boolean moving = false;
	private TankFrame tf;
	private int tankWidth;
	private int tankHeight;

	Random r = new Random();
	Rectangle rect = new Rectangle();

	Tank(int x, int y, int speed, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.group = group;
		this.tf = tf;
		if (group == Group.BAD) {
			this.moving = true;
			this.tankDir = Direction.values()[r.nextInt(4)];
			this.tankWidth = ResourceMgr.badTankU.getWidth();
			this.tankHeight = ResourceMgr.badTankU.getHeight();
		}

		rect.x = x;
		rect.y = y;
		rect.width = tankWidth = ResourceMgr.goodTankU.getWidth();
		rect.height = tankHeight = ResourceMgr.goodTankU.getHeight();

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getTankDir() {
		return tankDir;
	}

	public void setTankDir(Direction tankDir) {
		this.tankDir = tankDir;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getTankWidth() {
		return tankWidth;
	}

	public int getTankHeight() {
		return tankHeight;
	}

	public void paint(Graphics g) {
		switch (tankDir) {
		case UP: // 第一个三元运算判断敌我坦克 第二个判断我方两张Tank图片 第三个判断敌方两张Tank图片
			g.drawImage(this.getGroup() == Group.GOOD ? y % 2 == 0 ? ResourceMgr.goodTankU : ResourceMgr.goodTankU1
					: y % 2 == 0 ? ResourceMgr.badTankU : ResourceMgr.badTankU1, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.getGroup() == Group.GOOD ? y % 2 == 0 ? ResourceMgr.goodTankD : ResourceMgr.goodTankD1
					: y % 2 == 0 ? ResourceMgr.badTankD : ResourceMgr.badTankD1, x, y, null);
			break;
		case LEFT:
			g.drawImage(this.getGroup() == Group.GOOD ? x % 2 == 0 ? ResourceMgr.goodTankL : ResourceMgr.goodTankL1
					: x % 2 == 0 ? ResourceMgr.badTankL : ResourceMgr.badTankL1, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.getGroup() == Group.GOOD ? x % 2 == 0 ? ResourceMgr.goodTankR : ResourceMgr.goodTankR1
					: x % 2 == 0 ? ResourceMgr.badTankR : ResourceMgr.badTankR1, x, y, null);
			break;
		}

		tankGo();

		rect.x = x;
		rect.y = y;

		if (r.nextInt(100) > 97 && this.getGroup() == Group.BAD) {
			this.fire();
		}
		if (r.nextInt(100) > 97 && this.getGroup() == Group.BAD) {
			this.setTankDir(Direction.values()[r.nextInt(4)]);
		}

	}

	public void tankGo() {
		if (!moving)
			return;
		if (tankDir == Direction.UP && y > tf.getInsets().top) {
			y -= speed;
		}
		if (tankDir == Direction.DOWN && y <= tf.getHeight() - tankHeight - tf.getInsets().bottom) {
			y += speed;
		}
		if (tankDir == Direction.LEFT && x > tf.getInsets().left) {
			x -= speed;
		}
		if (tankDir == Direction.RIGHT && x <= tf.getWidth() - tankWidth - tf.getInsets().right) {
			x += speed;
		}

	}

	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.getSpeed() * 2, this.tankDir, this.getGroup(), tf, this));
	}

	public void superFire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.getSpeed() * 4, Direction.UP, this.getGroup(), tf, this));
		tf.bullets.add(new Bullet(this.x, this.y, this.getSpeed() * 4, Direction.DOWN, this.getGroup(), tf, this));
		tf.bullets.add(new Bullet(this.x, this.y, this.getSpeed() * 4, Direction.LEFT, this.getGroup(), tf, this));
		tf.bullets.add(new Bullet(this.x, this.y, this.getSpeed() * 4, Direction.RIGHT, this.getGroup(), tf, this));
	}

}
