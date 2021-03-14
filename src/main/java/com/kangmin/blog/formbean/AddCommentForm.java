package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AddCommentForm {

    private String item;
    private String toId;
    private String action;

    public AddCommentForm(final HttpServletRequest request) {
        item = FormHelper.sanitize(request.getParameter("newCommentItem"));
        toId = FormHelper.sanitize(request.getParameter("replyToId"));
        action = FormHelper.sanitize(request.getParameter("newCommentButton"));
    }

    public String getItem() {
        return item;
    }

    public String getAction() {
        return action;
    }

    public String getToId() {
        return toId;
    }

    public int getToIdAsInt() {
        try {
            return Integer.parseInt(toId);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setItem(final String item) {
        this.item = item;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public void setToId(final String id) {
        this.toId = id;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<>();
        if (item == null) {
            errors.add("Your comment content is MISSING");
        } else if (item.trim().equals("")) {
            errors.add("Your comment content is empty!");
        }

        if (toId == null) {
            errors.add("The toId is MISSING");
        } else if (toId.trim().equals("")) {
            errors.add("The toId is empty!");
        }

        try {
            assert toId != null;
            Integer.parseInt(toId);
            if (Integer.parseInt(toId) < 0) {
                errors.add("The replyToId is less than 0!");
            }
        } catch (final NumberFormatException e) {
            errors.add("The replyToId for the comment is not an integer");
        }

        if (action == null) {
            errors.add("Action is required, no Comment button is hit");
        }

        // So far, if above errors show, return
        if (errors.size() > 0) {
            return errors;
        }

        assert action != null;
        if (!action.equals("Comment")) {
            errors.add("Invalid action: " + action);
        }
        return errors;
    }
}
