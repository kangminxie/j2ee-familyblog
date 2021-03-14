package com.kangmin.blog.util;

import com.kangmin.blog.databean.Comment;
import com.kangmin.blog.databean.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CommentsUtil {

    // help method to filter the comments out, for some selected posts
    public static List<Comment> getFilteredComments(
        final Post[] thePosts,
        final Comment[] commentsPool
    ) {
        if (thePosts == null || commentsPool == null) {
            return null;
        }

        final Map<Integer, Integer> entryPostsIdMap = new HashMap<>();
        for (final Post p : thePosts) {
            entryPostsIdMap.put(p.getId(), p.getId());
        }

        final List<Comment> comments = new ArrayList<>();
        for (final Comment cmt : commentsPool) {
            if (entryPostsIdMap.containsKey(cmt.getReplyTo())) {
                comments.add(cmt);
            }
        }
        return comments;
    }

    // get a list of comment for a single post
    public static List<Comment> getCommentsFromOnePost(
        final Post thePost,
        final Comment[] commentsPool
    ) {
        if (thePost == null || commentsPool == null) {
            return null;
        }
        final List<Comment> comments = new ArrayList<>();
        for (final Comment cmt : commentsPool) {
            if (cmt.getReplyTo() == thePost.getId()) {
                comments.add(cmt);
            }
        }
        return comments;
    }

    private CommentsUtil() {

    }
}
