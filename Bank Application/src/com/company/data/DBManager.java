package com.company.data;

import java.sql.*;

public class DBManager {
    private static String Connection = "jdbc:posgresql://localhost:5432/FinalProject";
    private static String User = "postgres";
    private static String password = "L1chK1ng";
        public static Connection connection() throws Exception {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(Connection, User, password);
        }
    }

