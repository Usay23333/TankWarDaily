package top.ssxxlive;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private String frameTitle = "Tank";
	private static final int GAME_WIDTH = 960, GAME_HEIGHT = 480;

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

		GameModel.getInstance().setGameTopStart(getInsets().top);
		GameModel.getInstance().setGameDownStart(GAME_HEIGHT - getInsets().bottom);
		GameModel.getInstance().setGameLeftStart(getInsets().left);
		GameModel.getInstance().setGameRightStart(GAME_WIDTH - getInsets().right);
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

		GameModel.getInstance().paint(g);

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
				GameModel.getInstance().mainTank1.fire();
				break;
			case KeyEvent.VK_A:
				GameModel.getInstance().mainTank1.changeFire();
				break;
			case KeyEvent.VK_ESCAPE:
				GameModel.getInstance().newAITanks();
			}

			changeTankDir();

		}

		public void changeTankDir() {
			GameModel.getInstance().mainTank1.setMoving(true);
			if (!dirUp && !dirDown && !dirLeft && !dirRight)
				GameModel.getInstance().mainTank1.setMoving(false);
			if (dirUp) {
				GameModel.getInstance().mainTank1.setTankDir(Direction.UP);
			}
			if (dirDown) {
				GameModel.getInstance().mainTank1.setTankDir(Direction.DOWN);
			}
			if (dirLeft) {
				GameModel.getInstance().mainTank1.setTankDir(Direction.LEFT);
			}
			if (dirRight) {
				GameModel.getInstance().mainTank1.setTankDir(Direction.RIGHT);
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