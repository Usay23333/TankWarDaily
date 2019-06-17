package top.ssxxlive.net;

import top.ssxxlive.Direction;
import top.ssxxlive.Group;
import top.ssxxlive.Tank;

import java.util.UUID;

public class TankJoinMsg {
	private UUID id;
	private int x, y, speed;
	private Direction tankDir;
	private Group group;
	private boolean living, moving;

	
	public TankJoinMsg(Tank tank) {
		this.id = tank.getID();
		this.x = tank.getX();
		this.y = tank.getY();
		this.speed = tank.getSpeed();
		this.tankDir = tank.getTankDir();
		this.group = tank.getGroup();
		this.living = tank.isLiving();
		this.moving = tank.isMoving();
	}

	public byte[] toBytes(Tank tank) {

		return null;
	}
}
