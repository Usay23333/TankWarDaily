package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements FireStrategy {

    GameModel gm;

    @Override
    public void fire(Tank tank) {
        gm = tank.getGm();
        for (int i = 0; i < gm.objects.size(); i++) {
            if(gm.objects.get(i) instanceof Tank) {
                ((Tank) gm.objects.get(i)).die();
            }
        }


    }
}
