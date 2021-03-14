package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommentForm {

    private String id;
    private String action;
    private String toId;
    private String source;

    public DeleteCommentForm(final HttpServletRequest request) {
        id = FormHelper.sanitize(request.getParameter("deleteCommentId"));
        action = FormHelper.sanitize(request.getParameter("deleteCommentButton"));
        toId = FormHelper.sanitize(request.getParameter("deleteReplyToId"));
        source = FormHelper.sanitize(request.getParameter("deleteSource"));
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getToId() {
        return toId;
    }

    public String getSource() {
        return source;
    }

    public int getIdAsInt() {
        try {
            return Integer.parseInt(id);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getToIdAsInt() {
        try {
            return Integer.parseInt(toId);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setToId(final String id) {
        this.toId = id;
    }

    public void setId(final String idS) {
        this.id = idS;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public void setSource(final String s) {
        this.source = s;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<>();
        if (id == null) {
            errors.add("The id for Comment is MISSING");
        } else if (id.trim().equals("")) {
            errors.add("The id for Comment is empty!");
        }

        try {
            assert id != null;
            Integer.parseInt(id);
            if (Integer.parseInt(id) < 0) {
                errors.add("The comment id is less than 0!");
            }
        } catch (final NumberFormatException e) {
            errors.add("The id of comment to be deleted is not an integer, hacked");
        }

        if (toId == null) {
            errors.add("The replyToId is MISSING");
        } else if (toId.trim().equals("")) {
            errors.add("The replyToId is empty!");
        }

        try {
            assert toId != null;
            Integer.parseInt(toId);
            if (Integer.parseInt(toId) < 0) {
                errors.add("The replyToId is less than 0!");
            }
        } catch (final NumberFormatException e) {
            errors.add("The replyToId for commentTo is not an integer, hacked");
        }

        if (action == null) {
            errors.add("Action is required, no DeleteComment button is hit");
        }

        if (source == null) {
            errors.add("The comment source is MISSING");
        } else if (source.trim().equals("")) {
            errors.add("The comment source is empty, not home or visitor!");
        } else if (!source.trim().equals("home") && !source.trim().equals("visitor")) {
            errors.add("The comment source is not home or visitor");
        }

        // So far, if above errors show, return
        if (errors.size() > 0) {
            return errors;
        }

        assert action != null;
        if (!action.trim().equals("X")) {
            errors.add("Invalid action: " + action);
        }
        return errors;
    }
}
