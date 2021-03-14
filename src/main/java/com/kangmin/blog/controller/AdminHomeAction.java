package com.kangmin.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.model.AdminDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_ADMIN;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class AdminHomeAction extends Action {

    private final AdminDAO adminDAO;
    private final UserDAO userDAO;

    public AdminHomeAction(final Model model) {
        userDAO = model.getUserDAO();
        adminDAO = model.getAdminDAO();
    }

    public String getName() {
        return "adminHome.do";
    }

    public String performGet(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Admin admin = (Admin) session.getAttribute(SESSION_ATTR_ADMIN);
        final User sessionUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (admin == null && sessionUser == null) {
            return "adminLogin.do";
        } else if (admin == null) {
            session.setAttribute("user", null);
            return "adminLogin.do";
        }

        // now admin != null
        try {
            request.setAttribute("admins", adminDAO.getAdmins());
            request.setAttribute("users", userDAO.getUsers());
            request.setAttribute(REQ_ATTR_ERROR, request.getParameter("error"));  // will check
            return "adminHome.jsp";
        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "_errors.jsp";
        }
    }

    public String performPost(final HttpServletRequest request) {
        return performGet(request);
    }
}
