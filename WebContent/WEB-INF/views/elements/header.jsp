<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, ep.event.model.*" %>
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
	  User u = (User) session.getAttribute("userkey"); 
  	  String current_page = (String) request.getAttribute("current_page");
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
          	<% if(u.getSlug() != null){ %>
          			<li class="<% if(current_page == "home"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/<%= u.getSlug() %>">Home</a></li>
		            <li class="<% if(current_page == "events"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/events/<%= u.getSlug() %>">Events</a></li>
		            <li class="<% if(current_page == "about us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/about-us/<%= u.getSlug() %>">About Us</a></li>
		            <li class="<% if(current_page == "contact us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/contact-us/<%= u.getSlug() %>">Contact Us</a></li>
			        <c:if test="${user_info == null}">
			            <li><a class="button" href="${pageContext.request.contextPath}/create-account"><i class="far fa-paper-plane"></i> Get Started</a></li>
	          		</c:if>
            <% }else{ %>
            	<c:if test="${user_info != null}">
		            <li class="<% if(current_page == "home"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/${user_info.slug}">Home</a></li>
		            <li class="<% if(current_page == "events"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/events/${user_info.slug}">Events</a></li>
		            <li class="<% if(current_page == "about us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/about-us/${user_info.slug}">About Us</a></li>
		            <li class="<% if(current_page == "contact us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/contact-us/${user_info.slug}">Contact Us</a></li>
		            <li><a class="button" href="${pageContext.request.contextPath}/create-account"><i class="far fa-paper-plane"></i> Get Started</a></li>
	            </c:if>
	            <c:if test="${user_info == null}">
	            	<li class="<% if(current_page == "home"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/index">Home</a></li>
		            <li><a class="button" href="${pageContext.request.contextPath}/create-account"><i class="far fa-paper-plane"></i> Get Started</a></li>
	            </c:if>
            <% } %>
            <%-- <c:if test="${user_info == null}">
            	<li class="<% if(current_page == "home"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/index">Home</a></li>
	            <li class="<% if(current_page == "events"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/events">Events</a></li>
	            <li class="<% if(current_page == "about us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/about-us">About Us</a></li>
	            <li class="<% if(current_page == "contact us"){ out.print("is-active"); } %>"><a href="${pageContext.request.contextPath}/contact-us">Contact Us</a></li>
            </c:if> --%>
            <% if(u.getSlug() != null){ %>
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
            <% }else{ %>
            	<li><a class="button" href="${pageContext.request.contextPath}/login"><i class="fas fa-sign-in-alt"></i> Login</a></li>
            <% } %>
          </ul>
        </div>
      </div>
    </div>
  </div>