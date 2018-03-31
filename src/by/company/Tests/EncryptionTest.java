package by.company.Tests;

import by.company.LOGIC.Encryption;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class EncryptionTest {

    @Test
    public void getHashedPassword() {
        String password = "qwe";
        String expected = "aVndosrt4ozuDrirY7L6Lm1rxHnbE+xm9MG0qvZdEs0=";
        String actual = null;

        try {
            actual = Encryption.getHashedPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Wrong password", expected,actual);
    }
}