/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamvnt.cart.CartObj;

/**
 *
 * @author DELL
 */
@WebServlet(name = "RemoveBookController", urlPatterns = {"/RemoveBookController"})
public class RemoveBookController extends HttpServlet {

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
        
            try {
               //1. cust goes to his/her cart place
               HttpSession session = request.getSession(false); // session co the het han mac du nhin thay
               if (session!=null)  {
                   //2. cust get his/her cart
                   CartObj cart=(CartObj)session.getAttribute("CART");
                   if (cart!=null) {
                       //3. cust checks existed items
                       Map<String,Integer> items = cart.getItems();
//                       Map<ProductDTO,Integer> items = cart.getItems();
                       if (items!=null) {
                           //4. cust take selected item
                           String[] selectedItems=request.getParameterValues("chkRemove");
                           if (selectedItems!=null) {
                               //5. cust removes items from cart
                               for (String selectedItem : selectedItems) {
//                                   ProductDTO selectedBook = cart.getBook(selectedItem);
                                   cart.removeBookFromCart(selectedItem);
                               }// end remove all selected items from cart
                               session.setAttribute("CART", cart);
                           }// end selected items not null
                       }// end items not null
                   }// end cart not null
               }// end session not null
               
            } finally {
                //6. refresh view cart- call view cart again
                String urlRewriting="viewCart";
                response.sendRedirect(urlRewriting);
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
