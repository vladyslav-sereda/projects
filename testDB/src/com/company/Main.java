package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/practice?user=root&password=159357&useSSL=false");
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO user VALUES (DEFAULT, ?, ?, ?)");

        int k = 1;
        preparedStatement.setString(k++, "Sanya");
        preparedStatement.setString(k++, "admin");
/*
        if (preparedStatement.executeUpdate() > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {

            }*/
preparedStatement.execute();
    }
}

