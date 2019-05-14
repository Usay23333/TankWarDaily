package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        GameObject o;
        Iterator<GameObject> i = tank.getGm().objects.iterator();
        while (i.hasNext()) {
            if((o = i.next()) instanceof Tank) {
                //TODO 此处遗留Bug
                //o.die();
                i.remove();
            }
        }

    }
}
