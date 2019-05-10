package top.ssxxlive;

public class DefaultFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        new Bullet(tank.getX(), tank.getY(), tank.getSpeed() * 2, tank.getTankDir(), tank.getGroup(), tank.getTankFrame(), tank);
    }
}
