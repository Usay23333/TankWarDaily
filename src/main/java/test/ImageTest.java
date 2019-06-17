package test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		try {
			BufferedImage tankU = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("image/GoodTank/1Player/1/U1.png"));
			assertNotNull(tankU);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
