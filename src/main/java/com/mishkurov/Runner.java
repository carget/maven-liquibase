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

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("=====================Hello!=======================");
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            printTableData(connection, "DATABASECHANGELOG");
            printTableData(connection, "PERSON");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("=====================Bye!=======================");
    }

    private static void printTableColumns(Connection connection, String tableNamePattern) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet result = databaseMetaData.getColumns(
                null, null, tableNamePattern.toUpperCase(), null);

        while (result.next()) {
            String columnName = result.getString("COLUMN_NAME");
            System.out.println("Column name:" + columnName);
        }
    }

    private static void printTableData(Connection connection, String tableName) throws SQLException {
        //todo remove throws or change structure
        System.out.println("Table name:" + tableName);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet result = databaseMetaData.getColumns(null, null, tableName.toUpperCase(), null);
        Statement statement = connection.createStatement();
        //TODO remove sql injection
        String sql = "SELECT * FROM " + tableName + ";";
        ResultSet tableResultSet = statement.executeQuery(sql);
//        while (result.next()) {
//            System.out.println(String.format("Column %s: %s ", result.getString("COLUMN_NAME"),
//                    tableResultSet.getString(result.getString("COLUMN_NAME"))));
//        }
    }
}
