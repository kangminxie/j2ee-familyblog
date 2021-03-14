package com.kangmin.blog.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import static com.kangmin.blog.util.Constants.REQ_ATTR_ERROR;
import static com.kangmin.blog.util.CsrfTokenUtil.setupCsrfToken;
import static com.kangmin.blog.util.CsrfTokenUtil.validCsrfToken;

public abstract class Action {
    private static final Logger LOG = Logger.getLogger(Action.class);

    private static final Map<String, Action> HASH = new HashMap<>();

    public abstract String getName();

    public String performGet(final HttpServletRequest request) {
        LOG.error("Abstract Action's performGet() called.");
        request.setAttribute(REQ_ATTR_ERROR, "No implementation of performGet() for action " + getName());
        return "_errors.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        LOG.error("Abstract Action's performPost() called.");
        request.setAttribute(REQ_ATTR_ERROR, "No implementation of performPost() for action " + getName());
        return "_errors.jsp";
    }

    public static void add(final Action a) {
        synchronized (HASH) {
            if (HASH.get(a.getName()) != null) {
                throw new AssertionError("Two actions with the same name ("
                    + a.getName() + "): " + a.getClass().getName()
                    + " and " + HASH.get(a.getName()).getClass().getName());
            }
            HASH.put(a.getName(), a);
        }
    }

    public static String perform(
        final String name,
        final HttpServletRequest request
    ) {
        final Action a;
        synchronized (HASH) {
            a = HASH.get(name);
        }

        if (a == null) {
            request.setAttribute(REQ_ATTR_ERROR, "There is no action registered for \"" + name + "\"");
            return "_errors.jsp";
        }

        if ("GET".equals(request.getMethod())) {
            setupCsrfToken(request);
            return a.performGet(request);
        }

        if ("POST".equals(request.getMethod())) {
            final String contentType = request.getContentType();
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
                // with multipart encoding, we cannot check csrf parameters here
                return a.performPost(request);
            }

            if (validCsrfToken(request)) {
                return a.performPost(request);
            }

            request.setAttribute(REQ_ATTR_ERROR,
                "Missing or invalid CSRF token (with POST request) for \"" + name + "\"");
            return "login.jsp";
        }

        request.setAttribute(REQ_ATTR_ERROR, "Unexpected HTTP Method (\"" + request.getMethod() + "\") for \"" + name + "\"");
        return "_errors.jsp";
    }
}
