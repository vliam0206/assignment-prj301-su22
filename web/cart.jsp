<%-- 
    Document   : cart
    Created on : Jun 19, 2022, 3:27:08 PM
    Author     : DELL
--%>

<%@page import="java.util.HashMap"%>
<%@page import="lamvnt.product.ProductDTO"%>
<%@page import="lamvnt.cart.CartObj"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>Your Cart</h1>        
        <c:set var="cart" value="${sessionScope.CART}"></c:set>
        <c:set var="items" value="${cart.items}"></c:set>
        <c:choose>
            <c:when test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Sum</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <form action="removeBook">                    
                        <tbody>
                            <c:set var="cartProducts" value="${sessionScope.CART_PRODUCTS}"></c:set>
                            <c:set var="products" value="${cartProducts.products}"></c:set>
                                                        
                            <c:if test="${not empty products}">
                                <c:forEach var="pd" items="${products}"  varStatus="counter">
                                    <c:set var="dto" value="${pd.value}"></c:set>
                                    <c:set var="quantity" value="${items.get(pd.key)}"></c:set>                              
                                    <tr>
                                            <td>${counter.count}</td>
                                        <td>${dto.name}</td>
                                        <td>${quantity}</td>
                                        <td>${dto.price}</td>
                                        <td>${dto.price*quantity}</td>
                                        <td>
                                            <input type="checkbox" name="chkRemove" value="${pd.key}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="5">
                                        <a href="showProductList">Add more item to cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove" name="btAction" />
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </form>
                </table>
                <!-- CHECK OUT -->
                </br><a href="checkOut"><button>Checkout</button></a>
            </c:when>
            <c:otherwise>
                <font color="red">Your cart is empty!</font>
            </c:otherwise>
        </c:choose>


        <%--
        <%
            //1. cust goes to his/her cart place
            if (session != null) { // jsp mac dinh getSession(False) 
                //2. cust takes his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. cust check existed items
                    Map<String, Integer> items = cart.getItems();
                    //Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Sum</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="DispatchServlet">
                <tbody>
                    <%
                        int count = 0;
                        Map<String, ProductDTO> productList = (Map<String, ProductDTO>) session.getAttribute("PRODUCTS");//<SKU,product>
                        if (productList != null) {

                        }
                        //for (String key : items.keySet()) {
                        for (String key : productList.keySet()) {
                            String nameBook = productList.get(key).getName();
                            int quantity = items.get(key);
                            double price = productList.get(key).getPrice();
                    %>            
                    <tr>
                        <td><%= ++count%>.</td>
                        <td><%= nameBook%></td>
                        <td><%= quantity%></td>
                        <td><%= price%></td>
                        <td><%= price * quantity%></td>
                        <td>
                            <input type="checkbox" name="chkRemove" value="<%= key%>" />
                        </td>

                    </tr>            
                    <%
                        }// end of traverser list of items
                    %>                        
                    <tr>
                        <td colspan="5">
                            <a href="DispatchServlet?btAction=show List Product">Add more item to cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </form>
        </table>
        <!-- check out -->
        </br>        
        <a href="DispatchServlet?btAction=checkout"><button>Checkout</button></a>        
        <%
                        return;
                    }// end items not null       
                }// end of cart is not null
            }// end session not null
%>
        <font color="red">Your cart is empty!</font>
        --%>

    </body>
</html>
