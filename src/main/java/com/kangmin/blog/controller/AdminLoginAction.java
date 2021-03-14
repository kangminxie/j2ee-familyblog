package com.kangmin.blog.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.formbean.AdminLoginForm;
import com.kangmin.blog.formbean.SHA;
import com.kangmin.blog.model.AdminDAO;
import com.kangmin.blog.model.Model;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_ADMIN;

public class AdminLoginAction extends Action {

    private final AdminDAO adminDAO;

    public AdminLoginAction(final Model model) {
        adminDAO = model.getAdminDAO();
    }

    public String getName() {
        return "adminLogin.do";
    }

    public String performGet(final HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ATTR_ADMIN) != null) {
            return "adminHome.do";
        }

        try {
            request.setAttribute("admins", adminDAO.getAdmins());
        } catch (final RollbackException e) {
            e.printStackTrace();
        }

        return "adminLogin.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final HttpSession session = request.getSession();
        if (session.getAttribute("admin") != null) {
            return "adminHome.do";
        }

        try {
            request.setAttribute(SESSION_ATTR_ADMIN, adminDAO.getAdmins());
        } catch (final RollbackException e) {
            e.printStackTrace();
        }

        final AdminLoginForm form = new AdminLoginForm(request);
        request.setAttribute("form", form);

        final List<String> errors = new ArrayList<>(form.getValidationErrors());
        request.setAttribute("errors", errors);

        // POST method now, validation for login-form first
        if (errors.size() != 0) {
            // if there is at least one input error in the form
            return "adminLogin.jsp";
        }

        // no error from the form now, good
        if (form.getButton().trim().equals("adminLogin")) {
            // if the Login Button is really hit, login choice now
            final Admin admin;
            try {
                admin = adminDAO.read(form.getAdminName().toLowerCase());
            } catch (final RollbackException e) {
                errors.add(e.getMessage());
                return "adminLogin.jsp";
            }

            // user is NOT in the userDAO, re-do-login
            if (admin == null) {
                errors.add("No such admin in our database");
                return "adminLogin.jsp";
            }

            // user is in the userDAO, check credentials
            // admin user regular password
            if (!SHA.getResult(form.getPassword()).equals(admin.getPassword())) {
                errors.add("Incorrect password, please try again");
                return "adminLogin.jsp";
            }

            // successfully login in, finally
            session.setAttribute(SESSION_ATTR_ADMIN, admin);
            return "adminHome.do";
        } else {
            errors.add("Action is a question, no Login button was hit");
            return "adminLogin.jsp";
        }
    }
}
