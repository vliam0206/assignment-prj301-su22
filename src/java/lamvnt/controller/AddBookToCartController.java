/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamvnt.cart.CartObj;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddBookToCartController", urlPatterns = {"/AddBookToCartController"})
public class AddBookToCartController extends HttpServlet {
    
//    private final String SHOPPING_PAGE="shopping.html";
//    private final String SHOW_PRODUCT_LIST_CONTROLLER="showProductList";
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
        // get sitemap
        ServletContext context = getServletContext();
        Properties siteMaps= (Properties)context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyAppConstants.AddBookFeatures .SHOW_PRODUCT_LIST_CONTROLLER);
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(true); // bat buoc phai co gio hang (cart) -> session luon khac null            
            //2. Cust take his/her cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }// end cart did not existed
            //3. Cust drop item to cart
//            String book=request.getParameter("cboBook"); //ko bang null do ko co thi ko truyen para ve server
            String sku = request.getParameter("txtSKU");
            // add to cart
            cart.addBookToCart(sku);
            session.setAttribute("CART", cart);
            //4. Cust drop item again & again -> Quay lai trang mua hang: ShowProductList
            
        } finally {
            response.sendRedirect(url);
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
