package top.ssxxlive;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {

	public static BufferedImage goodTankU, goodTankD, goodTankL, goodTankR;
	public static BufferedImage goodTankU1, goodTankD1, goodTankL1, goodTankR1;
	public static BufferedImage badTankU, badTankD, badTankL, badTankR;
	public static BufferedImage badTankU1, badTankD1, badTankL1, badTankR1;
	public static BufferedImage bulletU, bulletD, bulletL, bulletR;
	public static BufferedImage[] booms = new BufferedImage[16];

	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/1Player/1/m1-u-1.png"));
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			
			goodTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/1Player/1/m1-u-2.png"));
			goodTankD1 = ImageUtil.rotateImage(goodTankU1, 180);
			goodTankL1 = ImageUtil.rotateImage(goodTankU1, -90);
			goodTankR1 = ImageUtil.rotateImage(goodTankU1, 90);

			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/BadTank/PublicTank/1/1-U-1.png"));
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			
			badTankU1 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/BadTank/PublicTank/1/1-U-2.png"));
			badTankD1 = ImageUtil.rotateImage(badTankU1, 180);
			badTankL1 = ImageUtil.rotateImage(badTankU1, -90);
			badTankR1 = ImageUtil.rotateImage(badTankU1, 90);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bullet/BulletU.png"));
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);

			for (int i = 0; i < booms.length; i++) {
				booms[i] = ImageIO
						.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
