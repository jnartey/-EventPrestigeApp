<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <h3><i class="fas fa-toolbox"></i> Dashboard</h3><br />
    <nav aria-label="You are here:" role="navigation">
      <ul class="breadcrumbs">
        <li><a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/user/user-events">Events</a></li>
        <li><a href="${pageContext.request.contextPath}/user/event-banners/${banner_details.event_id}">Banners</a></li>
        <li>
          <span class="show-for-sr">Current: </span> Create Banner
        </li>
      </ul>
    </nav>
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell medium-12">
              <h5><i class="far fa-images"></i> Add Banner | <a href="${pageContext.request.contextPath}/user/user-events/${event_details.event_id}">${event_details.title}</a></h5>
            </div>
         </div>
       </div>
     </div>
    <hr />
    <div class="pad-l-r">
      <form class="grid-x grid-padding-x align-middle" action="${pageContext.request.contextPath}/user/process-create-banner/${event_details.event_id}" method="post"  enctype="multipart/form-data" data-abide novalidate>
      	<div class="cell large-12 text-right">
        	<a class="button" href="${pageContext.request.contextPath}/user/event-banners/${event_details.event_id}">Cancel</a>
        </div>
        <div class="cell large-12">
          <label for="title">Title</label>
          <input type="text" id="title" name="title" placeholder="Title" value="${eventBanner.title}" required />
          <span class="form-error">
          	Title is required
          </span>
        </div>
        <div class="cell large-12">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Description">${eventBanner.description}</textarea>
        </div>
        <div class="cell large-12">
          <label for="FileUpload" class="button">Add Banner Image</label>
          <input type="file" id="FileUpload" name="bannerimage" class="show-for-sr">
        </div>
        <div class="cell large-12 text-right">
          <a class="button" href="${pageContext.request.contextPath}/event-banners/${event_details.event_id}">Cancel</a>
          <input type="submit" class="button" name="create_gallery" value="Create Banner">
        </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>