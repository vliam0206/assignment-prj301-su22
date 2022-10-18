/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamvnt.cart.CartObj;
import lamvnt.cart.CartProducts;
import lamvnt.product.ProductDAO;
import lamvnt.product.ProductDTO;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ViewBookCartController", urlPatterns = {"/ViewBookCartController"})
public class ViewBookCartController extends HttpServlet {
//    private final String VIEW_CART_PAGE="cartPage";
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
        Properties siteMaps= (Properties)context.getAttribute("SITEMAPS");
        String url=siteMaps.getProperty(MyAppConstants.ViewCartFeatures.CART_PAGE);
        try {
            //1. cust goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. cust take his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. check existes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. cal dao
//                        Map<String,ProductDTO> productList = new HashMap<>(); //<SKU, DTO>
                        CartProducts cartProducts = new CartProducts();
                        ProductDAO dao = new ProductDAO();
                        for (String SKU : items.keySet()) {
                            ProductDTO book = dao.getProductBySKU(SKU);
//                            productList.put(SKU, book);
                            cartProducts.addProductToList(book);
                        }// end traverser items
                        //5. process result
                        session.setAttribute("CART_PRODUCTS", cartProducts/*productList*/);
                    }// end items not null
                }// end cart not null               
            }// end cust goes to cart place                        
        } catch (SQLException ex) {
            log("ViewBookCartController _ SQL "+ex.getMessage());
        } catch (NamingException ex) {
            log("ViewBookCartController _ Naming "+ex.getMessage());
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
