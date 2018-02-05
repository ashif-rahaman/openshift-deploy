package com.testing.deploy.openshift_deploy_test.db.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDatabase {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String sql = null;
    private String className = "com.mysql.jdbc.Driver";
    private String databaseURL = "jdbc:mysql://localhost/leaveapp_module";
    private String username = "root";
    private String password = "27443";
    private Exception expections;

    public Exception getExpections() {
        return expections;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void setConnection() {

        try {

            Class.forName(this.className);
            this.connection = DriverManager.getConnection(this.databaseURL, this.username, this.password);
            this.statement = this.connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {

            this.expections = e;
            System.out.println(e.toString());
        }
    }

    private void executeQuery() {

        try {

            this.setConnection();
            this.statement.execute(sql);
            this.resultSet = this.statement.getResultSet();
        } catch (SQLException ex) {

            this.expections = ex;
            System.out.println(ex.toString());
        }
    }

    public ResultSet getResult(String sql) {

        this.sql = sql;
        this.executeQuery();
        return this.resultSet;
    }

    public void closeConnection() throws SQLException {

        try {

            if (this.connection != null) {

                this.connection.close();
            }

            if (this.statement != null) {

                this.statement.close();
            }

            if (this.resultSet != null) {

                this.resultSet.close();
            }
            this.sql = null;
        } catch (SQLException e) {

            throw e;
        }
    }
}
