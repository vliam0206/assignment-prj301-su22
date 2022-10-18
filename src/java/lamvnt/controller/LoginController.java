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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamvnt.registration.RegistrationDAO;
import lamvnt.registration.RegistrationDTO;
import lamvnt.registration.RegistrationLoginError;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    //private final String INVALID_PAGE = "invalidPage";
    //private final String SEARCH_PAGE = "searchPage";
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
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyAppConstants.LoginFeatures.INVALID_PAGE); //INVALID_PAGE;   
        RegistrationLoginError errors = new RegistrationLoginError();
        boolean foundErr = false;

        try { // TOng SL tham so tren servlet chuc nang = tong SL tham so tren (form - 1)
            String username = request.getParameter("txtUsername").trim();
            String password = request.getParameter("txtPassword");
            // check all user's constrain
            if (username.isEmpty()) {
                foundErr = true;
                errors.setUserameEmptyErr("Username must be not empty!");
            }
            if (password.isEmpty()) {
                foundErr = true;
                errors.setPasswordEmptyErr("Password must be not empty!");
            }
            if (foundErr) {
                request.setAttribute("LOGIN_ERR", errors);
            }
            else {
                //1. call model/DAO
                //- new DAO obj, then call method on DAO object
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO result = dao.checkLogin(username, password);
                //2. process result
                if (result != null) {
                    url = siteMaps.getProperty(MyAppConstants.LoginFeatures.SEARCH_PAGE);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER", result);
                    // Write cookie
//                Cookie cookie = new Cookie(username, password); // day chi la demo, lam that phai co hashcode                
//                cookie.setMaxAge(60*60);
//                response.addCookie(cookie);                
                } //end if user click login
                else {
                    errors.setAccountNotFound("Wrong username/password! Please try again!");
                    request.setAttribute("LOGIN_ERR", errors);
                }
            }
        } catch (SQLException ex) {
            log("LoginController _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginController _ Naming " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
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
