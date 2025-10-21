package com.example.gestion.util;
import java.sql.*; import java.util.*; import java.io.InputStream;
public class DbUtil {
    private static String url,user,password;
    static {
        try (InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties(); p.load(in);
            url = p.getProperty("jdbc.url"); user = p.getProperty("jdbc.user"); password = p.getProperty("jdbc.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) { throw new ExceptionInInitializerError(e); }
    }
    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(url,user,password); }
}
