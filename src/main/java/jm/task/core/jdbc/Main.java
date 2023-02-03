package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Arthur", "Simple", (byte) 38);
        usi.saveUser("Nikolai", "Pirojkov", (byte) 35);
        usi.saveUser("Johan", "Saylor", (byte) 41);
        usi.saveUser("Vitaliy", "Popov", (byte) 29);
        usi.removeUserById(4);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();

    }

}
