package com.kangmin.blog.model;

import java.util.Arrays;
import java.util.Comparator;

import com.kangmin.blog.databean.User;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class UserDAO extends GenericDAO<User> {

    public UserDAO(final ConnectionPool cp, final String tableName) throws DAOException {
        super(User.class, tableName, cp);
    }

    public User[] getUsers() throws RollbackException {
        try {
            // Calls GenericDAO's match() method.
            // This no match constraint arguments, match returns all the Item beans
            Transaction.begin();
            final User[] users = match();
            Arrays.sort(users, Comparator.comparing(User::getFirstName));
            Arrays.sort(users, Comparator.comparing(User::getLastName));
            Transaction.commit();
            return users;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }
}
