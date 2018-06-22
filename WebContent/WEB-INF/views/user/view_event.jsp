<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <div class="cell large-12">
      <h5><i class="far fa-calendar-alt"></i> Update Event</h5>
    </div>
    <hr />
    <div class="pad-l-r">
    	<div id="status-message"></div>
    	<div class="grid-x grid-margin-x">
    		<div class="cell large-12 text-right">
		       	<a class="button tiny" href="${pageContext.request.contextPath}/user/user-events">Back</a>
		        <a class="button tiny" href="${pageContext.request.contextPath}/user/edit-event/${event_details.event_id}">Edit</a>
		        <a class="button tiny alert delete-confirm" href="pageContext.request.contextPath}/user/delete-event/${event_details.event_id}" data-title="${event_details.title}">Delete</a>
		        <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Galleries</a>
		        <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-banners/${event_details.event_id}">Event Banners</a>
		      </div>
		    	<div class="cell large-12">
		    		<nav aria-label="You are here:" role="navigation">
			         <ul class="breadcrumbs">
			           <li><a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a></li>
			           <li><a href="${pageContext.request.contextPath}/user/user-events">Events</a></li>
			           <li>
			             <span class="show-for-sr">Current: </span> ${event_details.title}
			           </li>
			         </ul>
			      	</nav>
		    	</div>
		      <div class="cell large-12">
		        <h3>Title</h3>
		        <h5>${event_details.title}</h5><br />
		      </div>
		      <div class="cell large-12">
		        <h3>Description</h3>
		        <p>${event_details.description}</p><br />
		      </div>
		      <div class="cell large-6">
		        <h3>Date</h3>
		        <span class="secondary label">${event_details.start_date}</span><br /><br />
		      </div>
		      <div class="cell large-6">
		        <h3>Street Address</h3>
		        <span class="secondary label">${event_details.address}</span><br /><br />
		      </div>
		      <div class="cell large-6">
		        <h3>City</h3>
		        <span class="secondary label">${event_details.city}</span><br /><br />
		      </div>
		      <div class="cell large-6">
		        <h3>State</h3>
		        <span class="secondary label">${event_details.state}</span><br /><br />
		      </div>
		      <div class="cell large-6">
		        <h3>Country</h3>
		        <span class="secondary label">${event_details.country}</span><br /><br />
		      </div>
		      <div class="cell large-12">
		      	<c:if test="${event_details.image != null}">
		      		<div class="card-image">
					  <img src="${pageContext.request.contextPath}/${user_path}/${event_details.image}" />
					</div><br />
		      	</c:if>
		      </div>
		      <div class="cell large-12 text-left">
		        <a class="button tiny" href="${pageContext.request.contextPath}/user/user-events">Back</a>
		        <a class="button tiny" href="${pageContext.request.contextPath}/user/edit-event/${event_details.event_id}">Edit</a>
		        <a class="button tiny alert delete-confirm" href="${pageContext.request.contextPath}/user/delete-event/${event_details.event_id}" data-title="${event_details.title}">Delete</a>
		        <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Galleries</a>
		        <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-banners/${event_details.event_id}">Event Banners</a>
		      </div>
    	</div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>