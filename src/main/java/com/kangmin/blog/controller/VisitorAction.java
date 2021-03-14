package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

public class VisitorAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public VisitorAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "visitor.do";
    }

    public String performGet(final HttpServletRequest request) {
        try {
            final String email = request.getParameter("email");
            final User entry = userDAO.read(email);
            final Post[] entryPosts = postDAO.getUserPosts(entry.getUserName());
            final Comment[] allComments = commentDAO.getAllComments();

            final Map<Integer, Integer> entryPostsIdMap = new HashMap<Integer, Integer>();
            if (entryPosts != null) {
                for (Post p : entryPosts) {
                    entryPostsIdMap.put(p.getId(), p.getId());
                }
            }

            final List<Comment> comments = new ArrayList<>();
            for (Comment cmt : allComments) {
                if (entryPostsIdMap.containsKey(cmt.getReplyTo())) {
                    comments.add(cmt);
                }
            }

            request.setAttribute("entry", entry);
            request.setAttribute("users", userDAO.getUsers());
            request.setAttribute("posts", entryPosts);
            request.setAttribute("filteredComments", comments);
            return ("visitor.jsp");
        } catch (final RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "_errors.jsp";
        }
    }

    public String performPost(final HttpServletRequest request) {
        return performGet(request);
    }
}
