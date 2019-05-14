package top.ssxxlive;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tank extends GameObject {

	private int x, y, speed;
	private Direction tankDir = Direction.UP;
	private Group group;
	private boolean living = true;
	private boolean moving = false;
	private GameModel gm;
	private ResourceMgr rm = ResourceMgr.getInstance();
	private int tankWidth;
	private int tankHeight;
	private int fireLevel = 0;
	FireStrategy[] fs = {rm.getDefaultFire(), rm.getFourDirFire(), rm.getSuperFire()};

	Map<FireStrategy,String> fireMode = new HashMap<FireStrategy,String>();

	private FireStrategy tankFire = fs[fireLevel];

	Random r = new Random();
	Rectangle rect = new Rectangle();

	Tank(int x, int y, int speed, Group group, GameModel gm) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.group = group;
		this.gm = gm;

		rect.x = x;
		rect.y = y;

		if (group == Group.BAD) {
			this.moving = true;
			this.tankDir = Direction.values()[r.nextInt(4)];
			rect.width = this.tankWidth = rm.getBadTankU().getWidth();
			rect.height = this.tankHeight = rm.getBadTankU().getHeight();
		} else {
			rect.width = this.tankWidth = rm.getGoodTankU().getWidth();
			rect.height = this.tankHeight = rm.getGoodTankU().getHeight();
		}

		fireMode.put(fs[0],"普通火力");
		fireMode.put(fs[1],"四射火力");
		fireMode.put(fs[2],"超级火力");

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

	public GameModel getGm() {
		return gm;
	}

	public FireStrategy getTankFire() {
		return tankFire;
	}

	public void die() {
		this.living = false;
		gm.objects.add(new Boom(x, y, gm));
	}
	
	public void paint(Graphics g) {
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
		if (!moving)
			return;
		if (tankDir == Direction.UP && y > gm.getGameTopStart()) {
			y -= speed;
		}
		if (tankDir == Direction.DOWN && y <= gm.getGameDownStart() - tankHeight) {
			y += speed;
		}
		if (tankDir == Direction.LEFT && x > gm.getGameLeftStart()) {
			x -= speed;
		}
		if (tankDir == Direction.RIGHT && x <= gm.getGameRightStart() - tankWidth) {
			x += speed;
		}

	}

	public void fire() {
		tankFire.fire(this);
	}

	public void changeFire() {
		//tankFire = new FourDirFire();
		//tankFire = new SuperFire();
		if (fireLevel < 2) {
			tankFire = fs[fireLevel += 1];
		} else {
			tankFire = fs[fireLevel = 0];
		}
	}

}
