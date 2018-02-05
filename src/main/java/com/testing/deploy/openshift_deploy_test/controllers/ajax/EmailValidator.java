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
@WebServlet(name = "EmailValidator", urlPatterns = {"/getemail"})
public class EmailValidator extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email").trim();
        String jsonReply = "{\"wrong_param\":";
        
        if (email != null && !email.isEmpty()) {
            
            jsonReply += "\"false\",\"email\":";
            
            String sql = "Select email FROM users WHERE email=\"" + email + "\"";
            DBExecutor db = new DBExecutor();
            
            ResultSet resultSet = db.execute(sql);
            
            try {
                
                if (resultSet.next()) {
                    
                    jsonReply += "\"true\"}";
                } else {
                    
                    jsonReply += "\"false\"}";
                }
                
            } catch (SQLException e) {
                
                jsonReply = "{\"wrong_param\":\"true\"}";
            }
        } else {
            
            jsonReply = "{\"wrong_param\":\"true\"}";
        }
        
        response.setContentType("text/JSON;charset=UTF-8");
        response.getWriter().write(jsonReply);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
