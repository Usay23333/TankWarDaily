package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        if (!tank.isLiving()) return;
        for (int i = 0; i < GameModel.getInstance().objects.size(); i++) {
            if(GameModel.getInstance().objects.get(i) instanceof Tank) {
                if(((Tank) GameModel.getInstance().objects.get(i)).getGroup() == Group.BAD)
                ((Tank) GameModel.getInstance().objects.get(i)).die();
            }
        }


    }
}
