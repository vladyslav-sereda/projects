<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title><fmt:message key='admin.management'/></title>
<body>
<template:main>

    <jsp:body>

        <div class="row">
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <div class="collapsible-header">
                        <fmt:message key='admin.reservations'/>
                    </div>
                    <div class="collapsible-body">
                        <div id="schedule-table" class="row container">
                            <ul id="swipe-dates" class="tabs">
                                <c:forEach items="${sessionScope.get('workdays')}" var="day">
                                    <li class="tab col s2">
                                        <a id="day${day.id}" class="workday-date" href="#time${day.id}">${day.date}</a>
                                    </li>
                                </c:forEach>
                                <li class="tab col s2">
                                    <a class="waves-effect btn teal accent-2" id="add-day-btn"
                                       data-target="day-modal" onclick="openDayModal()">
                                        <fmt:message key='admin.addDay'/>
                                    </a>
                                </li>
                            </ul>
                            <c:forEach items="${sessionScope.workdays}" var="day">
                                <div id="time${day.id}" class="col s12">
                                    <table class="striped">
                                        <thead>
                                        <tr>
                                            <th><fmt:message key='main_schedule.startTime'/></th>
                                            <th><fmt:message key='main_schedule.endTime'/></th>
                                            <th><fmt:message key='admin.price'/></th>
                                            <th><fmt:message key='admin.showUser'/></th>
                                        </tr>
                                        </thead>
                                        <tbody id="newReservationList${day.id}">
                                        <c:forEach items="${sessionScope.reservations}" var="mapReservations">
                                            <c:if test="${day.id == mapReservations.key}">
                                                <c:forEach items="${mapReservations.value}" var="reservs">
                                                    <tr>
                                                        <td>${reservs.startTime}</td>
                                                        <td>${reservs.endTime}</td>
                                                        <td>${reservs.price}</td>
                                                        <td>
                                                            <c:forEach items="${sessionScope.users}" var="users">
                                                                <c:if test="${users.id == reservs.userId}">
                                                                    <a class="waves-effect  btn teal accent-2 show-user-btn"
                                                                       data-target="sh-user-modal"
                                                                       onclick='openUserForm(${day.id})'>
                                                                        <fmt:message key='admin.showUserBtn'/>
                                                                    </a>
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="collapsible-header">
                        <fmt:message key='admin.unpaidResrv'/>
                    </div>
                    <div class="collapsible-body">
                        <table class="striped">
                            <thead>
                            <tr>
                                <th><fmt:message key='main_schedule.startTime'/></th>
                                <th><fmt:message key='main_schedule.endTime'/></th>
                                <th><fmt:message key='admin.price'/></th>
                                <th><fmt:message key='admin.showUser'/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.unpaidReservs}" var="reservs">
                                <c:if test="${reservs.status=='UNPAID'}">
                                    <tr>
                                        <td>${reservs.startTime}</td>
                                        <td>${reservs.endTime}</td>
                                        <td>${reservs.price}</td>
                                        <td>
                                            <a class="waves-effect btn teal accent-2" id="chenge-status-btn"
                                               data-target="status-modal" onclick="changeStatus(${reservs})">
                                                <fmt:message key='admin.confirmed'/>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </li>
            </ul>
        </div>

        <div id="createAppResponseModal" class="modal bottom-sheet">
            <div class="modal-content">
                <h5><fmt:message key='admin.choose_room'/></h5>
                <form id="search-form" name="search" onchange="searchRooms()" class="validate">
                    <div class="row">

                        <div class="input-field col s4 m3 l2">
                            <i class="material-icons prefix">perm_identity</i>
                            <select id="search-capacity">
                                <option value="" disabled selected>Number of guests:</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <label for="search-capacity"></label>
                        </div>

                        <div class="input-field col s4 m3 l2">
                            <i class="material-icons prefix">stars</i>
                            <select id="search-classOfRoom">
                                <option value="" disabled selected><fmt:message key='form.class_of_room'/></option>
                                <option value="ECONOMIC"><fmt:message key='class_of_room.economic'/></option>
                                <option value="STANDARD"><fmt:message key='class_of_room.standard'/></option>
                                <option value="FAMILY"><fmt:message key='class_of_room.family'/></option>
                                <option value="HONEYMOON"><fmt:message key='class_of_room.honeymoon'/></option>
                                <option value="DE_LUX"><fmt:message key='class_of_room.delux'/></option>
                                <option value="BUSINESS"><fmt:message key='class_of_room.business'/></option>
                                <option value="PRESIDENT"><fmt:message key='class_of_room.president'/></option>
                            </select>
                        </div>

                        <div class="input-field col s6 l2 m3">
                            <i class="material-icons prefix">find_replace</i>
                            <select id="sortBy">
                                <option value="up"><fmt:message key='form.sort_by_price_up_down'/></option>
                                <option value="down"><fmt:message key='form.sort_by_price_down_up'/></option>
                                <option value="rating"><fmt:message key='form.sort_by_rating'/></option>
                            </select>
                        </div>

                        <div class="input-field col s6 l6 m3">
                            <i class="material-icons prefix">attach_money</i>
                            <input id="search-max-price" name="maxPrice"
                                   type="range" min="50" max="5000" value="5000">
                        </div>
                        <span class="right">Set max price: <span id='price-value'></span> $</span>
                    </div>
                    <input id="search-from-date" type="hidden">
                    <input id="search-to-date" type="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <div class="indigo lighten-4 container">
                    <table class="highlight fixed" id="rooms">
                        <col width="150px"/>
                        <col/>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>


        <div id="confirmPaymentModal" class="modal">
            <div class="modal-content">
                <h4 class="center"><fmt:message key='admin.confirm_payment'/></h4>
                <input type="hidden" id="confirm-reserve-id">
                <input type="hidden" id="confirm-reserve-row">

            </div>
            <div class="modal-footer">
                <a class="waves-effect waves-light btn orange right"
                   id="submit-registration" onclick="confirmPayment()"><i
                        class="material-icons left">done</i><fmt:message key='admin.yes'/></a>
                <a class="waves-effect waves-light btn white orange-text right modal-close"><i
                        class="material-icons left">cancel</i><fmt:message key='admin.no'/></a>
            </div>
        </div>
    </jsp:body>

</template:main>
</body>
</html>
