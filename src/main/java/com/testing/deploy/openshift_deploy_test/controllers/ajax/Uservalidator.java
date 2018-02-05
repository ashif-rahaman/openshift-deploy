/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.deploy.openshift_deploy_test.controllers.ajax;

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
@WebServlet(name = "Uservalidator", urlPatterns = {"/getuser"})
public class Uservalidator extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String jsonReply;

        if ((username != null) && (!username.isEmpty())) {

            jsonReply = "{\"wrong_param\":\"false\",";
            jsonReply += "\"user\":";

            DBExecutor db = new DBExecutor();

            String sql = "SELECT username FROM users WHERE username = \"" + username + "\"";

            ResultSet resultSet = db.execute(sql);
            try {
                if (resultSet.next()) {

                    jsonReply += "\"true\"}";
                } else {

                    jsonReply += "\"false\"}";
                }
            } catch (SQLException ex) {

                jsonReply += "\"false\"}";
            }

            db.close();
        } else {

            jsonReply = "{\"wrong_param\":\"true\"}";
        }

        response.setContentType("text/JSON;charset=UTF-8");
        response.getWriter().write(jsonReply);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String username = request.getParameter("username");
//        String jsonReply = "{user:";
//
//        response.setContentType("text/JSON;charset=UTF-8");
//        jsonReply += "\"" + username + "\"}";
//        response.getWriter().write(jsonReply);
//        if (!username.isEmpty()) {
//
//            DBExecutor db = new DBExecutor();
//
//            String sql = "SELECT * FROM users WHERE username = " + username;
//
//            ResultSet resultSet = db.execute(sql);
//            try {
//                if (resultSet.next()) {
//
//                    jsonReply += "\"true\"}";
//                }
//            } catch (SQLException ex) {
//
//                jsonReply += "\"false\"}";
//            }
//
//            db.close();
//
//            response.getWriter().write(jsonReply);
//        } else {
//
//            response.getWriter().write("{wrong_param:\"true\"}");
//        }
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
