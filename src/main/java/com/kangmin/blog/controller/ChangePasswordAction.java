package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.ChangePasswordForm;
import com.kangmin.blog.formbean.SHA;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_ADMIN;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class ChangePasswordAction extends Action {

    private final UserDAO userDAO;

    public ChangePasswordAction(final Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "changePassword.do";
    }

    public String performGet(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Admin admin = (Admin) session.getAttribute(SESSION_ATTR_ADMIN);
        final User user = (User) session.getAttribute(SESSION_ATTR_USER);
        if (user == null && admin == null) {
            return "login.do";
        } else if (user == null) {
            // admin will not be able to change password here
            return "adminHome.do";
        }
        // user is here, not an admin, render the jsp form
        return "changePassword.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Admin admin = (Admin) session.getAttribute(SESSION_ATTR_ADMIN);
        final User user = (User) session.getAttribute(SESSION_ATTR_USER);
        if (user == null && admin == null) {
            return "login.do";
        } else if (user == null) {
            // admin will not be able to change password here
            return "adminHome.do";
        }

        final ChangePasswordForm form = new ChangePasswordForm(request);
        request.setAttribute("form", form);

        final List<String> errors = new ArrayList<>(form.getValidationErrors());
        request.setAttribute("errors", errors);
        if (errors.size() != 0) {
            return "changePassword.jsp";
        }

        if (form.getButton().trim().equals("changePassword")) {
            // if the Login Button is really hit, login choice now
            if (!user.getPassword().equals(SHA.getResult(form.getPassword()))) {
                errors.add("Current Password typed in is not right");
                return "changePassword.jsp";
            }

            user.setPassword(SHA.getResult(form.getNewPassword()));
            try {
                Transaction.begin();
                userDAO.update(user);
                Transaction.commit();
            } catch (final RollbackException e) {
                e.printStackTrace();
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }

            // successfully changed
            session.setAttribute(SESSION_ATTR_USER, user);
            request.setAttribute("remind", "Password Successfully Changed");
            return "changePassword.jsp";
        } else {
            // Something was wrong // Not hit the Login button
            errors.add("Action is a question, no changePassword button was hit");
            return "changePassword.jsp";
        }
    }
}
