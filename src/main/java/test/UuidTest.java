package test;

import org.junit.jupiter.api.Test;
import top.ssxxlive.TokenProccessor;

public class UuidTest {

    @Test
    public void uuidTest() {
        String s = TokenProccessor.getInstance().makeToken();
        System.out.println(s);

    }
}
