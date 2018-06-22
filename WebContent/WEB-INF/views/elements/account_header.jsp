<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List, ep.event.model.User, ep.event.model.Event" %>
<jsp:useBean id="timenow" class="java.util.Date"/>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Event Prestige</title>
  <spring:url value="/assets/css/foundation.min.css" var="foundation" />
  <link rel="stylesheet" href="${foundation}">
  <spring:url value="/assets/css/fontawesome-all.min.css" var="fontawesome" />
  <link rel="stylesheet" href="${fontawesome}">
  
  <!-- LayerSlider CSS -->
  <spring:url value="/assets/layerslider/css/layerslider.css" var="layerslider" />
  <link rel="stylesheet" href="${layerslider}">
  <spring:url value="/assets/datatables/datatables.min.css" var="datatables" />
  <link rel="stylesheet" href="${datatables}">
  
  <spring:url value="/assets/datepicker-i/dist/datepicker.min.css" var="datepickCSS" />
  <link rel="stylesheet" href="${datepickCSS}">
  
  <spring:url value="/assets/datepicker/dist/Intimidatetime.min.css" var="datepickCSSI" />
  <link rel="stylesheet" href="${datepickCSSI}">
  
  <spring:url value="/assets/jquery-confirm/dist/jquery-confirm.min.css" var="confirmCSS" />
  <link rel="stylesheet" href="${confirmCSS}">
  
  <spring:url value="/assets/fancybox/dist/jquery.fancybox.min.css" var="fancyboxCSS" />
  <link rel="stylesheet" href="${fancyboxCSS}">
  
  <spring:url value="/assets/css/app.css" var="style" />
  <link rel="stylesheet" href="${style}">
</head>
<body>
	<% 
	  	User u = null;
	  	u = (User) session.getAttribute("userkey"); 
	  	
	%>
  <div class="header">
    <div class="grid-container">
      <div class="title-bar" data-responsive-toggle="responsive-menu" data-hide-for="medium">
        <button class="menu-icon dark" type="button" data-toggle="responsive-menu"></button>
        <div class="title-bar-title">Menu</div>
      </div>

      <div class="top-bar" id="responsive-menu">
        <div class="top-bar-right">
          <ul class="dropdown menu" data-dropdown-menu>
            <li><a href="${pageContext.request.contextPath}/${userkey.slug}"><i class="fas fa-home"></i></a></li>
            <li class="is-active"><a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/user/user-events">Events</a></li>
            <%-- <li><a href="${pageContext.request.contextPath}/user/banners">Banners</a></li> --%>
            <li class="spacer"></li>
            <li><a class="button sp" href="${pageContext.request.contextPath}/user/create-event">Create Event</a></li>
            <li class="has-submenu">
              <a class="spx" href="#">
              	<span class="mini-p-image">
              		<c:if test="${userkey.image != null}">
			      		<div class="card-image">
						  <img src="${pageContext.request.contextPath}/${user_path}/passport/${userkey.image}" />
						</div><br />
			      	</c:if>
              	</span> 
              	<span>${userkey.full_name} <i class="fas fa-cog"></i></span>
              </a>
              <ul class="submenu menu vertical" data-submenu>
                <li><a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/user/account-settings">Account settings</a></li>
                <li><a href="${pageContext.request.contextPath}/user/logout">Log out</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>