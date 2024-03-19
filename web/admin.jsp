<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator</title>
    </head>
    <body>
        <h1>Hello Admin!</h1>
        <h3>User Management</h3>
        <form action="main">
            Search user <input type="text" name="keyword" value="${keywork}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>UserID</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Phone</th>
                    <th>RoleID</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${USER_LIST}">
                    <tr>
                        <td>${o.userId}</td>
                        <td>${o.name}</td>
                        <td>${o.password}</td>
                        <td>${o.phone}</td>
                        <td>${o.roleId}</td>
                        <td>${o.status ? "Active" : "Inactive"}</td>
                        <td>
                            <a href="/main?action=Block&userId=${o.userId}&keyword=${keyword}">Block</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
