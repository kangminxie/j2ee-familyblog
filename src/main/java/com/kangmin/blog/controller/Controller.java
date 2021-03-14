package com.kangmin.blog.controller;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.model.Model;
import org.apache.log4j.Logger;
import org.genericdao.RollbackException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    public void init() {
        final Model model;
        try {
            model = new Model(getServletConfig());
            Action.add(new LoginAction(model));
            Action.add(new RegisterAction(model));
            Action.add(new LogoutAction());

            Action.add(new HomeAction(model));
            Action.add(new VisitorAction(model));

            Action.add(new AddPostAction(model));
            Action.add(new DeletePostAction(model));
            Action.add(new AddCommentAction(model));
            Action.add(new DeleteCommentAction(model));

            Action.add(new AdminLoginAction(model));
            Action.add(new AdminHomeAction(model));
            Action.add(new ChangePasswordAction(model));
            Action.add(new EditInformationAction(model));
            Action.add(new ResetPasswordAction(model));
        } catch (ServletException | RollbackException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

    }

    public void doGet(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");  //for Chinese char
        response.setCharacterEncoding("UTF-8");  //for Chinese char

        final String nextPage = performTheAction(request);
        LOG.debug(">>> nextPage is:" + nextPage);

        sendToNextPage(nextPage, request, response);
    }

    public void doPost(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws IOException, ServletException {
        doGet(request, response);
    }

    private String performTheAction(final HttpServletRequest request) {
        final String servletPath = request.getServletPath();
        LOG.debug("///////////////////////////////////////////");
        LOG.debug("The current servletPath is: " + servletPath);

        String action = getActionName(servletPath);
        LOG.debug("action is: " + action);

        final HttpSession session = request.getSession(true);
        final User user = (User) session.getAttribute(SESSION_ATTR_USER);
        if (user != null) {
            // Let the logged in user run his chosen action
            LOG.debug("user come back");
            return Action.perform(action, request);
        }

        final Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            // Let the logged in user run his chosen action
            LOG.debug("admin come back");
            return Action.perform(action, request);
        }

        // not logged-in user
        if (action.equals("login.do")
            || action.equals("changePassword.do")
            || action.equals("editInformation.do")
        ) {
            LOG.debug("will perform login.do, request");
            return Action.perform("login.do", request);
        }

        if (action.equals("adminLogin.do")) {
            LOG.debug("will perform admin Login.do, request");
            return Action.perform("adminLogin.do", request);
        }

        if (action.equals("adminHome.do")) {
            System.out.println("will perform admin Home.do, request");
            return Action.perform("adminHome.do", request);
        }

        if (action.equals("resetPassword.do")) {
            System.out.println("will perform admin Home.do, request");
            return Action.perform("adminHome.do", request);
        }

        if (action.equals("register.do")) {
            System.out.println("will perform register.do, request");
            return Action.perform("register.do", request);
        }

        if (action.contains("visitor.do")) {
            System.out.println("will perform visitor.do, request");
            return Action.perform("visitor.do", request);
        }

        if (action.equals("home.do") || action.equals("addPost.do")
            || action.equals("deletePost.do") || action.equals("logout.do")) {
            System.out.println("not log-in, can not go to home or add/deletePost, or even logout");
            return Action.perform("login.do", request);
        }

        if (action.equals("addComment.do")) {
            String s = "addComment.do";
            request.setAttribute("checkPath", s);
            System.out.println("not loggin, can not add Comment");
            return Action.perform("login.do", request);
        }

        if (action.equals("deleteComment.do")) {
            String s = "deleteComment.do";
            request.setAttribute("checkPath", s);
            System.out.println("not loggin, can not delete Comment");
            return Action.perform("login.do", request);
        }

        // not valid .do
        request.setAttribute("error", "Action error! Expected *.do as the entry to our servlet!");
        return "_errors.jsp";
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    private void sendToNextPage(
        final String nextPage,
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
            return;
        }

        if (nextPage.contains(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.contains(".jsp")) {
            final RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
            d.forward(request, response);
            System.out.println("d.forward to:" + "WEB-INF/" + nextPage);
            return;
        }

        throw new ServletException(Controller.class.getName()
            + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    private String getActionName(final String path) {
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1);
    }
}
