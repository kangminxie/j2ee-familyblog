package com.kangmin.blog.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.LoginForm;
import com.kangmin.blog.formbean.SHA;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.apache.log4j.Logger;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.Constants.REQ_ATTR_FORM;
import static com.kangmin.blog.util.Constants.REQ_ATTR_USERS;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class LoginAction extends Action {

    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    private final UserDAO userDAO;

    public LoginAction(final Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String performGet(final HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        LOG.debug("Login-Action GET here start");
        final HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ATTR_USER) != null) {
            // if the user is already logged in, redirect to home.do
            return "home.do";
        }

        // Otherwise, just display the login page
        try {
            // get all Users for output list to show
            request.setAttribute(REQ_ATTR_USERS, userDAO.getUsers());
        } catch (final RollbackException e) {
            e.printStackTrace();
        }

        request.setAttribute(REQ_ATTR_FORM, new LoginForm(request));
        return "login.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (final UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // If user is already logged in, redirect to home.do
        final HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ATTR_USER) != null) {
            return "home.do";
        }

        try {
            request.setAttribute("users", userDAO.getUsers());
        } catch (final RollbackException e1) {
            e1.printStackTrace();
        }

        final String thePath = (String) request.getAttribute("checkPath");
        if (thePath != null && thePath.equals("addComment.do")) {
            LOG.debug("From addComment, remind go to login.jsp");
            return "login.jsp";
        }

        LOG.debug("LoginAction POST user is null, go ahead");
        // the current session has no user, will be here for login

        final LoginForm form = new LoginForm(request);
        request.setAttribute(REQ_ATTR_FORM, form);

        final List<String> errors = new ArrayList<>(form.getValidationErrors());
        request.setAttribute("errors", errors);

        // POST method now, validation for login-form first
        if (errors.size() != 0) {  // if there is at least one input error in the form
            return "login.jsp";
        }

        // no error from the form now, good
        if (form.getButton().trim().equals("Login")) {
            // if the Login Button is really hit, login choice now
            User user;
            try {
                user = userDAO.read(form.getUserName().toLowerCase());
            } catch (final RollbackException e) {
                errors.add(e.getMessage());
                return "login.jsp";
            }

            // user is NOT in the userDAO, re-do-login
            if (user == null) {
                errors.add("No such user in our database");
                return "login.jsp";
            }

            // user is in the userDAO, check credentials
            if (!SHA.getResult(form.getPassword()).equals(user.getPassword())) {
                errors.add("Incorrect password, please try again");
                return "login.jsp";
            }

            // successfully login in, finally
            session.setAttribute(SESSION_ATTR_USER, user);
            return "home.do";
        } else {
            // Something was wrong // Not hit the Login button
            errors.add("Action is a question, no Login button was hit");
            return "login.jsp";
        }
    }
}
