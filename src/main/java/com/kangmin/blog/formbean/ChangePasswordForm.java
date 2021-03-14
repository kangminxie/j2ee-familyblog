package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordForm {

    private final String password;
    private final String newPassword;
    private final String newPassword2;
    private final String button;

    public ChangePasswordForm(final HttpServletRequest request) {
        password = FormHelper.sanitize(request.getParameter("password"));
        newPassword = FormHelper.sanitize(request.getParameter("newPassword"));
        newPassword2 = FormHelper.sanitize(request.getParameter("newPassword2"));
        button = FormHelper.sanitize(request.getParameter("changePasswordButton"));
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public String getButton() {
        return button;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<String>();

        if (password == null) {
            errors.add("" + ErrorMessage.PASSWORD_IS_NULL);
        } else if (password.trim().equals("")) {
            errors.add("" + ErrorMessage.PASSWORD_IS_EMPTY);
        }

        if (newPassword == null) {
            errors.add("" + ErrorMessage.NEW_PASSWORD_IS_NULL);
        } else if (newPassword.trim().equals("")) {
            errors.add("" + ErrorMessage.NEW_PASSWORD_IS_EMPTY);
        }

        if (newPassword2 == null) {
            errors.add("" + ErrorMessage.NEW_PASSWORD2_IS_NULL);
        } else if (newPassword2.trim().equals("")) {
            errors.add("" + ErrorMessage.NEW_PASSWORD2_IS_EMPTY);
        }

        assert newPassword != null;
        if (!newPassword.equals(newPassword2)) {
            errors.add("" + ErrorMessage.PASSWORD_NOT_MATCH);
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
        if (!button.equals("changePassword")) {
            errors.add("" + ErrorMessage.BUTTON_IS_INVALID);
        }
        return errors;
    }

    //*************************************************************************
    // enum Error Messages
    //*************************************************************************
    protected enum ErrorMessage {
        PASSWORD_IS_NULL, PASSWORD_IS_EMPTY, NEW_PASSWORD_IS_NULL, NEW_PASSWORD_IS_EMPTY,
        NEW_PASSWORD2_IS_NULL, NEW_PASSWORD2_IS_EMPTY, PASSWORD_NOT_MATCH,
        BUTTON_IS_NULL, BUTTON_IS_INVALID;

        public String toString() {
            switch (this) {
                case PASSWORD_IS_NULL:
                    return "Password is null/missing!";
                case PASSWORD_IS_EMPTY:
                    return "Password is empty!";
                case NEW_PASSWORD_IS_NULL:
                    return "New Password is null/missing!";
                case NEW_PASSWORD_IS_EMPTY:
                    return "New Password is empty!";
                case NEW_PASSWORD2_IS_NULL:
                    return "New Password Confirm is null/missing!";
                case NEW_PASSWORD2_IS_EMPTY:
                    return "New Password Confirm is empty!";
                case PASSWORD_NOT_MATCH:
                    return "Password does not match!";
                case BUTTON_IS_NULL:
                    return "Action is required, no Login button is hit";
                case BUTTON_IS_INVALID:
                    return "Invalid action, should be Login button";
                default:
                    return "Unknown error, keep debugging";
            }
        }
    }
}
