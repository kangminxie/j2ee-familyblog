package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.AddPostForm;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.CommentsUtil.getFilteredComments;
import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;

public class AddPostAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public AddPostAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "addPost.do";
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            System.out.println("actually not logged in, can not perform AddCommentAction");
            return "login.do";
        }

        try {
            final List<String> errors = new ArrayList<>();
            request.setAttribute("errors", errors);

            final AddPostForm form = new AddPostForm(request);
            request.setAttribute("form", form);
            errors.addAll(form.getValidationErrors());

            request.setAttribute("users", userDAO.getUsers());
            final Comment[] allComments = commentDAO.getAllComments();

            if (errors.size() != 0) {
                final Post[] userPosts = postDAO.getUserPosts(sessionUser.getUserName());
                final List<Comment> comments = getFilteredComments(userPosts, allComments);
                request.setAttribute("posts", userPosts);
                request.setAttribute("filteredComments", comments);
                return "home.jsp";
            }

            if (form.getAction().equals("Post")) {
                // already make sure form.getAction is not null
                final Post p = new Post();
                p.setDate(new Date());
                p.setContent(form.getItem());
                p.setUserEmail(((User) request.getSession().getAttribute("user")).getUserName());
                postDAO.addPost(p);
                request.setAttribute("form", new AddPostForm(request));
                return "home.do";
            } else {
                request.setAttribute(REQ_ATTR_ERROR, "Not hit Post, or being hacked");
                return "error.jsp";
            }
        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "error.jsp";
        }
    }
}
