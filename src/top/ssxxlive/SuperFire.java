package top.ssxxlive;

import java.util.Iterator;

public class SuperFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {

        for (int i = 0; i < GameModel.getInstance().objects.size(); i++) {
            if(GameModel.getInstance().objects.get(i) instanceof Tank) {
                ((Tank) GameModel.getInstance().objects.get(i)).die();
            }
        }


    }
}
