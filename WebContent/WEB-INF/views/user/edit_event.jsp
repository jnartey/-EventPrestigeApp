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
      ${message}
      <form action="${pageContext.request.contextPath}/user/update-event" method="post" class="grid-x grid-padding-x align-middle" enctype="multipart/form-data" data-abide novalidate>
      	<div class="cell large-12 text-right">
        	<a class="button" href="${pageContext.request.contextPath}/user/user-events">Cancel</a>
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
          <label for="title">Title</label>
          <input type="text" id="title" name="title" placeholder="Title" value="${event_details.title}" required />
          <span class="form-error">
          	Title is required
          </span>
        </div>
        <div class="cell large-12">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Description">${event_details.description}</textarea>
        </div>
        <div class="cell large-6">
          <label for="start_date">Date & Time</label>
          <input type="text" id="start_date" name="start_date" placeholder="Date" data-toggle="datepicker" autocomplete="off" value="${event_details.start_date}" required />
          <span class="form-error">
          	Date & Time is required
          </span>
        </div>
        <div class="cell large-6">
          <label for="address">Street Address</label>
          <input type="text" id="address" name="address" placeholder="Address" value="${event_details.address}" />
        </div>
        <div class="cell large-6">
          <label for="cityId">City</label>
          <!-- <select id="city" name="city">
            <option value="">-- City --</option>
          </select> -->
          <input type="text" id="city" name="city" placeholder="City" value="${event_details.city}" />
        </div>
        <div class="cell large-6">
          <label for="stateId">State</label>
          <!-- <select id="state" name="state">
            <option value="">-- State --</option>
          </select> -->
          <input type="text" id="state" name="state" placeholder="State" value="${event_details.state}" />
        </div>
        <div class="cell large-6">
          <label for="countryId">Country</label>
          <!-- <select id="country" name="country">
            <option value="">-- Select Country --</option>
          </select> -->
          <input type="text" id="country" name="country" placeholder="Country" value="${event_details.country}" />
          <br />
        </div>
        <div class="cell large-12 text-left">
        	<c:if test="${event_details.image != null}">
	      		<div class="card-image">
				  <img src="${pageContext.request.contextPath}/${user_path}/${event_details.image}" />
				</div><br />
	      	</c:if>
          <label for="FileUpload" class="button">Choose Event Poster</label>
          <input type="file" id="FileUpload" name="image" class="show-for-sr">
        </div>
        <div class="cell large-12 text-right">
          <input type="hidden" id="event_id" name="event_id" value="${event_details.event_id}" />
          <a class="button" href="${pageContext.request.contextPath}/user/user-events">Cancel</a>
          <input type="submit" class="button" name="edit_event" value="Update Event">
        </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>