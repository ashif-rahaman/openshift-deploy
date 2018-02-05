/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.deploy.openshift_deploy_test.controllers.additional;

import com.testing.deploy.openshift_deploy_test.db.util.DBExecutor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashif
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //HTTP GET Request Handler
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("userKey");

        if (user == null) {

            response.sendRedirect("index.jsp");
            return;
        }

        response.sendRedirect("home");
    }

    //HTTP POST Request Handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("userKey");

        if (user == null) {

            DBExecutor db = new DBExecutor();

            String loginId = request.getParameter("loginid").trim();
            String password = request.getParameter("password");

            if (loginId != null && password != null) {

                String sql;

                if (loginId.indexOf('@') == -1) {

                    sql = "SELECT * FROM users WHERE username = '" + loginId + "'";
                } else {

                    sql = "SELECT * FROM users WHERE email = '" + loginId + "'";
                }

                ResultSet resultSet = db.execute(sql);

                try {
                    if (resultSet.next()) {

                        if (resultSet.getString("password").equals(password)) {

                            request.getSession().setAttribute("name",
                                    resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                            request.getSession().setAttribute("userKey", loginId);
                            response.sendRedirect("home");
                        } else {

                            String msg = "Invalid username or password";
                            request.setAttribute("login_error_msg", msg);
                            request.getSession().setAttribute("userKey", null);

                            response.sendRedirect("index.jsp");
                        }

                        db.close();
                    } else {

                        String msg = "Invalid username or password";
                        request.setAttribute("login_error_msg", msg);
                        request.getSession().setAttribute("userKey", null);

                        response.sendRedirect("index.jsp");
                        return;
                    }
                } catch (SQLException ex) {

                    System.err.println(ex.toString());
                }
            }
        } else {

            response.sendRedirect("home");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
