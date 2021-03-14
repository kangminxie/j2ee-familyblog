package com.kangmin.blog.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

public class Model {

    private final UserDAO userDAO;
    private final AdminDAO adminDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public Model(final ServletConfig config) throws ServletException, RollbackException {
        try {
            final String jdbcDriverName = config.getInitParameter("jdbcDriverName");
            final String jdbcURL = config.getInitParameter("jdbcURL");
            final String jdbcUser = config.getInitParameter("jdbcUser");
            final String jdbcPassword = config.getInitParameter("jdbcPassword");
            final ConnectionPool pool;
            if (jdbcUser == null || jdbcUser.trim().isEmpty()) {
                pool = new ConnectionPool(jdbcDriverName, jdbcURL, "", "");
            } else {
                pool = new ConnectionPool(jdbcDriverName, jdbcURL, jdbcUser, jdbcPassword);
            }

            userDAO = new UserDAO(pool, "blog_user");
            adminDAO = new AdminDAO(pool, "blog_admin");
            postDAO = new PostDAO(pool, "blog_post");
            commentDAO = new CommentDAO(pool, "blog_comment");
        } catch (final DAOException e) {
            throw new ServletException(e);
        }
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }
}
