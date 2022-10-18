<%-- 
    Document   : login
    Created on : Jul 14, 2022, 1:22:39 AM
    Author     : DELL
--%>

<%@page import="lamvnt.registration.RegistrationLoginError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.LOGIN_ERR}"></c:set>

            <h1>Login Page</h1>
            <form action="login" method="POST">
                Username <input type="text" name="txtUsername" value="${param.txtUsername.trim()}" /></br>
            <c:if test="${not empty error.userameEmptyErr}">
                <font color="red">${error.userameEmptyErr}</font></br>
            </c:if>
                
            Password <input type="password" name="txtPassword" value="" /></br>
            <c:if test="${not empty error.passwordEmptyErr}">
                <font color="red">${error.passwordEmptyErr}</font></br>
            </c:if>

            <c:if test="${not empty error.accountNotFound}">
                <font color="red">${error.accountNotFound}</font></br>
            </c:if>
                
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" /></br>                            
        </form></br>       
        
        <a href="showProductList">Click here to buy book</a></br>
        <a href="createAccountHtml">Click here to create a new account</a>
    </body>
</html>
