<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>comunTwist</title>
</head>
<body>
<div align="center">
    <input type="submit" value="Upload New Photo" onclick="window.location='/';" />
    <form action="/list" method="POST">
        <table>
            <c:forEach items="${photos_id}" var="id">
                <tr>
                    <td><input type="checkbox" name="check" value="${id}" id="${id}"/></td>
                    <td><c:out value="${id}"/></td>
                    <td><img src="/photo/${id}" width="10%" height="10%"/></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Delete Photos" name="del"/>
        <input type="submit" value="Photos to ZIP" name="zip"/>
    </form>
</div>
</body>
</html>