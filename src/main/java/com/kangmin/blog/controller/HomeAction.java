package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class HomeAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public HomeAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "home.do";
    }

    public String performGet(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute(SESSION_ATTR_USER);
        if (user == null) {
            return "login.do";
        }

        try {
            final Post[] userPosts = postDAO.getUserPosts(user.getUserName());
            final Comment[] allComments = commentDAO.getAllComments();

            final Map<Integer, Integer> userPostsIdMap = new HashMap<>();
            if (userPosts != null) {
                for (final Post p : userPosts) {
                    userPostsIdMap.put(p.getId(), p.getId());
                }
            }

            final List<Comment> filteredCommons = new ArrayList<>();
            if (allComments != null) {
                for (final Comment cmt : allComments) {
                    if (userPostsIdMap.containsKey(cmt.getReplyTo())) {
                        filteredCommons.add(cmt);
                    }
                }
            }

            request.setAttribute(REQ_ATTR_ERROR, request.getParameter("error"));  // will check
            request.setAttribute("users", userDAO.getUsers());
            request.setAttribute("posts", userPosts);
            request.setAttribute("filteredComments", filteredCommons);

            return "home.jsp";
        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "_errors.jsp";
        }
    }

    public String performPost(final HttpServletRequest request) {
        return performGet(request);
    }
}
