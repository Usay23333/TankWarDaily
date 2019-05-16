package top.ssxxlive.cor;

import top.ssxxlive.Bullet;
import top.ssxxlive.GameObject;
import top.ssxxlive.Tank;

public class TankBulletCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank t = (Tank) o1;
            Bullet b = (Bullet) o2;
            if(t.getRect().intersects(b.getRect()) && t.getGroup() != b.getGroup()) {
                t.die();
                b.die();
                return true;
            }
        } else if (o1 instanceof Bullet && o2 instanceof Tank) {
            Tank t = (Tank) o2;
            Bullet b = (Bullet) o1;
            collide(o2, o1);
        } else {
            return false;
        }
        return false;
    }
}
