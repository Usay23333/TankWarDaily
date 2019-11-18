package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.UUID;

public class Tank extends GameObject {

	private UUID id;
	private int x, y, speed;
	private Direction tankDir = Direction.UP;
	private Group group;
	private boolean living = true;
	private boolean moving = false;

	private ResourceMgr rm = ResourceMgr.getInstance();

	private int fireLevel = 0;

	Random r = new Random();
	Rectangle rect = new Rectangle();

	Tank(int x, int y, int speed, Group group) {
		this.id = UUID.randomUUID();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.group = group;

		rect.x = x;
		rect.y = y;

		if (group == Group.BAD) {

			this.moving = true;
			this.tankDir = Direction.values()[r.nextInt(4)];
			rect.width = rm.getBadTankU().getWidth();
			rect.height = rm.getBadTankU().getHeight();

		} else {

			rect.width = rm.getGoodTankU().getWidth();
			rect.height = rm.getGoodTankU().getHeight();

		}

		GameModel.getInstance().add(this);

	}

	public UUID getID() {
		return id;
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

	public Rectangle getRect() {
		return rect;
	}

	public int getFireLevel() {
		return fireLevel;
	}

	public void die() {
		System.out.println(this.getID());
		this.living = false;
		//GameModel.getInstance().remove(this);
		GameModel.getInstance().add(new Boom(x, y));
	}
	
	public void paint(Graphics g) {
		if (!isLiving()) GameModel.getInstance().remove(this);
		switch (tankDir) {
		case UP: // 第一个三元运算判断敌我坦克 第二个判断我方两张Tank图片 第三个判断敌方两张Tank图片
			g.drawImage(this.getGroup() == Group.GOOD ? y % 2 == 0 ? rm.getGoodTankU() : rm.getGoodTankU1()
					: y % 2 == 0 ? rm.getBadTankU() : rm.getBadTankU1(), x, y, null);
			break;
		case DOWN:
			g.drawImage(this.getGroup() == Group.GOOD ? y % 2 == 0 ? rm.getGoodTankD() : rm.getGoodTankD1()
					: y % 2 == 0 ? rm.getBadTankD() : rm.getBadTankD1(), x, y, null);
			break;
		case LEFT:
			g.drawImage(this.getGroup() == Group.GOOD ? x % 2 == 0 ? rm.getGoodTankL() : rm.getGoodTankL1()
					: x % 2 == 0 ? rm.getBadTankL() : rm.getBadTankL1(), x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.getGroup() == Group.GOOD ? x % 2 == 0 ? rm.getGoodTankR() : rm.getGoodTankR1()
					: x % 2 == 0 ? rm.getBadTankR() : rm.getBadTankR1(), x, y, null);
			break;
		}

		tankGo();

		rect.x = x;
		rect.y = y;

		if (r.nextInt(100) > 98 && this.getGroup() == Group.BAD) {
			this.fire();
		}
		if (r.nextInt(100) > 97 && this.getGroup() == Group.BAD) {
			this.setTankDir(Direction.values()[r.nextInt(4)]);
		}

	}

	public void tankGo() {
		if (!moving) return;
		if (tankDir == Direction.UP && y > GameModel.getInstance().getGameTopStart()) {
			y -= speed;
		}
		if (tankDir == Direction.DOWN && y <= GameModel.getInstance().getGameDownStart() - rect.height) {
			y += speed;
		}
		if (tankDir == Direction.LEFT && x > GameModel.getInstance().getGameLeftStart()) {
			x -= speed;
		}
		if (tankDir == Direction.RIGHT && x <= GameModel.getInstance().getGameRightStart() - rect.width) {
			x += speed;
		}

	}

	public void fire() {
		rm.getFireStrategy()[fireLevel].fire(this);
	}

	public void changeFire() {
		if (fireLevel < 2) {
			fireLevel += 1;
		} else {
			fireLevel = 0;
		}
	}

}
