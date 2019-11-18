package top.ssxxlive;

import top.ssxxlive.cor.BulletBulletCollider;
import top.ssxxlive.cor.CollideChain;
import top.ssxxlive.cor.TankBulletCollider;
import top.ssxxlive.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;

public class GameModel {

    CollideChain cc = new CollideChain();

    private int gameTopStart, gameDownStart, gameLeftStart, gameRightStart;
    private int mainTankBoom = 0; //被击中&死亡次数 备用

    private static final GameModel GM = new GameModel();

    static {
        GM.TIANCONGYUN();
        //GM.newAITanks();
    }

    Tank mainTank1;

    private GameModel() {
        cc.add(new TankBulletCollider())
                .add(new BulletBulletCollider())
                .add(new TankTankCollider());
    }

    public final void TIANCONGYUN() {
        mainTank1 = new Tank(470, 412, 1, Group.GOOD);
    }

    public static GameModel getInstance() {
        return GM;
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



    ArrayList<GameObject> objects = new ArrayList<>();

    public void newAITanks() {
        for (int i = 1; i <= Integer.parseInt((String) PropertyMgr.getInstance().get("initCountTank")); i++) {
            new Tank(80 * i, 100, 1, Group.BAD);
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
            if (object instanceof Tank && ((Tank) object).getGroup() == Group.BAD) a += 1;
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
        g.drawString("按 R 键复活！", 20, 90);
        g.drawString("按 ESC 生产敌军！", gameRightStart - 160, 50);
        g.drawString("当前火力模式：" + ResourceMgr.getInstance()
                .getFireText(mainTank1.getFireLevel()), gameRightStart - 160, 70);
        g.drawString("按 A 切换火力模式！", gameRightStart - 160, 90);

        g.setColor(c);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                cc.collide(objects.get(i), objects.get(j));
            }
        }
    }

}
