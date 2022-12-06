<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>userPage</title>
</head>
<body>

<select id="languagesSelect" onchange="selectLanguage()">
    <option value="en" selected>English</option>
    <option value="ru">Русский</option>
</select>

<div class="en">
    <h1>Our rooms</h1>
    <c:forEach var="room" items="${sessionScope.rooms}">
        <c:out value="${room.getId()}"/>
        <c:out value="${room.getNumber()}"/>
        <c:choose>
            <c:when test="${room.isReserved()}">
                <form method="post" style="border: red solid 2px">
                    <input type="hidden" name="roomId" value="${room.getId()}">
                    <button type="submit" disabled>Can't be reserved</button>
                </form>
            </c:when>
            <c:otherwise>
                <form method="post" style="border: green solid 2px" action="Controller">
                    <input name="COMMAND" value="RESERVE_ROOM">
                    <input type="hidden" name="roomId" value="${room.getId()}">
                    <button type="submit">Reserve</button>
                </form>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <h1>Your ordered rooms</h1>

    <c:set var="orders" value="${sessionScope.orders}"/>
    <c:forEach var="order" items="${orders}">
        <div style="border: blue solid 2px">
            <c:out value="Order id - ${order.getOrderId()}"/>
            <c:out value="User id - ${order.getUserId()}"/>
            <c:out value="Room id - ${order.getRoomId()}"/>
            <form action="Controller" type="post">
                <input type="hidden" name="COMMAND" value="DELETE_RESERVATION"/>
                <input type="hidden" name="orderId" value="${order.getOrderId()}"/>
                <input type="hidden" name="roomId" value="${order.getRoomId()}"/>
                <button type="submit">Delete reservation</button>
            </form>

        </div>
    </c:forEach>

    <form action="Controller" method="post">
        <input type="hidden" name="COMMAND" value="LOG_OUT_COMMAND">
        <button type="submit">Log out</button>
    </form>
</div>

<div class="ru">
    <h1>Наши номера</h1>
    <c:forEach var="room" items="${sessionScope.rooms}">
        <c:out value="${room.getId()}"/>
        <c:out value="${room.getNumber()}"/>
        <c:choose>
            <c:when test="${room.isReserved()}">
                <form method="post" style="border: red solid 2px">
                    <input type="hidden" name="roomId" value="${room.getId()}">
                    <button type="submit" disabled>Уже забронировано</button>
                </form>
            </c:when>
            <c:otherwise>
                <form method="post" style="border: green solid 2px" action="Controller">
                    <input name="COMMAND" value="RESERVE_ROOM">
                    <input type="hidden" name="roomId" value="${room.getId()}">
                    <button type="submit">Забронировать</button>
                </form>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <h1>Ваши заказанные номера</h1>

    <c:set var="orders" value="${sessionScope.orders}"/>
    <c:forEach var="order" items="${orders}">
        <div style="border: blue solid 2px">
            <c:out value="Order id - ${order.getOrderId()}"/>
            <c:out value="User id - ${order.getUserId()}"/>
            <c:out value="Room id - ${order.getRoomId()}"/>
            <form action="Controller" type="post">
                <input type="hidden" name="COMMAND" value="DELETE_RESERVATION"/>
                <input type="hidden" name="orderId" value="${order.getOrderId()}"/>
                <input type="hidden" name="roomId" value="${order.getRoomId()}"/>
                <button type="submit">Удалить бронь</button>
            </form>

        </div>
    </c:forEach>

    <form action="Controller" method="post">
        <input type="hidden" name="COMMAND" value="LOG_OUT_COMMAND">
        <button type="submit">Выйти</button>
    </form>
</div>
<script src="js/translator.js"></script>
</body>
</html>
