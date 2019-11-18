package top.ssxxlive;

public class DefaultFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        if(!tank.isLiving()) return;
        new Bullet(tank.getX(), tank.getY(), tank.getSpeed() * 2, tank.getTankDir(), tank.getGroup(), tank);
    }
}
