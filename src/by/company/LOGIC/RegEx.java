package by.company.LOGIC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public boolean checkEmail(String teststring){
        Pattern p = Pattern.compile("(^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$)");
        Matcher m = p.matcher(teststring);
        return m.matches();
    }
}
