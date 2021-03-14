package com.kangmin.blog.formbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FormHelper {

    //*************************************************************************
    // deal with the input is HTML contents like  <a href = "" /a>
    //*************************************************************************
    /**
     * Help method to validate the inputs. Borrow from lecture sample
     * @param s the String to be checked
     * @return String sanitized
     */
    static String sanitize(final String s) {
        if (s == null) {
            return null;
        }
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }

    public static boolean isValidEmailAddress(final String email) {
        Pattern pt = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher mr = pt.matcher(email.toLowerCase());
        return mr.matches();
    }

    private FormHelper() {

    }
}
