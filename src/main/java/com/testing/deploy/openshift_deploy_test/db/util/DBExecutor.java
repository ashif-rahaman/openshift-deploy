/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.deploy.openshift_deploy_test.db.util;

import com.testing.deploy.openshift_deploy_test.db.core.ConnectToDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ashif
 */
public class DBExecutor {

    private final ConnectToDatabase connection;

    public DBExecutor() {

        this.connection = new ConnectToDatabase();
    }

    public ResultSet execute(String sql) {

        ResultSet resultSet = connection.getResult(sql);

        return resultSet;
    }

    public boolean close() {

        try {

            this.connection.closeConnection();
            return true;
        } catch (SQLException ex) {

            return false;
        }
    }
}
