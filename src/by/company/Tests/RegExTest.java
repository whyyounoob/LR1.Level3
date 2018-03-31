package by.company.Tests;

import by.company.LOGIC.RegEx;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegExTest {

    private RegEx regEx;

    @Before
    public void setUp()  {
        regEx = new RegEx();
    }

    @After
    public void tearDown() {
        regEx = null;
    }

    @Test
    public void checkCorrectEmail() {
        String correctEmail = "gooogle@gmail.com";

        boolean expected = true;
        boolean actual = regEx.checkEmail(correctEmail);
        Assert.assertEquals("Bad email", expected, actual);
    }

    @Test
    public void checkIncorrectEmail(){
        String incorrectEmail = "rebhrrvkjerjv";

        boolean expected = false;
        boolean actual = regEx.checkEmail(incorrectEmail);
        Assert.assertEquals("Good email.",expected,actual);
    }



}