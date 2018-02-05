/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.deploy.openshift_deploy_test.controllers.core;

import com.testing.deploy.openshift_deploy_test.db.util.DBExecutor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashif
 */
@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
public class SignupController extends HttpServlet {

    private static final long serialVersionUID = 1L;

//      Handles the HTTP <code>GET</code> method.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("userKey");

        if (user != null) {

            response.sendRedirect("home");
            return;
        } else {

            response.sendRedirect("signup.jsp");
            return;
        }
    }

//      Handles the HTTP <code>POST</code> method.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("userKey");

        if (user != null) {

            response.sendRedirect("home");
            return;
        } else {

            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (!(firstName == null || lastName == null
                    || username == null || email == null || password == null)) {

                if (!(firstName.isEmpty() || lastName.isEmpty()
                        || username.isEmpty() || email.isEmpty() || password.isEmpty())) {

                    String sql = "INSERT INTO users (first_name, last_name, email, username, password)"
                            + "VALUES (\""
                            + firstName + "\", \""
                            + lastName + "\", \""
                            + email + "\", \""
                            + username + "\", \""
                            + password + "\")";

                    DBExecutor db = new DBExecutor();
                    db.execute(sql);

                    request.getSession().setAttribute("userKey", username);
                    response.sendRedirect("home");
                    return;
                } else {

                    response.sendRedirect("signup");
                    return;
                }
            } else {

                response.sendRedirect("signup");
                return;
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
