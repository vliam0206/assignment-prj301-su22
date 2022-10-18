/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamvnt.cart.CartObj;
import lamvnt.cart.CartProducts;
import lamvnt.orderDetail.OrderDetailDAO;
import lamvnt.product.ProductDTO;
import lamvnt.tblOrder.TblOrderDAO;
import lamvnt.utils.MyAppConstants;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

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
        String url = siteMaps.getProperty(MyAppConstants.CheckOutFeatures.ERROR_PAGE);
        try {
            //1. cust goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. cust take his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. check existes items
                    Map<String, Integer> items = cart.getItems(); //<SKU,quantity>
                    if (items != null) {
                        //4. insert to DB
                        //4.5. get list of products in cart
                        boolean insertOrdDetailRs = false;
                        boolean insertOrdRs = false;
                        CartProducts cartProducts = (CartProducts)session.getAttribute("CART_PRODUCTS");
                        Map<String, ProductDTO> productList = cartProducts.getProducts(); //<sku,productDTO>
                        
                        if (productList != null) {
                            // insert into tblOrder
                            TblOrderDAO ordDao = new TblOrderDAO();                                                       
                            insertOrdRs = ordDao.checkout(productList, items);
                            // insert into oderDetail
                            OrderDetailDAO ordDetailDao = new OrderDetailDAO(); 
                            int identCurrent = ordDao.getIdentCurent();
                            insertOrdDetailRs = ordDetailDao.checkout(identCurrent,productList,items);
                            
                        }// end product list not null
                        if (insertOrdDetailRs && insertOrdRs) {
                            //5. delete cart -> remove attribute
                            session.removeAttribute("CART");
                            session.removeAttribute("CART_PRODUCTS");
                            //6. Back to shopping page
                            url = siteMaps.getProperty(MyAppConstants.CheckOutFeatures.SHOW_PRODUCT_LIST_CONTROLLER);
                        }

                    }// end items not null                    
                }// end cart not null               
            }// end cust goes to cart place
        } catch (NamingException ex) {
            log("CheckOutController _ Naming "+ ex.getMessage());
        } catch (SQLException ex) {
            log("CheckOutController _ SQL "+ex.getMessage());
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
