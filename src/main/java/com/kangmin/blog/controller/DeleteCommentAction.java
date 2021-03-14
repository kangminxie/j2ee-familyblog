package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.DeleteCommentForm;
import com.kangmin.blog.model.CommentDAO;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.PostDAO;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;

import static com.kangmin.blog.util.CommentsUtil.getFilteredComments;
import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;

public class DeleteCommentAction extends Action {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    public DeleteCommentAction(final Model model) {
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
        commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "deleteComment.do";
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final User user = (User) session.getAttribute("user");

        if (user == null) {
            // deal with the time out case
            // also in case of null-pointer error, since only login user can come
            return "login.do";
        }

        try {
            final DeleteCommentForm form = new DeleteCommentForm(request);
            request.setAttribute("form", form);
            final List<String> errors = new ArrayList<>(form.getValidationErrors());
            request.setAttribute("errors", errors);

            request.setAttribute("users", userDAO.getUsers());  // always need
            final Comment[] allComments = commentDAO.getAllComments();

            // here comments should exist so allComments.length > 1
            System.out.println("all Comments count:" + allComments.length);

            if (errors.size() != 0) {
                if (form.getSource() != null && form.getSource().equals("home")) {
                    // if we are delete from home.jsp, back home.jsp
                    final Post[] userPosts = postDAO.getUserPosts(user.getUserName());
                    final List<Comment> comments = getFilteredComments(userPosts, allComments);
                    request.setAttribute("posts", userPosts);
                    request.setAttribute("filteredComments", comments);
                    return "home.jsp";
                }

                // now we are delete from visitor.jsp, back visitor.jsp
                final Comment c = commentDAO.getUserComment(form.getIdAsInt());
                final Post targetP;
                if (c != null) {
                    targetP = postDAO.getUserPost(c.getReplyTo());
                } else {
                    // This comment does not exist, will have to user reply id to back
                    targetP = postDAO.getUserPost(form.getToIdAsInt());
                }

                final User entry = userDAO.read(targetP.getUserEmail());
                final Post[] entryPosts = postDAO.getUserPosts(entry.getUserName());
                final List<Comment> comments = getFilteredComments(entryPosts, allComments);
                request.setAttribute("entry", entry);
                request.setAttribute("posts", entryPosts);
                request.setAttribute("filteredComments", comments);
                return "visitor.jsp";
            }

            // it looks no error in the form
            final String homeUserEmail = ((User) request.getSession().getAttribute("user")).getUserName();
            if (form.getSource().equals("home")) {
                System.out.println("Delete comment from home...");
                // at home, can delete everything comment under self's post at home
                // in other words, the comment to be deleted has a toId that belongs to home
                final Comment c = commentDAO.getUserComment(form.getIdAsInt());
                if (c == null) {
                    System.out.println("c is null, cid is: null");
                    request.setAttribute(REQ_ATTR_ERROR, "You can NOT delete a comment that NOT exist, hacked");
                    return "blogMasterError.jsp";
                }

                // now the cid should exist
                final String relyToPostOwnerEmail = postDAO.getPostAuthorEmail(c.getReplyTo()); //replyToId, get a userName
                if (relyToPostOwnerEmail != null && relyToPostOwnerEmail.equals(homeUserEmail)) {
                    commentDAO.removeComment(form.getIdAsInt());
                    return "home.do";
                } else {
                    errors.add("You can NOT delete other's comment that NOT reply To your post");
                    final Post[] userPosts = postDAO.getUserPosts(user.getUserName());
                    final List<Comment> comments = getFilteredComments(userPosts, allComments);
                    request.setAttribute("posts", userPosts);
                    request.setAttribute("filteredComments", comments);
                    return "home.jsp";
                }
            } else {
                // at visitor page, can only delete self's comment
                final String commentUserEmail = commentDAO.getCommentAuthorEmail(form.getIdAsInt());
                if (commentUserEmail != null && commentUserEmail.trim().equals(homeUserEmail)) {
                    final Comment c = commentDAO.getUserComment(form.getIdAsInt());
                    final Post p = postDAO.read(c.getReplyTo());
                    commentDAO.delete(form.getIdAsInt());
                    return "visitor.do?email=" + p.getUserEmail();
                } else {
                    final Comment c = commentDAO.getUserComment(form.getIdAsInt());
                    if (c == null) {
                        request.setAttribute(REQ_ATTR_ERROR, "You can NOT delete a comment that NOT exist\n"
                            + "Either the comment has been deleted already, or "
                            + "being hacked, someone changed the comment id");
                        return "error.jsp";
                    }
                    errors.add("You can NOT delete other person's comment in visitor page!");

                    final Post toP = postDAO.read(form.getToIdAsInt());
                    final User entry = userDAO.read(toP.getUserEmail());
                    final Post[] entryPosts = postDAO.getUserPosts(entry.getUserName());
                    final List<Comment> comments = getFilteredComments(entryPosts, allComments);
                    request.setAttribute("entry", entry);
                    request.setAttribute("posts", entryPosts);
                    request.setAttribute("filteredComments", comments);
                    return "visitor.jsp";
                }
            }

        } catch (final RollbackException e) {
            request.setAttribute(REQ_ATTR_ERROR, e.getMessage());
            return "error.jsp";
        }
    }
}
