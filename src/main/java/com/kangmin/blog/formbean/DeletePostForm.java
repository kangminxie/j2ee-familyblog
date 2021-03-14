package com.kangmin.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DeletePostForm {

    private String id;
    private String action;

    public DeletePostForm(final HttpServletRequest request) {
        id = FormHelper.sanitize(request.getParameter("deletePostId"));
        action = FormHelper.sanitize(request.getParameter("deletePostButton"));
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public int getIdAsInt() {
        try {
            return Integer.parseInt(id);
        } catch (final NumberFormatException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void setId(final String idS) {
        this.id = idS;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public List<String> getValidationErrors() {
        final List<String> errors = new ArrayList<String>();
        if (id == null) {
            errors.add("The id for post is MISSING");
        } else if (id.trim().equals("")) {
            errors.add("The id for post is empty!");
        }

        try {
            assert id != null;
            Integer.parseInt(id);
            if (Integer.parseInt(id) < 0) {
                errors.add("The post id is less than 0!");
            }
        } catch (final NumberFormatException e) {
            errors.add("The id for post is not an integer");
        }

        if (action == null) {
            errors.add("Action is required, no DeletePost button is hit");
        }

        // So far, if above errors show, return
        if (errors.size() > 0) {
            return errors;
        }

        assert action != null;
        if (!action.trim().equals("Remove")) {
            errors.add("Invalid action: " + action);
        }
        return errors;
    }
}
