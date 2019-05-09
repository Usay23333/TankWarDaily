package top.ssxxlive;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Properties;

public class TankFrame extends Frame {

	private static final long serialVersionUID = 1L;

	private String frameTitle = "Tank";
	private static final int GAME_WIDTH = 960, GAME_HEIGHT = 480;
	
	Properties pm = PropertyMgr.getInstance();
	
	ArrayList<Tank> aiTanks = new ArrayList<Tank>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Boom> booms = new ArrayList<Boom>();
	
	Tank mainTank1 = new Tank(470, 412, 1, Group.GOOD, this);
	int mainTankBoom = 0;

	TankFrame() {

		setTitle(frameTitle);
		setSize(GAME_WIDTH, GAME_HEIGHT);

		this.addKeyListener(new TankFrameKeyListener());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent c) {
				System.exit(0);
			}
		});
		this.addWindowFocusListener(new TankFrameFocusListener());

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		newAITanks();
	}

	Image offScreenImage = null;

	@Override // 双缓冲解决画面闪烁问题
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void paint(Graphics g) {
		if (!mainTank1.isLiving()) {
			Color c = g.getColor();
			g.setColor(Color.WHITE);
			g.drawString("被击中  " + mainTankBoom +" 次！", GAME_WIDTH - 120, 70);
			g.setColor(c);
		}

		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹数量：" + bullets.size(), 10, 50);
		g.drawString("敌方数量：" + aiTanks.size(), 10, 70);
		g.drawString("按 ESC 生产敌军！", GAME_WIDTH - 120, 50);
		g.drawString("按A切换火力模式！", GAME_WIDTH - 120, 90);
		//g.drawString("当前火力模式：", GAME_WIDTH - 120, 90);
		g.setColor(c);
		mainTank1.paint(g);

		for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) {
			if (!bullets.get(bulletNum).isLive()) {
				bullets.remove(bulletNum);
			}
		}

		for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) {
			bullets.get(bulletNum).paint(g);
		}

		for (int AITankNum = 0; AITankNum < aiTanks.size(); AITankNum++) {
			aiTanks.get(AITankNum).paint(g);
		}

		for (int i = 0; i < booms.size(); i++) {
			booms.get(i).paint(g);
		}

	}

	public void newAITanks() {
		for (int i = 1; i <= Integer.parseInt((String) pm.get("initCountTank")); i++) {
			aiTanks.add(new Tank(80 * i, 100, 1, Group.BAD, this));
		}
	}

	class TankFrameKeyListener extends KeyAdapter {
		boolean dirUp = false;
		boolean dirDown = false;
		boolean dirLeft = false;
		boolean dirRight = false;

		@Override
		public void keyPressed(KeyEvent p) {

			int keyCode = p.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				dirUp = true;
				break;
			case KeyEvent.VK_DOWN:
				dirDown = true;
				break;
			case KeyEvent.VK_LEFT:
				dirLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				dirRight = true;
				break;

			}

			changeTankDir();

		}

		@Override
		public void keyReleased(KeyEvent r) {
			int keyCode = r.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				dirUp = false;
				break;
			case KeyEvent.VK_DOWN:
				dirDown = false;
				break;
			case KeyEvent.VK_LEFT:
				dirLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				dirRight = false;
				break;
			case KeyEvent.VK_SPACE:
				mainTank1.fire();
				break;
			case KeyEvent.VK_A:
				mainTank1.changeFire();
				break;
			case KeyEvent.VK_ESCAPE:
				newAITanks();
			}

			changeTankDir();

		}

		public void changeTankDir() {
			mainTank1.setMoving(true);
			if (!dirUp && !dirDown && !dirLeft && !dirRight)
				mainTank1.setMoving(false);
			if (dirUp) {
				mainTank1.setTankDir(Direction.UP);
			}
			if (dirDown) {
				mainTank1.setTankDir(Direction.DOWN);
			}
			if (dirLeft) {
				mainTank1.setTankDir(Direction.LEFT);
			}
			if (dirRight) {
				mainTank1.setTankDir(Direction.RIGHT);
			}
		}

	}

	class TankFrameFocusListener extends WindowAdapter {
		boolean isFirstLostFocus = true;

		@Override
		public void windowGainedFocus(WindowEvent g) {

			if (!isFirstLostFocus) {

				setTitle("又好了+_+!");
				new Thread(()->{
					try {
						Thread.sleep(2000);
						setTitle(frameTitle);
					} catch (InterruptedException i) {
						i.printStackTrace();
					}
				}).start();
			}

		}

		@Override
		public void windowLostFocus(WindowEvent l) {

			setTitle("游戏崩溃了-_-!!");
			isFirstLostFocus = false;

		}

	}

}
