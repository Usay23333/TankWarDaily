package top.ssxxlive.cor;

import top.ssxxlive.GameObject;
import top.ssxxlive.Tank;

public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                //System.out.println("撞上了！");
            }
        }
        return true;
    }
}
