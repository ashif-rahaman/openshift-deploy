/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.deploy.openshift_deploy_test.controllers.core;

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
@WebServlet(name = "ApplyController", urlPatterns = {"/apply"})
public class ApplyController extends HttpServlet {

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

        String user = (String) request.getSession().getAttribute("userKey");

        if (user != null) {

            DBExecutor db = new DBExecutor();
            String sqlApplicant = "Select first_name, last_name, super_id FROM users "
                    + "WHERE username = '" + user + "'";

            ResultSet applicant = db.execute(sqlApplicant);
            try {

                if (applicant.next()) {

                    request.getSession().setAttribute("Applicant_Name",
                            applicant.getString("first_name") + " " + applicant.getString("last_name"));

                    String sqlBoss = "Select first_name, last_name FROM users "
                            + "WHERE id = " + applicant.getInt("super_id");

                    if (applicant.getInt("super_id") != 0) {

                        DBExecutor bossDB = new DBExecutor();

                        ResultSet boss = bossDB.execute(sqlBoss);

                        if (boss.next()) {

                            request.getSession().setAttribute("Super_Name",
                                    boss.getString("first_name") + " " + boss.getString("last_name"));
                        } else {

                            request.getSession().setAttribute("Super_Name", null);
                        }

                        bossDB.close();
                    } else {

                        request.getSession().setAttribute("Super_Name", null);
                    }

                    response.sendRedirect("apply.jsp");

                    db.close();
                    return;
                } else {

                    response.sendRedirect("index.jsp");
                    db.close();
                    return;
                }
            } catch (SQLException e) {

                System.err.println(e.toString());

                response.sendRedirect("index.jsp");
                db.close();
                return;
            }

        } else {

            response.sendRedirect("index.jsp");
            return;
        }
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
