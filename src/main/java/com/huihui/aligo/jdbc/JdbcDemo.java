package com.huihui.aligo.jdbc;

import java.sql.*;

/**
 * JDBC操作数据库Demo
 *
 * @author minghui.y BG358486
 * @create 2020-09-12 21:23
 **/
public class JdbcDemo {


    public static void main(String[] args) throws SQLException {



        insertDemo();




    }

    public static void insertDemo() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into aligo_store(id, code, user_name, name, address) values(?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, 8);
        ps.setString(2, "code008");
        ps.setString(3, "17826789989");
        ps.setString(4, "事务店铺008");
        ps.setString(5, "事务地址");

        int count = ps.executeUpdate();
        System.out.println(count);

    }


    public static void searchDemo() throws SQLException {
        Connection connection = JdbcUtils.getConnection();


//        Statement statement = connection.createStatement();
        //预编译SQL
        String sql = "select * from aligo_store where user_name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        //SQL参数赋值
        ps.setString(1, "17826833394");
        connection.setAutoCommit(false);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("user_name"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("address"));
            System.out.println(resultSet.getDate("created_time"));
        }
    }


}
