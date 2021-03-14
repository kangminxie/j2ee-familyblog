package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.AddCommentForm;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.CommentsUtil.getFilteredComments;
import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;

public class AddCommentAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public AddCommentAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "addComment.do";
    }

    public String performGet(final HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            // in case of null-pointer error, since only login user can come
            System.out.println("actually not logged in, can not perform AddCommentAction");
            return "login.do";
        }
        try {
            final AddCommentForm form = new AddCommentForm(request);
            final List<String> errors = new ArrayList<>(form.getValidationErrors());
            request.setAttribute("errors", errors);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());

            final Comment[] allComments = commentDAO.getAllComments();
            if (errors.size() != 0) {
                final Post targetPost = postDAO.read(form.getToIdAsInt());  // from comment's replyToId
                if (targetPost == null) {
                    request.setAttribute(REQ_ATTR_ERROR, "You can not add comment to a post does not exist!\n"
                        + "Either the post has been deleted already, or "
                        + "Someone must have changed the postId to non-exist one in hidden field, hacked");
                    return "error.jsp";
                }

                final User entry = userDAO.read(targetPost.getUserEmail());
                final Post[] entryPosts = postDAO.getUserPosts(entry.getUserName());
                final List<Comment> comments = getFilteredComments(entryPosts, allComments);
                request.setAttribute("entry", entry);
                request.setAttribute("posts", entryPosts);
                request.setAttribute("filteredComments", comments);
                return ("visitor.jsp");
            }

            // even addCommentForm has no error, need to see if the itTo Post exist or not
            final Post p = postDAO.read(form.getToIdAsInt());
            if (p == null) {
                System.out.println("Someone changed the postId to non-exist one...");
                request.setAttribute(REQ_ATTR_ERROR, "You can not add comment to a post does not exist!\n"
                    + "Either the post has been deleted already, or "
                    + "someone must have changed the postId to non-exist one in hidden field, hacked");
                return "error.jsp";
            }

            if (form.getAction().equals("Comment")) {
                final Comment c = new Comment();
                c.setDate(new Date());
                c.setContent(form.getItem());
                c.setReplyTo(form.getToIdAsInt());
                c.setByWho(sessionUser.getFirstName() + " " + sessionUser.getLastName());
                c.setByEmail(sessionUser.getUserName());
                commentDAO.addComment(c);
                request.setAttribute("allComments", commentDAO.getAllComments());
                return "visitor.do?email=" + p.getUserEmail();
            } else {
                request.setAttribute(REQ_ATTR_ERROR, "Not hit Comment, button value changed, or being hacked");
                return "error.jsp";
            }

        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "error.jsp";
        }
    }
}
