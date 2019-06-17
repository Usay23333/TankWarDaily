package top.ssxxlive;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

public class TokenProccessor {

    Base64 base64 = new Base64();
    private TokenProccessor(){};
    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            return base64.encodeToString(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

