package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.kangmin.blog.formbean.FormHelper.isValidEmailAddress;

public class LoginForm {

    private final String userName;  // unique email address
    private final String password;
    private final String button;

    public LoginForm(final HttpServletRequest request) {
        userName = FormHelper.sanitize(request.getParameter("emailAddress"));
        password = FormHelper.sanitize(request.getParameter("password"));
        button = FormHelper.sanitize(request.getParameter("loginButton"));
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getButton() {
        return button;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<>();

        if (userName == null) {
            errors.add("" + ErrorMessage.EMAIL_IS_NULL);
        } else if (userName.trim().equals("")) {
            errors.add("" + ErrorMessage.EMAIL_IS_EMPTY);
        } else if (!isValidEmailAddress(userName)) {
            errors.add("" + ErrorMessage.EMAIL_IS_INVALID);
        }

        if (password == null) {
            errors.add("" + ErrorMessage.PASSWORD_IS_NULL);
        } else if (password.trim().equals("")) {
            errors.add("" + ErrorMessage.PASSWORD_IS_EMPTY);
        }

        if (button == null) {
            errors.add("" + ErrorMessage.BUTTON_IS_NULL);
        }

        // So far, if above errors show, return
        if (errors.size() > 0) {
            return errors;
        }

        // now we have all values, check further
        assert button != null;
        if (!button.equals("Login")) {
            errors.add("" + ErrorMessage.BUTTON_IS_INVALID);
        }
        assert userName != null;
        if (userName.matches(".*[<>\"].*")) {
            errors.add("" + ErrorMessage.USERNAME_ERROR);
        }

        return errors;
    }

    //*************************************************************************
    // enum Error Messages
    //*************************************************************************
    protected enum ErrorMessage {
        EMAIL_IS_NULL, EMAIL_IS_EMPTY, EMAIL_IS_INVALID, USERNAME_ERROR,
        PASSWORD_IS_NULL, PASSWORD_IS_EMPTY, BUTTON_IS_NULL, BUTTON_IS_INVALID;

        public String toString() {
            switch (this) {
                case EMAIL_IS_NULL:
                    return "Email Address is null/missing!";
                case EMAIL_IS_EMPTY:
                    return "Email Address is empty!";
                case EMAIL_IS_INVALID:
                    return "Email Address is invalid!";
                case PASSWORD_IS_NULL:
                    return "Password is null/missing!";
                case PASSWORD_IS_EMPTY:
                    return "Password is empty!";
                case BUTTON_IS_NULL:
                    return "Action is required, no Login button is hit";
                case BUTTON_IS_INVALID:
                    return "Invalid action, should be Login button";
                case USERNAME_ERROR:
                    return "User Name may not contain angle brackets or quotes";
                default:
                    return "Unknown error, keep debugging";
            }
        }
    }
}
