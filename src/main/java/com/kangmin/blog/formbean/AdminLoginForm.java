package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AdminLoginForm {

    private final String adminName;  // unique email address
    private final String password;
    private final String button;

    public AdminLoginForm(final HttpServletRequest request) {
        adminName = FormHelper.sanitize(request.getParameter("adminName"));
        password = FormHelper.sanitize(request.getParameter("password"));
        button = FormHelper.sanitize(request.getParameter("loginButton"));
    }

    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public String getButton() {
        return button;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<>();

        if (adminName == null) {
            errors.add("" + ErrorMessage.ADMIN_IS_NULL);
        } else if (adminName.trim().equals("")) {
            errors.add("" + ErrorMessage.ADMIN_IS_EMPTY);
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
        if (!button.equals("adminLogin")) {
            errors.add("" + ErrorMessage.BUTTON_IS_INVALID);
        }
        assert adminName != null;
        if (adminName.matches(".*[<>\"].*")) {
            errors.add("" + ErrorMessage.ADMIN_NAME_ERROR);
        }

        return errors;
    }

    //*************************************************************************
    // enum Error Messages
    //*************************************************************************
    protected enum ErrorMessage {
        ADMIN_IS_NULL, ADMIN_IS_EMPTY, ADMIN_NAME_ERROR,
        PASSWORD_IS_NULL, PASSWORD_IS_EMPTY, BUTTON_IS_NULL, BUTTON_IS_INVALID;

        public String toString() {
            switch (this) {
                case ADMIN_IS_NULL:
                    return "Admin Name is null/missing!";
                case ADMIN_IS_EMPTY:
                    return "Admin Name is empty!";
                case PASSWORD_IS_NULL:
                    return "Password is null/missing!";
                case PASSWORD_IS_EMPTY:
                    return "Password is empty!";
                case BUTTON_IS_NULL:
                    return "Action is required, no Login button is hit";
                case BUTTON_IS_INVALID:
                    return "Invalid action, should be Login button";
                case ADMIN_NAME_ERROR:
                    return "Admin Name may not contain angle brackets or quotes";
                default:
                    return "Unknown error, keep debugging";
            }
        }
    }
}
