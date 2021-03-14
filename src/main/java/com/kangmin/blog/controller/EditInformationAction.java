package com.kangmin.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.databean.User;
import com.kangmin.blog.formbean.EditInformationForm;
import com.kangmin.blog.model.Model;
import com.kangmin.blog.model.UserDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import static com.kangmin.blog.util.Constants.SESSION_ATTR_ADMIN;
import static com.kangmin.blog.util.Constants.SESSION_ATTR_USER;

public class EditInformationAction extends Action {

    private final UserDAO userDAO;

    public EditInformationAction(final Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "editInformation.do";
    }

    public String performGet(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Admin admin = (Admin) session.getAttribute(SESSION_ATTR_ADMIN);
        final User sessionUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (sessionUser == null && admin == null) {
            return "login.do";
        } else if (sessionUser == null) {
            return "adminHome.do";
        }

        // user is here, not an admin, render the jsp form
        return "editInformation.jsp";
    }

    public String performPost(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Admin admin = (Admin) session.getAttribute(SESSION_ATTR_ADMIN);
        final User user = (User) session.getAttribute(SESSION_ATTR_USER);
        if (user == null && admin == null) {
            return "login.do";
        } else if (user == null) {
            return "adminHome.do";
        }

        // check the form
        final EditInformationForm form = new EditInformationForm(request);
        request.setAttribute("form", form);
        final List<String> errors = new ArrayList<>(form.getValidationErrors());
        request.setAttribute("errors", errors);

        if (errors.size() != 0) {
            return "editInformation.jsp";
        }

        if (form.getButton().trim().equals("Edit Information")) {
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setAddressLine1(form.getAddressLine1());
            user.setAddressLine2(form.getAddressLine2());
            user.setCity(form.getCity());
            user.setState(form.getState());
            user.setCountry(form.getCountry());
            user.setZipcode(form.getZipcode());
            user.setPhoneNumber(form.getPhoneNumber());

            try {
                Transaction.begin();
                userDAO.update(user);
                Transaction.commit();
            } catch (final RollbackException e) {
                e.printStackTrace();
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }
            // successfully changed
            session.setAttribute(SESSION_ATTR_USER, user);
            request.setAttribute("remind", "My Information Successfully Updated");
            return "editInformation.jsp";
        } else {
            errors.add("Action is a question, no changePassword button was hit");
            return "editInformation.jsp";
        }
    }
}
