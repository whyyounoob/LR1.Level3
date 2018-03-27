package by.company.LOGIC;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encryption{

    public static String getHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String saltedHash = null;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(password.getBytes());
            KeySpec spec = new PBEKeySpec(password.toCharArray(), bytes, Constants.ITERATION_COUNT,
                    Constants.KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constants.ALGORITHM);
            byte[] enteredPasswordHash = factory.generateSecret(spec).getEncoded();
            saltedHash = Base64.getEncoder().encodeToString(enteredPasswordHash);

        return saltedHash;
    }

}
