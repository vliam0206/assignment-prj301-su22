<%-- 
    Document   : search
    Created on : Jun 8, 2022, 7:45:00 AM
    Author     : LamVo
--%>

<%--<%@page import="lamvnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%--tglb--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
         <%--
        <% /* CHECK COOKIES */
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                if (!lastCookie.getName().equals("JSESSIONID")) {
        %>
                                        
        <%
                }// end cooky is not JSESSIONID                                      
            }// end cooky has existed
        %>
        --%>
        <!-- LOG OUT link -->
        <c:url var="urlLogOut" value="logOut">
        </c:url>
        <a href="${urlLogOut}">Log Out</a> </br>
        
       <!-- WELCOME -->
        <h2 style="color: red">
            Welcome, ${sessionScope.USER.fullName}
        </h2>
        
        <!-- SEARCH PAGE-->
        <h1>Search Account Page</h1>        
        <form action="searchAccount">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue.trim()}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
            
        <c:set var="searchValue" value="${param.txtSearchValue.trim()}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccount">
                        
                            <tr>
                                <th>
                                    ${counter.count}.
                                </th>
                                <th>
                                    ${dto.username}
                                    <input type="hidden" name="txtUserName" value="${dto.username}" />
                                </th>
                                <th>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </th>
                                <th>
                                    ${dto.fullName}
                                </th>
                                <th>
                                    ${dto.role}
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </th>
                                <th>                                        
                                    <c:url var="deleteLink" value="deleteAccount">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </th>
                                <th>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </th>
                            </tr>
                        </form>    
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>No record matches!</h2>
            </c:if>
        </c:if>
                
    </body>
</html>
