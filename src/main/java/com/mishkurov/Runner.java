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
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

            printTableData(connection, "DATABASECHANGELOG");
            printTableData(connection, "PERSON");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("=====================Bye!=======================");
    }

    private static void printTableData(Connection connection, String tableName) throws SQLException {
        System.out.println("Table name:" + tableName);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet columns = databaseMetaData.getColumns(null, null, tableName.toUpperCase(), null);
        try (Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM " + tableName + ";";
            ResultSet table = statement.executeQuery(sql);
            printValuesForEachColumnsInTable(table, columns);
        }
    }

    private static void printValuesForEachColumnsInTable(ResultSet table, ResultSet columns) throws SQLException {
        while (table.next()) {
            while (columns.next()) {
                System.out.println(String.format("Column %s: %s ", columns.getString("COLUMN_NAME"),
                        table.getString(columns.getString("COLUMN_NAME"))));
            }
        }
    }
}
