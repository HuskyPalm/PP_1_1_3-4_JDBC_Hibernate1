package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;

import javax.persistence.Transient;



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

  public static Session getSession() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, DRIVER);
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, NAME_USER);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, DIALECT);
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sessionFactory.getCurrentSession();
  }// реализуйте настройку соеденения с БД
}

