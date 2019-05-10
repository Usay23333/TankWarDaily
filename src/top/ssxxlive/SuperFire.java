package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {

        Iterator<Tank> i = tank.getTankFrame().aiTanks.iterator();
        while (i.hasNext()) {
            i.next().die();
            i.remove();
        }

    }
}
