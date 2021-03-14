package com.kangmin.blog.controller;

import com.kangmin.blog.formbean.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_ADMIN;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class LogoutAction extends Action {

    public LogoutAction() {

    }

    public String getName() {
        return "logout.do";
    }

    public String performGet(final HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.setAttribute(SESSION_ATTR_USER, null);
        session.setAttribute(SESSION_ATTR_ADMIN, null);
        request.setAttribute("form", new LoginForm(request));
        request.getSession().invalidate();
        return "login.do";
    }
}
