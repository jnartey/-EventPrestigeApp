<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <div class="cell large-12">
      <h5><i class="far fa-calendar-alt"></i> Create Event</h5>
    </div>
    <hr />
    <div class="pad-l-r">
      ${message}
      <form class="grid-x grid-padding-x align-middle" action="${pageContext.request.contextPath}/user/create-event" method="post" enctype="multipart/form-data" data-abide novalidate>
      	<div class="cell large-12 text-right">
        	<a class="button" href="user-events">Cancel</a>
        </div>
      	<div class="cell large-12">
      		<nav aria-label="You are here:" role="navigation">
	          <ul class="breadcrumbs">
	            <li><a href="dashboard">Dashboard</a></li>
	            <li><a href="user-events">Events</a></li>
	            <li>
	              <span class="show-for-sr">Current: </span> Create Event
	            </li>
	          </ul>
	        </nav>
      	</div>
        <div class="cell large-12">
          <label for="title">Title</label>
          <input type="text" id="title" name="title" placeholder="Title" required />
          <span class="form-error">
          	Title is required
          </span>
        </div>
        <div class="cell large-12">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Description"></textarea>
        </div>
        <div class="cell large-6">
          <label for="start_date">Date & Time</label>
          <input type="text" id="start_date" name="start_date" placeholder="Date" data-toggle="datepicker" autocomplete="off" required />
          <span class="form-error">
          	Date & Time is required
          </span>
        </div>
        <div class="cell large-6">
          <label for="address">Street Address</label>
          <input type="text" id="address" name="address" placeholder="Address" />
        </div>
        <div class="cell large-6">
          <label for="cityId">City</label>
          <!-- <select id="city" name="city">
            <option value="">-- City --</option>
          </select> -->
          <input type="text" id="city" name="city" placeholder="City" />
        </div>
        <div class="cell large-6">
          <label for="stateId">State</label>
          <!-- <select id="state" name="state">
            <option value="">-- State --</option>
          </select> -->
          <input type="text" id="state" name="state" placeholder="State" />
        </div>
        <div class="cell large-6">
          <label for="countryId">Country</label>
          <!-- <select id="country" name="country">
            <option value="">-- Select Country --</option>
          </select> -->
          <input type="text" id="country" name="country" placeholder="Country" />
        </div>
        <div class="cell large-12 text-left">
          <label for="FileUpload" class="button">Choose Event Poster</label>
          <input type="file" id="FileUpload" name="image" class="show-for-sr">
        </div>
        <div class="cell large-12 text-right">
          <a class="button" href="user-events">Cancel</a>
          <input type="submit" class="button" name="create_event" value="Create Event">
        </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>