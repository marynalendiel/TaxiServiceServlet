package com.taxiservice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for validating data entered by user using regular expressions.
 *
 * @author Maryna Lendiel
 */
public class DataValidator {
    private static final String F_L_NAME_PATTERN = "([a-zA-Zа-яА-ЯёЁіІїЇґҐ ]+){2,}";
    private static final String LOGIN_PATTERN = "([a-zA-Z0-9_]+){5,}";
    private static final String PHONE_PATTERN = "0[0-9]{9}";
    private static final String EMAIL_PATTERN = "[\\w\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})";
    private static final String PASSWORD_PATTERN = "([a-zA-Z0-9_]+){8,}";

    //data validation
    public static boolean checkData(String firstName, String lastName, String login, String phoneNumber, String email, String password) {
        if (!patternMatcher(firstName, F_L_NAME_PATTERN)) {
            return false;
        }
        if (!patternMatcher(lastName, F_L_NAME_PATTERN)) {
            return false;
        }
        if (!patternMatcher(login, LOGIN_PATTERN)) {
            return false;
        }
        if (!patternMatcher(phoneNumber, PHONE_PATTERN)) {
            return false;
        }
        if (!patternMatcher(email, EMAIL_PATTERN)) {
            return false;
        }
        return patternMatcher(password, PASSWORD_PATTERN);
    }

    /**
     * Method that checks if input string matches corresponding pattern
     */
    private static boolean patternMatcher(String data, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);

        return matcher.matches();
    }
}
