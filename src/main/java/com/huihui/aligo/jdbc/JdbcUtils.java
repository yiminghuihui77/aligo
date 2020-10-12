package com.huihui.aligo.jdbc;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类
 *
 * @author minghui.y BG358486
 * @create 2020-09-12 21:33
 **/
public class JdbcUtils {

    public static final String PARAM_REPLACE_REGEX = "#\\{[a-z,A-Z]+\\}";
    public static final String PARAM_REPLACE_TARGET = "?";

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

    /**
     * 执行插入操作
     * @param sql
     * @param params
     * @return
     */
    public static int insert(String sql, Object[] params) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(true);

            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0;i < params.length;i ++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("执行insert SQL失败:" +  e.getMessage());
        }

    }

}
