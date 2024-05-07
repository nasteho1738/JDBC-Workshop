package com.Lexicon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityDaoJDBC {
        public static void main(String[] args) {

        }
        public CityDaoJDBC() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "6789");
            } catch (SQLException e) {
                System.out.println("SQL Exception: ");
                e.printStackTrace();
            }
        }

        public void findById(int id) {
            City city = null;
            try()

        }
}
