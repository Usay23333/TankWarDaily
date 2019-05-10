package test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import top.ssxxlive.ResourceMgr;

class ImageTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		try {
			BufferedImage tankU = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("resource/image/GoodTank/1Player/1/U1.png"));
			assertNotNull(tankU);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
