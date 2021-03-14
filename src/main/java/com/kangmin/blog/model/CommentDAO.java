package com.kangmin.blog.model;

import java.util.Arrays;

import com.kangmin.blog.databean.Comment;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class CommentDAO extends GenericDAO<Comment> {

    public CommentDAO(final ConnectionPool cp, final String tableName) throws DAOException {
        super(Comment.class, tableName, cp);
    }

    // For adding a comment to the system, using Transactions
    public void addComment(final Comment c) throws RollbackException {
        if (c == null) {
            return;
        }
        try {
            Transaction.begin();
            create(c);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    // For deleting a comment by cid from the system, using Transactions
    public void removeComment(final int cid) throws RollbackException {
        if (cid < 0) {
            return;
        }
        try {
            Transaction.begin();
            delete(cid);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    public Comment[] getAllComments() throws RollbackException {
        try {
            Transaction.begin();
            final Comment[] comments = match();
            Arrays.sort(comments);
            Transaction.commit();
            return comments;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    public String getCommentAuthorEmail(final int idOfTheComment) throws RollbackException {
        try {
            Transaction.begin();
            // assume we can find a array, and just return the first one
            Comment[] commentsMatchedId = match(MatchArg.equals("id", idOfTheComment));
            if (commentsMatchedId != null && commentsMatchedId.length >= 1) {
                Comment commentFind = commentsMatchedId[0];
                Transaction.commit();
                return commentFind.getByEmail();
            }
            return null;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    public Comment getUserComment(final int id) throws RollbackException {
        try {
            Transaction.begin();
            Comment[] cmts = match(MatchArg.equals("id", id));
            if (cmts != null && cmts.length >= 1) {
                Transaction.commit();
                return cmts[0];
            }
            Transaction.commit();
            return null;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

/*  public Comment[] getUserComments(int replyTo) throws RollbackException {
        if (replyTo <= 0) return null;
        try {
            Transaction.begin();
            Comment[] cmts = match(MatchArg.equals("replyTo", replyTo));
            Arrays.sort(cmts, (Comment c1, Comment c2) -> c1.getDate().compareTo(c2.getDate()));
            Transaction.commit();
            return cmts;
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public Comment[] getUserComments(String userEmail) throws RollbackException {
        if (userEmail == null) return null;
        try {
            Transaction.begin();
            Comment[] cmts = match(MatchArg.equals("byWho", userEmail));
            Arrays.sort(cmts, (Comment c1, Comment c2) -> c1.getDate().compareTo(c2.getDate()));
            Transaction.commit();
            return cmts;
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }*/
}
