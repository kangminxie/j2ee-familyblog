package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.DeletePostForm;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.CommentsUtil.getCommentsFromOnePost;
import static com.kangmin.blog.util.CommentsUtil.getFilteredComments;
import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;

public class DeletePostAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public DeletePostAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "deletePost.do";
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            // in case of null-pointer error, since only login user can come
            return "login.do";
        }
        try {
            final List<String> errors = new ArrayList<>();
            request.setAttribute("errors", errors);
            final DeletePostForm form = new DeletePostForm(request);
            request.setAttribute("form", form);
            errors.addAll(form.getValidationErrors());
            request.setAttribute("users", userDAO.getUsers());

            final Comment[] allComments = commentDAO.getAllComments();
            if (errors.size() != 0) {
                final Post[] userPosts = postDAO.getUserPosts(user.getUserName());
                final List<Comment> comments = getFilteredComments(userPosts, allComments);
                request.setAttribute("posts", userPosts);
                request.setAttribute("filteredComments", comments);
                return "home.jsp";
            }

            final String testEmail = postDAO.getPostAuthorEmail(form.getIdAsInt());
            final String homeUserEmail = ((User) request.getSession().getAttribute("user")).getUserName();
            // if the post exist && it is the author's post
            if (testEmail != null && testEmail.trim().equals(homeUserEmail)) {
                final Post thisPost = postDAO.getUserPost(form.getIdAsInt());
                // remove all the comments from that post first, to clean the database
                final List<Comment> filteredCommsForThisPost = getCommentsFromOnePost(thisPost, allComments);
                for (final Comment c : filteredCommsForThisPost) {
                    commentDAO.removeComment(c.getId());
                }
                postDAO.removePost(form.getIdAsInt());
                request.setAttribute("form", new DeletePostForm(request));
                return "home.do";
            } else {
                if (testEmail == null) {  // the post does not exist
                    errors.add("You can NOT delete a post NOT exist now! \n"
                        + "Either the post has been deleted already, or "
                        + "someone changed the postId to non-exist one in hidden field, hacked");
                } else {
                    errors.add("You can NOT delete other person's Post, no authority!");
                }

                final Post[] userPosts = postDAO.getUserPosts(user.getUserName());
                final List<Comment> comments = getFilteredComments(userPosts, allComments);
                request.setAttribute("posts", userPosts);
                request.setAttribute("filteredComments", comments);
                return "home.jsp";
            }

        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "error.jsp";
        }
    }
}
