package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.kangmin.blog.formbean.FormHelper.isValidEmailAddress;

public class RegisterForm {

    private final String emailAddress;  // unique email address
    private final String password;
    private final String password2;
    private final String firstName;
    private final String lastName;
    private final String button;

    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipcode;
    private final String phoneNumber;

    public RegisterForm(final HttpServletRequest request) {
        emailAddress = FormHelper.sanitize(request.getParameter("emailAddress"));
        password = request.getParameter("password");
        password2 = request.getParameter("password2");
        firstName = FormHelper.sanitize(request.getParameter("firstName"));
        lastName = FormHelper.sanitize(request.getParameter("lastName"));
        button = FormHelper.sanitize(request.getParameter("registerButton"));

        addressLine1 = FormHelper.sanitize(request.getParameter("addressLine1"));
        addressLine2 = FormHelper.sanitize(request.getParameter("addressLine2"));
        city = FormHelper.sanitize(request.getParameter("city"));
        state = FormHelper.sanitize(request.getParameter("state"));
        country = FormHelper.sanitize(request.getParameter("country"));
        zipcode = FormHelper.sanitize(request.getParameter("zipcode"));
        phoneNumber = FormHelper.sanitize(request.getParameter("phoneNumber"));
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getButton() {
        return button;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        // no attribute or had been modified, or empty
        if (emailAddress == null) {
            errors.add("" + ErrorMessage.EMAIL_IS_NULL);
        } else if (emailAddress.trim().equals("")) {
            errors.add("" + ErrorMessage.EMAIL_IS_EMPTY);
        } else if (!isValidEmailAddress(emailAddress)) {
            errors.add("" + ErrorMessage.EMAIL_IS_INVALID);
        }

        if (password == null) {
            errors.add("" + ErrorMessage.PASSWORD_IS_NULL);
        } else if (password.trim().equals("")) {
            errors.add("" + ErrorMessage.PASSWORD_IS_EMPTY);
        }

        if (password2 == null) {
            errors.add("" + ErrorMessage.PASSWORD2_IS_NULL);
        } else if (password2.trim().equals("")) {
            errors.add("" + ErrorMessage.PASSWORD2_IS_EMPTY);
        }
        assert password != null;
        if (!password.equals(password2)) {
            errors.add("" + ErrorMessage.PASSWORD_NOT_MATCH);
        }

        if (firstName == null) {
            errors.add("" + ErrorMessage.FNAME_IS_NULL);
        } else if (firstName.trim().equals("")) {
            errors.add("" + ErrorMessage.FNAME_IS_EMPTY);
        }

        if (lastName == null) {
            errors.add("" + ErrorMessage.LNAME_IS_NULL);
        } else if (lastName.trim().equals("")) {
            errors.add("" + ErrorMessage.LNAME_IS_EMPTY);
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
        if (!button.equals("Register")) {
            errors.add("" + ErrorMessage.BUTTON_IS_INVALID);
        }
        assert emailAddress != null;
        if (emailAddress.matches(".*[<>\"].*")) {
            errors.add("" + ErrorMessage.USERNAME_ERROR);
        }
        assert firstName != null;
        if (firstName.matches(".*[<>\"].*")) {
            errors.add("" + ErrorMessage.FNAME_IS_INVALID);
        }
        assert lastName != null;
        if (lastName.matches(".*[<>\"].*")) {
            errors.add("" + ErrorMessage.LNAME_IS_INVALID);
        }

        return errors;
    }

    //*************************************************************************
    // enum Error Messages
    //*************************************************************************
    protected enum ErrorMessage {
        EMAIL_IS_NULL, EMAIL_IS_EMPTY, EMAIL_IS_INVALID, USERNAME_ERROR,
        FNAME_IS_NULL, FNAME_IS_EMPTY, FNAME_IS_INVALID,
        LNAME_IS_NULL, LNAME_IS_EMPTY, LNAME_IS_INVALID,
        PASSWORD_IS_NULL, PASSWORD_IS_EMPTY,
        PASSWORD2_IS_NULL, PASSWORD2_IS_EMPTY, PASSWORD_NOT_MATCH,
        BUTTON_IS_NULL, BUTTON_IS_INVALID;

        public String toString() {
            switch (this) {
                case EMAIL_IS_NULL:
                    return "Email address is null/missing!";
                case EMAIL_IS_EMPTY:
                    return "Email address is empty!";
                case EMAIL_IS_INVALID:
                    return "Email adress is invalid!";
                case FNAME_IS_NULL:
                    return "First name is null/missing!";
                case FNAME_IS_EMPTY:
                    return "First name is empty!";
                case FNAME_IS_INVALID:
                    return "First name is invalid!";
                case LNAME_IS_NULL:
                    return "Last name is null/missing!";
                case LNAME_IS_EMPTY:
                    return "Last name is empty!";
                case LNAME_IS_INVALID:
                    return "Last name is invalid!";
                case PASSWORD_IS_NULL:
                    return "Password is null/missing!";
                case PASSWORD_IS_EMPTY:
                    return "Password is empty!";
                case PASSWORD2_IS_NULL:
                    return "Password confirm is null/missing!";
                case PASSWORD2_IS_EMPTY:
                    return "Password confirm is empty!";
                case PASSWORD_NOT_MATCH:
                    return "Password does not match!";
                case BUTTON_IS_NULL:
                    return "Action is required, no Register button is hit";
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
