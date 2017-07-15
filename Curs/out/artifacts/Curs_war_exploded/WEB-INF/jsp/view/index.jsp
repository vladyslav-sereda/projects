<%--
  Created by IntelliJ IDEA.
  User: sered
  Date: 10.05.2017
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html"
         pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/myStyle.css"/>
</head>
<title>Photostudio</title>
<body>
<template:main>

    <jsp:body>

        <div class="parallax-container">
            <div class="parallax ">
                <img src="resources/images/mainView1.jpg">
            </div>
        </div>

        <div class="section white">
            <div class="row container">
                <h2 class="header"><fmt:message key='main.welcome'/></h2>
                <c:if test="${sessionScope.containsKey('user')}">
                    <p class="grey-text text-darken-3 lighten-3">
                        <fmt:message key='main.dear_friend'/>
                        <a href="#schedule">
                            <fmt:message key='main.schedule'/>
                            <fmt:message key='main.and'/>
                        </a>
                        <fmt:message key='main.hope'/>
                    </p>
                </c:if>

                <c:if test="${!sessionScope.containsKey('user')}">
                    <p class="grey-text text-darken-3 lighten-3">
                        <fmt:message key='main.dear_friend'/>
                        <a href="#schedule">
                            <fmt:message key='main.schedule'/>
                        </a>
                        <fmt:message key='main.more_features'/>
                    </p>
                </c:if>
            </div>
        </div>

        <div class="parallax-container">
            <div class="parallax">
                <img src="resources/images/mainView2.jpg">
            </div>
        </div>

        <div class="section white" id="schedule">
            <div class="row container">
                <h2 class="header indigo-text text-lighten-2">
                    <fmt:message key='main_schedule.header'/>
                </h2>
                <p class="grey-text text-darken-3 lighten-3">
                    <fmt:message key='main_schedule.here_you_can'/>
                </p>
            </div>
            <div id="schedule-table" class="row container">
                <ul id="swipe-dates" class="tabs">
                    <c:forEach items="${requestScope.get('workdays')}" var="day">
                        <li class="tab col s2">
                            <a id="day${day.id}" class="workday-date" href="#time${day.id}" onclick="closeReserveForm()">${day.date}</a>
                        </li>
                    </c:forEach>
                </ul>
                <c:forEach items="${requestScope.workdays}" var="day">
                    <div id="time${day.id}" class="col s12">
                        <table class="striped" >
                            <thead>
                            <tr>
                                <th><fmt:message key='main_schedule.startTime'/></th>
                                <th><fmt:message key='main_schedule.endTime'/></th>
                                <th><fmt:message key='main_schedule.status'/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.mapFreeTime}" var="freeTime">
                                <c:if test="${day.id==freeTime.key}">
                                    <c:forEach items="${freeTime.value}" var="time">
                                        <tr>
                                            <td>${time.startTime}</td>
                                            <td>${time.endTime}</td>
                                            <td>
                                                <c:if test="${!sessionScope.containsKey('user')}">
                                                    <a class="waves-effect waves-light btn teal accent-2" id="login-btn"
                                                       data-target="login-modal" onclick="openLoginModal()">
                                                        <fmt:message key='main_schedule.statusBtn'/>
                                                    </a>
                                                </c:if>
                                                <c:if test="${sessionScope.containsKey('user')}">
                                                    <a class="waves-effect waves-light btn teal accent-2 reservation-btn"
                                                       data-target="login-modal" onclick='openReserveForm(${day.id})'>
                                                        <fmt:message key='main_schedule.statusBtn'/>
                                                    </a>
                                                </c:if>
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
            <div id="reservation-form" class="container" style="display: none;">
                <form id="reserv-form" name="reservation">
                    <div class="row">

                        <div id="dateReservation" class="col s6">
                            <span><fmt:message key='form.freeTime'/></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">query_builder</i>
                            <input id="from-time" name="fromTime" type="time"
                                   class="timepicker" placeholder="start">
                            <label for="from-time"></label>
                        </div>

                        <div class="input-field col s6">
                            <i class="material-icons prefix">query_builder</i>
                            <input id="to-time" name="toTime" type="time"
                                   class="timepicker" placeholder="end">
                            <label for="to-time"></label>
                        </div>

                    </div>
                    <span id="new-reserv-error" class="error"></span><br>

                    <a class="waves-effect waves-light btn amber"
                       id="submit-registration" onclick="addReserve(${sessionScope.user.id})"><i
                            class="material-icons left">done</i><fmt:message key='main_reserv.add'/></a>
                    <a class="waves-effect waves-light btn white amber-text"
                       onclick="closeReserveForm()"><i class="material-icons left">cancel</i>
                        <fmt:message key='form.cancel'/></a>
                </form>
            </div>
        </div>

        <div class="parallax-container">
            <div class="parallax">
                <img src="resources/images/mainView3.jpg">
            </div>
        </div>

    </jsp:body>
</template:main>
</body>
</html>