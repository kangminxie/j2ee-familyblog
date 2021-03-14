package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AddPostForm {

    private static final int MAX_LENGTH = 1024;

    private String item;
    private String action;

    public AddPostForm(final HttpServletRequest request) {
        item = FormHelper.sanitize(request.getParameter("newPostItem"));
        action = FormHelper.sanitize(request.getParameter("newPostButton"));
    }

    public String getItem() {
        return item;
    }

    public String getAction() {
        return action;
    }

    public void setItem(final String item) {
        this.item = item;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<String>();
        if (item == null) {
            errors.add("Your post is MISSING");
        } else if (item.trim().equals("")) {
            errors.add("Your post content is empty!");
        } else if (item.length() > MAX_LENGTH) {
            errors.add("Two much words (>1024), mysql does not support it now!");
        }

        if (action == null) {
            errors.add("Action is required, no Post button is hit");
        }

        // So far, if above errors show, return
        if (errors.size() > 0) {
            return errors;
        }

        assert action != null;
        if (!action.equals("Post")) {
            errors.add("Invalid action: " + action);
        }
        return errors;
    }
}
