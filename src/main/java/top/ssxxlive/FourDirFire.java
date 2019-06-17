package top.ssxxlive;

public class FourDirFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Direction[] dirs = Direction.values();

        for (Direction dir : dirs) {
            new Bullet(tank.getX(), tank.getY(), tank.getSpeed() * 4, dir, tank.getGroup(), tank);
        }
    }
}