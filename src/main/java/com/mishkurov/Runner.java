package com.mishkurov;

import java.sql.*;

/**
 * @author Anton_Mishkurov
 */
public class Runner {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/liquibase";

    //  Database credentials
    static final String USER = "liquibase";
    static final String PASS = "";

    public static void main(String[] args) {
        System.out.println("=====================Hello!=======================");
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM DATABASECHANGELOG";
            ResultSet rs = statement.executeQuery(sql);

//            printTableColumns(connection, "PERSON");

            System.out.println("Changeset list:");
            while (rs.next()) {
                System.out.print("ID:" + rs.getString("ID") + ", ");
                System.out.print("AUTHOR:" + rs.getString("AUTHOR") + ", ");
                System.out.print("FILENAME:" + rs.getString("FILENAME") + ", ");
                System.out.print("COMMENTS:" + rs.getString("COMMENTS") + "\n");
            }

            sql = "SELECT * FROM PERSON";
            rs = statement.executeQuery(sql);
            System.out.println("Persons table:");
            while (rs.next()) {
                System.out.print("ID:" + rs.getString("ID") + ", ");
                System.out.print("FIRSTNAME:" + rs.getString("FIRSTNAME") + ", ");
                System.out.print("LASTNAME:" + rs.getString("LASTNAME") + ", ");
                System.out.print("USERNAME:" + rs.getString("USERNAME") + ", ");
                System.out.print("NICKNAME:" + rs.getString("NICKNAME") + "\n");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("=====================Bye!=======================");
    }

    private static void printTableColumns(Connection connection, String tableNamePattern) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet result = databaseMetaData.getColumns(
                null , null, tableNamePattern.toUpperCase(), null);

        while (result.next()) {
            String columnName = result.getString("COLUMN_NAME");
            System.out.println("Column name:" + columnName);
        }
    }
}
