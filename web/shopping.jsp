<%-- 
    Document   : shopping
    Created on : Jun 19, 2022, 1:02:35 AM
    Author     : LamVo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="lamvnt.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>List of items</h1>
        <c:set var="pdList" value="${requestScope.PRODUCT_LIST}"></c:set>
        <c:choose>
            <c:when test="${not empty pdList}">
                <a href="viewCart">View Your Cart</a></br>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Add to cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${pdList}" varStatus="counter">
                        <form action="addBook">                        
                            <tr>
                            <td>${counter.count}.</td>
                            <td>${dto.SKU}
                                <input type="hidden" name="txtSKU" value="${dto.SKU}" />
                            </td>
                            <td>${dto.name}
                                <input type="hidden" name="txtName" value="${dto.name}" />
                            </td>
                            <td>${dto.description}</td>
                            <td>${dto.price}
                                <input type="hidden" name="txtPrice" value="${dto.price}" />
                            </td>
                            <td>
                                <input type="submit" value="Add to cart" name="btAction" />
                            </td>
                        </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>                        
                </c:when>
            <c:otherwise>
                <h2>There is no items here! :<</h2>
            </c:otherwise>
        </c:choose>

    </body>
</html>
