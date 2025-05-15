<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Репетиторы</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>

<h1>Добавить репетитора</h1>
<div>
    <form class="content-form" action="${pageContext.request.contextPath}/tutors" method="post">
        Имя: <input type="text" name="firstName" required><br>
        Фамилия: <input type="text" name="lastName" required><br>
        Предмет: <input type="text" name="subject" required><br>
        Опыт (лет): <input type="number" name="experienceYears" required><br>
        Стоимость/час: <input type="number" step="0.01" name="hourlyRate" required><br>
        Телефон: <input type="text" name="phoneNumber" required><br>
        <input class="btn" type="submit" value="Добавить">
    </form>
</div>
<c:if test="${empty tutors and empty param.redirected}">
    <c:redirect url="/tutors?redirected=true"/>
</c:if>
<h2>Список репетиторов</h2>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Предмет</th>
        <th>Опыт (лет)</th>
        <th>Стоимость/час</th>
        <th>Телефон</th>
    </tr>
    <c:forEach var="tutor" items="${tutors}">
        <tr>
            <td>${tutor.firstName}</td>
            <td>${tutor.lastName}</td>
            <td>${tutor.subject}</td>
            <td>${tutor.experienceYears}</td>
            <td>${tutor.hourlyRate}</td>
            <td>${tutor.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>