package com.kangmin.blog.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.RegisterForm;
import com.kangmin.blog.formbean.SHA;
import com.kangmin.blog.model.AdminDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class RegisterAction extends Action {

    private final UserDAO userDAO; // instance variable
    private final AdminDAO adminDAO;

    public RegisterAction(final Model model) {
        userDAO = model.getUserDAO();
        adminDAO = model.getAdminDAO();
    }

    public String getName() {
        return "register.do";
    }

    public String performGet(final HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
        final HttpSession session = request.getSession();
        request.setAttribute("form", new RegisterForm(request));
        if (session.getAttribute("user") != null) {
            return "home.do";
        }

        // Otherwise, just display the login page.
        try {
            request.setAttribute("users", userDAO.getUsers());
        } catch (final RollbackException e) {
            e.printStackTrace();
        }
        return "register.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
        final HttpSession session = request.getSession();
        request.setAttribute("form", new RegisterForm(request));
        if (session.getAttribute("user") != null) {
            return "home.do";
        }

        try {
            request.setAttribute("users", userDAO.getUsers());
        } catch (final RollbackException e) {
           e.printStackTrace();
            return "home.do";
        }

        // the current session has no user, will be here for login
        final RegisterForm form = new RegisterForm(request);
        request.setAttribute("form", form);
        final List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        errors.addAll(form.getValidationErrors());

        // if there is at least one input error in the form
        if (errors.size() != 0) {
            return "register.jsp";
        }

        // no error from the form now, good
        try {
            if (form.getButton().trim().equals("Register")) {
                // if the Register Button is really hit
                final User userTest = userDAO.read(form.getEmailAddress());
                if (userTest != null) {
                    errors.add("Such userName is already in our system, please try another email");
                    return "register.jsp";
                }
                final Admin adminTest = adminDAO.read(form.getEmailAddress());
                if (adminTest != null) {
                    errors.add("Such userName is already in our system, please try another email");
                    return "register.jsp";
                }

                final User user = new User();
                user.setUserName(form.getEmailAddress().toLowerCase());
                user.setPassword(SHA.getResult(form.getPassword()));
                user.setFirstName(form.getFirstName());
                user.setLastName(form.getLastName());

                user.setAddressLine1(form.getAddressLine1());
                user.setAddressLine2(form.getAddressLine2());
                user.setCity(form.getCity());
                user.setState(form.getState());
                user.setCountry(form.getCountry());
                user.setZipcode(form.getZipcode());
                user.setPhoneNumber(form.getPhoneNumber());
                user.setEnrollDate(new Date(System.currentTimeMillis()));

                userDAO.create(user);
                session.setAttribute(SESSION_ATTR_USER, user);
                return "home.do";
            } else {
                // Something was wrong, such as not hit the Login button
                return "register.jsp";
            }

        } catch (final RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        }
    }
}
