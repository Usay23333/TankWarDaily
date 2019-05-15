package top.ssxxlive.cor;

import top.ssxxlive.Bullet;
import top.ssxxlive.GameObject;

public class BulletBulletCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Bullet) {
            Bullet b = (Bullet) o1;
            Bullet b1 = (Bullet) o2;
            if(b.getRect().intersects(b1.getRect()) && b.getGroup() != b1.getGroup()) {
                b.die();
                b1.die();
                return true;
            }
            return false;
        }
        return false;
    }
}
