package com.kangmin.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.SHA;
import com.kangmin.blog.model.AdminDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class ResetPasswordAction extends Action {

    private final UserDAO userDAO;
    private final AdminDAO adminDAO;

    public ResetPasswordAction(final Model model) {
        userDAO = model.getUserDAO();
        adminDAO = model.getAdminDAO();
    }

    @Override
    public String getName() {
        return "resetPassword.do";
    }

    public String performGet(final HttpServletRequest request) {
        return this.performPost(request);
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            return "login.do";
        }

        String userName = request.getParameter("userName");
        if (userName != null) {
            userName = userName.trim().toLowerCase();
        }

        String adminName = request.getParameter("adminName");
        if (adminName != null) {
            adminName = adminName.trim().toLowerCase();
        }

        if (userName != null && adminName == null) {
            //reset the password of user
            try {
                Transaction.begin();
                final User user = userDAO.read(userName);
                user.setPassword(SHA.getResult("family2021"));
                userDAO.update(user);
                Transaction.commit();
                request.setAttribute("remind", "Password is successfully rest for " + userName);
            } catch (final RollbackException e) {
                e.printStackTrace();
            }

        } else if (userName == null && adminName != null) {
            //reset the password of admin
            try {
                Transaction.begin();
                final Admin admin = adminDAO.read(adminName);
                admin.setPassword(SHA.getResult("5523"));
                adminDAO.update(admin);
                Transaction.commit();
                request.setAttribute("remind", "Password is successfully rest for " + adminName);
            } catch (RollbackException e) {
                e.printStackTrace();
            }
        }
        try {
            request.setAttribute("admins", adminDAO.getAdmins());
            request.setAttribute("users", userDAO.getUsers());
        } catch (final RollbackException e) {
            e.printStackTrace();
        }
        return "adminHome.jsp";
    }
}
