package top.ssxxlive;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResourceMgr {

	private static BufferedImage goodTankU, goodTankD, goodTankL, goodTankR;
	private static BufferedImage goodTankU1, goodTankD1, goodTankL1, goodTankR1;
	private static BufferedImage badTankU, badTankD, badTankL, badTankR;
	private static BufferedImage badTankU1, badTankD1, badTankL1, badTankR1;
	private static BufferedImage bulletU, bulletD, bulletL, bulletR;
	private static BufferedImage[] booms = new BufferedImage[16];
	private static FireStrategy[] fs = {new DefaultFire(), new FourDirFire(), new SuperFire()};
	private Map<FireStrategy,String> fireText;


	private ResourceMgr() {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/image/GoodTank/1Player/1/U1.png"));
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			
			goodTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/image/GoodTank/1Player/1/U2.png"));
			goodTankD1 = ImageUtil.rotateImage(goodTankU1, 180);
			goodTankL1 = ImageUtil.rotateImage(goodTankU1, -90);
			goodTankR1 = ImageUtil.rotateImage(goodTankU1, 90);

			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/image/BadTank/PublicTank/1/U1.png"));
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			
			badTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/image/BadTank/PublicTank/1/U2.png"));
			badTankD1 = ImageUtil.rotateImage(badTankU1, 180);
			badTankL1 = ImageUtil.rotateImage(badTankU1, -90);
			badTankR1 = ImageUtil.rotateImage(badTankU1, 90);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/image/Bullet/BulletU.png"));
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);

			for (int i = 0; i < booms.length; i++) {
				booms[i] = ImageIO
						.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resource/images/e" + (i + 1) + ".gif"));
			}

			fireText = new HashMap<FireStrategy,String>();
			fireText.put(fs[0],"普通火力");
			fireText.put(fs[1],"四射火力");
			fireText.put(fs[2],"超级火力");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getGoodTankU() {
		return goodTankU;
	}
	public BufferedImage getGoodTankD() {
		return goodTankD;
	}
	public BufferedImage getGoodTankL() {
		return goodTankL;
	}
	public BufferedImage getGoodTankR() {
		return goodTankR;
	}
	public BufferedImage getGoodTankU1() {
		return goodTankU1;
	}
	public BufferedImage getGoodTankD1() {
		return goodTankD1;
	}
	public BufferedImage getGoodTankL1() {
		return goodTankL1;
	}
	public BufferedImage getGoodTankR1() {
		return goodTankR1;
	}
	public BufferedImage getBadTankU() {
		return badTankU;
	}
	public BufferedImage getBadTankD() {
		return badTankD;
	}
	public BufferedImage getBadTankL() {
		return badTankL;
	}
	public BufferedImage getBadTankR() {
		return badTankR;
	}
	public BufferedImage getBadTankU1() {
		return badTankU1;
	}
	public BufferedImage getBadTankD1() {
		return badTankD1;
	}
	public BufferedImage getBadTankL1() {
		return badTankL1;
	}
	public BufferedImage getBadTankR1() {
		return badTankR1;
	}
	public BufferedImage getBulletU() {
		return bulletU;
	}
	public BufferedImage getBulletD() {
		return bulletD;
	}
	public BufferedImage getBulletL() {
		return bulletL;
	}
	public BufferedImage getBulletR() {
		return bulletR;
	}
	public BufferedImage[] getBooms() {
		return booms;
	}
	public FireStrategy[] getFireStrategy() {
		return fs;
	}
	public String getFireText(int fireLevel) {
		return fireText.get(fs[fireLevel]);
	}

	private static class ResourceInner {
		
		private static final ResourceMgr RM = new ResourceMgr();
		
	}
	
	public static ResourceMgr getInstance() {
		return ResourceInner.RM;
	}
}
