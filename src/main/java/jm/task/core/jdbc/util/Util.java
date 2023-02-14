package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class Util {
  private static SessionFactory sessionFactory;
  public static final String NAME_USER= "root";
  public static final String PASSWORD = "7Kb10pyW12";
  public static final String URL = "jdbc:mysql://localhost:3306/mysql";
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(DRIVER);
      connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
      System.out.println("Connection OK");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      System.out.println("Connection ERROR");
    }
    return connection;
  }


  public static SessionFactory getSession() {

      try {
        Configuration configuration = new Configuration()
            .setProperty("hibernate.connection.driver_class", DRIVER)
            .setProperty("hibernate.connection.url", URL)
            .setProperty("hibernate.connection.username", NAME_USER)
            .setProperty("hibernate.connection.password", PASSWORD)
            .setProperty("hibernate.dialect", DIALECT)
            .addAnnotatedClass(User.class)
            .setProperty("hibernate.c3p0.min_size","5")
            .setProperty("hibernate.c3p0.max_size","200")
            .setProperty("hibernate.c3p0.max_statements","200");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
            e.printStackTrace();
                } if (sessionFactory != null) {
                    System.out.println("ok");
      } return sessionFactory;
  }// реализуйте настройку соеденения с БД
}

