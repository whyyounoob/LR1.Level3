package by.company.LOGIC;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegExTest {

    private RegEx regEx;

    @Before
    public void setUp() throws Exception {
        regEx = new RegEx();
    }

    @After
    public void tearDown() throws Exception {
        regEx = null;
    }

    @Test
    public void checkCorrectEmail() {
        String correctEmail = "google@gmail.com";

        boolean expected = false;
        boolean actual = regEx.checkEmail(correctEmail);
        Assert.assertEquals("VSe OK.",expected,actual);
    }
}