package top.ssxxlive.cor;

import top.ssxxlive.GameObject;

import java.util.LinkedList;
import java.util.List;

public class CollideChain implements Collider{

    List<Collider> collideChains = new LinkedList<>();

    public CollideChain add(Collider c) {
        collideChains.add(c);
        return this;
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < collideChains.size(); i++) {
            if (collideChains.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }

}
