/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lamvnt.registration.RegistrationCreateError;
import lamvnt.registration.RegistrationDAO;
import lamvnt.registration.RegistrationDTO;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        RegistrationCreateError errors = new RegistrationCreateError();
        // get sitemap
        ServletContext context = getServletContext();
        Properties siteMaps= (Properties)context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyAppConstants.CreateAccFeatures.ERROR_PAGE);
        boolean foundError = false;
        try {
            //1. check all user's constraints
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundError=true;
                errors.setUserameLengthErr("User name required input from 6-20 chars");
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundError=true;
                errors.setPasswordLengthErr("Password  required input from 6-30 chars");
            } else if (!confirm.trim().equals(password)) {
                foundError=true;
                errors.setConfirmNotMathched("Confirm must matches password");
            }
            if (fullname.trim().length() < 2
                    || fullname.trim().length() > 50) {
                foundError=true;
                errors.setFullnamLengthErr("Full name  required input from 2-50 chars");
            }
            
            if (foundError) {
                //2. store errors and fw to error page
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //3. cal DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);                
                dao.createAccount(dto);
                url=siteMaps.getProperty(MyAppConstants.CreateAccFeatures.LOGIN_PAGE);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
            String msg = ex.getMessage();
            log("CreateAccountController _ SQL " + msg);
            if (msg.contains("duplicate")) { // trung username (key) cung la SQLException
                errors.setUsernameExisted(username+" is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {            
            log("CreateAccountController _ Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
