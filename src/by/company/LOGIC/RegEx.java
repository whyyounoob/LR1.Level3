package by.company.LOGIC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for working with regular expressions.
 * @author Maxim Borodin
 */

public class RegEx {

    /**
     * Method verifies the correctness of the entered email.
     * @param teststring - entered email
     * @return true - email correct, false - email incorrect
     */

    public boolean checkEmail(final String teststring) {
        Pattern p = Pattern.compile("(^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$)");
        Matcher m = p.matcher(teststring);
        return m.matches();
    }
}
