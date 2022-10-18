<%-- 
    Document   : createAccount
    Created on : Jun 22, 2022, 7:57:58 AM
    Author     : DELL
--%>

<%@page import="lamvnt.registration.RegistrationCreateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createAccount" method="POST"> 
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"></c:set>
            
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> 6-20 </br>
            <c:if test="${not empty errors.userameLengthErr}">
                <font color="red">
                    ${errors.userameLengthErr}
                    </font></br>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /> 6-30 </br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                </font></br>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> </br>
            <c:if test="${not empty errors.confirmNotMathched}">
                <font color="red">
                    ${errors.confirmNotMathched}
                </font></br>
            </c:if>
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />  2-50 </br>
            <c:if test="${not empty errors.fullnamLengthErr}">
                <font color="red">
                    ${errors.fullnamLengthErr}
                </font></br>
            </c:if>
            <input type="submit" value="Create a new account" name="btAction" />
            <input type="reset" value="Reset" /></br>
            
        </form>
    </body>
</html>
