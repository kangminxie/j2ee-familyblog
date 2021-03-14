package com.kangmin.blog.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadLocalRandom;

import static com.kangmin.blog.util.Constants.REQ_PARAM_CSRF_TOKEN;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_CSRF_TOKEN;

public final class CsrfTokenUtil {

    private static final Logger LOG = Logger.getLogger(CsrfTokenUtil.class);

    public static void setupCsrfToken(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String sessionToken = (String) session.getAttribute(SESSION_ATTR_CSRF_TOKEN);

        if (sessionToken != null) {
            LOG.debug("Already have csrfToken from GET session: " + sessionToken);
            return;
        }

        final long longToken = ThreadLocalRandom.current().nextLong();
        final String stringToken = Long.toHexString(longToken);
        session.setAttribute(SESSION_ATTR_CSRF_TOKEN, stringToken);
        LOG.debug("set session csrfToken is" + stringToken);
    }

    public static boolean validCsrfToken(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String sessionToken = (String) session.getAttribute(SESSION_ATTR_CSRF_TOKEN);
        LOG.debug("Current sessionToken is: " + sessionToken);

        if (sessionToken == null) {
            return false;
        }

        final String requestToken = request.getParameter(REQ_PARAM_CSRF_TOKEN);
        LOG.debug("The csrfToken from the request is: " + requestToken);

        if (requestToken == null) {
            return false;
        }

        return sessionToken.equals(requestToken);
    }

    private CsrfTokenUtil() {
        // com.puppycrawl.tools.checkstyle.checks.design.HideUtilityClassConstructorCheck
    }
}
