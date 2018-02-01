<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>comunTwist</title>
</head>
<body>
<div align="center">
    <h1>Secret page for admins only!</h1>
    <form action="/admin" method="POST">
        <table>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><input type="checkbox" name="check" value="${user.login}" id="${user.login}"/></td>
                    <td><c:out value="${user.login}"/></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Delete Users" name="del"/>
    </form>
    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>
</body>
</html>
