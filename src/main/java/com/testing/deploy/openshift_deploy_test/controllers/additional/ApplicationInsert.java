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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashif
 */
@WebServlet(name = "ApplicationInsert", urlPatterns = {"/applyinsert"})
public class ApplicationInsert extends HttpServlet {

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

        String user = (String) request.getSession().getAttribute("userKey");

        if (user != null && !user.isEmpty()) {

            String subject = request.getParameter("apply_subject");
            String body = request.getParameter("apply_body");
            String startDate = request.getParameter("start_date");
            String endDate = request.getParameter("end_date");

            if (subject != null && body != null && startDate != null && endDate != null) {

                DBExecutor db = new DBExecutor();
                String sql = "SELECT id, super_id FROM users WHERE username = '" + user + "'";
                ResultSet resultSet = db.execute(sql);

                try {

                    if (resultSet.next()) {

                        int super_id = resultSet.getInt("super_id");
                        int applicant_id = resultSet.getInt("id");

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        String applyDate = df.format(date);

                        String insertSql = "INSERT INTO application_log (super_id, app_subject, app_body, app_status, apply_date, app_update, startdate, enddate, applicant_id) "
                                + "VALUES (" + super_id + ", '" + subject + "', '" + body + "', 'pending', '" + applyDate + "', 1, '" + startDate + "', '" + endDate + "', " + applicant_id + ")";

                        db.execute(insertSql);

                        response.sendRedirect("home");
                        db.close();
                        return;
                    }
                } catch (SQLException e) {

                    response.sendRedirect("apply");
                    return;
                }

                db.close();

            } else {

                response.sendRedirect("apply");
            }

        } else {

            response.sendRedirect("index");
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
