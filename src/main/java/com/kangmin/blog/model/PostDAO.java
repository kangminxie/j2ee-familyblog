package com.kangmin.blog.model;

import com.kangmin.blog.databean.Post;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import java.util.Arrays;

public class PostDAO extends GenericDAO<Post> {

    public PostDAO(final ConnectionPool cp, final String tableName) throws DAOException {
        super(Post.class, tableName, cp);
    }

    // For adding a post to the system, using Transactions
    public void addPost(final Post p) throws RollbackException {
        if (p == null) {
            return;
        }
        try {
            Transaction.begin();
            create(p);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    // For deleting a post by pid from the system, using Transactions
    public void removePost(final int pid) throws RollbackException {
        if (pid < 0) {
            return;
        }
        try {
            Transaction.begin();
            delete(pid);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    // Get the posts belong to a particular userName
    public Post[] getUserPosts(final String userName) throws RollbackException {
        try {
            Transaction.begin();
            Post[] posts = match(MatchArg.equals("userEmail", userName));
            Arrays.sort(posts, (Post p1, Post p2) -> -p1.getDate().compareTo(p2.getDate()));
            Transaction.commit();
            return posts;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    // Get the posts belong to a particular userName
    public Post getUserPost(final int pid) throws RollbackException {
        try {
            Transaction.begin();
            final Post[] posts = match(MatchArg.equals("id", pid));
            if (posts != null && posts.length >= 1) {
                Transaction.commit();
                return posts[0];
            }
            return null;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    // Get the owner of a posts by the postId
    public String getPostAuthorEmail(final int idOfThePost) throws RollbackException {
        try {
            Transaction.begin();
            // assume we can find a array, and just return the first one
            Post[] postsMatchedId = match(MatchArg.equals("id", idOfThePost));
            if (postsMatchedId != null && postsMatchedId.length >= 1) {
                Post postFind = postsMatchedId[0];
                Transaction.commit();
                return postFind.getUserEmail();
            }
            Transaction.commit();
            return null;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }

    /*  public Post[] getAllPosts() throws RollbackException {
        try {
            Transaction.begin();
            Post[] posts = match();
            Arrays.sort(posts, (Post p1, Post p2) -> -p1.getDate().compareTo(p2.getDate()));
            Transaction.commit();
            return posts;
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }*/
}
