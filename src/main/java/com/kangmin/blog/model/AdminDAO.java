package com.kangmin.blog.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.kangmin.blog.databean.Admin;
import com.kangmin.blog.formbean.SHA;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class AdminDAO extends GenericDAO<Admin> {

    public AdminDAO(final ConnectionPool cp, final String tableName) throws DAOException, RollbackException {
        super(Admin.class, tableName, cp);

        // initial admin password
        // remove this code after first deploy
        if (!this.getAllAdminNames().contains("admin".toLowerCase())) {
            final Admin defaultAdmin = new Admin();
            defaultAdmin.setAdminName("admin".toLowerCase());
            defaultAdmin.setPassword(SHA.getResult("admin"));
            defaultAdmin.setFirstName("Michael");
            defaultAdmin.setLastName("Owen");
            defaultAdmin.setEnrollDate(new Date(System.currentTimeMillis()));
            this.create(defaultAdmin);
        } else {
            final Admin admin = match(MatchArg.equals("adminName", "admin"))[0];
            admin.setPassword(SHA.getResult("admin"));
            this.update(admin);
        }
    }

    public List<String> getAllAdminNames() throws RollbackException {
        final Admin[] admins = match();
        final List<String> allNames = new ArrayList<>();
        for (final Admin a : admins) {
            allNames.add(a.getAdminName());
        }
        return allNames;
    }

    public Admin[] getAdmins() throws RollbackException {
        try {
            // Calls GenericDAO's match() method.
            // This no match constraint arguments, match returns all the Item beans
            Transaction.begin();
            final Admin[] admins = match();
            Arrays.sort(admins, Comparator.comparing(Admin::getFirstName));
            Arrays.sort(admins, Comparator.comparing(Admin::getLastName));
            Transaction.commit();
            return admins;
        } finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
        }
    }
}
