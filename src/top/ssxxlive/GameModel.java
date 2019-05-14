package top.ssxxlive;

import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

public class GameModel {

    Properties pm = PropertyMgr.getInstance();

    private int gameTopStart, gameDownStart, gameLeftStart, gameRightStart;
    private int mainTankBoom = 0;

    GameModel() {
        newAITanks();
    }

    public int getGameTopStart() {
        return gameTopStart;
    }

    public void setGameTopStart(int gameTopStart) {
        this.gameTopStart = gameTopStart;
    }

    public int getGameDownStart() {
        return gameDownStart;
    }

    public void setGameDownStart(int gameDownStart) {
        this.gameDownStart = gameDownStart;
    }

    public int getGameLeftStart() {
        return gameLeftStart;
    }

    public void setGameLeftStart(int gameLeftStart) {
        this.gameLeftStart = gameLeftStart;
    }

    public int getGameRightStart() {
        return gameRightStart;
    }

    public void setGameRightStart(int gameRightStart) {
        this.gameRightStart = gameRightStart;
    }

    Tank mainTank1 = new Tank(470, 412, 1, Group.GOOD, this);
    ArrayList<GameObject> objects = new ArrayList<>();

    public void newAITanks() {
        for (int i = 1; i <= Integer.parseInt((String) pm.get("initCountTank")); i++) {
            add(new Tank(80 * i, 100, 1, Group.BAD, this));
        }
    }

    public void add(GameObject o) {
        objects.add(o);
    }

    public void remove(GameObject o) {
        objects.remove(o);
    }

    public int getBadTankAmount() {
        int a = 0;
        for (GameObject object : objects) {
            if (object instanceof Tank) a += 1;
        }
        return a;
    }

    public int getBadBulletAmount() {
        int a = 0;
        for (GameObject object : objects) {
            if (object instanceof Bullet) a += 1; //敌我子弹数量和
            //if (object instanceof Bullet && ((Bullet) object).getGroup() == Group.BAD) a += 1; //敌方子弹数量和
        }
        return a;
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + getBadBulletAmount(), 20, 50);
        g.drawString("敌方数量：" + getBadTankAmount(), 20, 70);
        g.drawString("被击中 " + mainTankBoom +" 次！", 20, 90);
        g.drawString("按 ESC 生产敌军！", gameRightStart - 160, 50);
        g.drawString("当前火力模式：" + mainTank1.fireMode.get(mainTank1.getTankFire()), gameRightStart - 160, 70);
        g.drawString("按 A 切换火力模式！", gameRightStart - 160, 90);

        g.setColor(c);

        mainTank1.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
    }
}