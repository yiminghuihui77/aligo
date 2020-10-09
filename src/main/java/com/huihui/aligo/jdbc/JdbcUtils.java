package com.huihui.aligo.jdbc;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * JDBC工具类
 *
 * @author minghui.y BG358486
 * @create 2020-09-12 21:33
 **/
public class JdbcUtils {

    public static Connection getConnection() {
//        Properties properties = new Properties();

//        InputStream in = JdbcUtils.class.getResourceAsStream("jdbc.properties");
        try {
//            properties.load(in);

           /* String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String driver = properties.getProperty("driver");*/
            String username = "root";
            String password = "ymh96122";
            String jdbcUrl = "jdbc:mysql://localhost:3306/aligo";
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);
            return DriverManager.getConnection(jdbcUrl, username, password);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
