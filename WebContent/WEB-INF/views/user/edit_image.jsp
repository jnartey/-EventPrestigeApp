<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <h3><i class="fas fa-toolbox"></i> Dashboard</h3><br />
    <nav aria-label="You are here:" role="navigation">
      <ul class="breadcrumbs">
        <li><a href="dashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/user/user-events">Events</a></li>
        <li><a href="${pageContext.request.contextPath}/user/event-galleries">Galleries</a></li>
        <li><a href="${pageContext.request.contextPath}/user/view-gallery/${gallery_details.event_id}/${gallery_details.event_gallery_id}">${gallery_details.title}</a></li>
        <li>
          <span class="show-for-sr">Current: </span> ${image_details.title}
        </li>
      </ul>
    </nav>
    <div class="large-12 dashboard-content">
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell medium-6">
              <h5>
              	<i class="fas fa-camera-retro fa-fw"></i> 
              	Gallery | <a href="${pageContext.request.contextPath}/user/view-gallery/${gallery_details.event_id}/${gallery_details.event_gallery_id}">${gallery_details.title}</a>
              </h5>
            </div>
            <div class="cell medium-6 text-right">
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/view-event/${event_details.event_id}">Go to Event</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/view-gallery/${gallery_details.event_id}/${gallery_details.event_gallery_id}">Cancel</a>
            </div>
          </div>
          <hr />
          <div class="large-12">
          	<form action="${pageContext.request.contextPath}/user/process-update-image/${gallery_details.event_id}/${gallery_details.event_gallery_id}/${image_details.event_image_id}" method="post" class="grid-x grid-padding-x align-middle" enctype="multipart/form-data"  modelAttribute="eventImage" data-abide novalidate>
          		<div class="cell large-12">
		          <label for="title">Title</label>
		          <input type="text" id="title" name="title" placeholder="Title" value="${image_details.title}" required />
		          <span class="form-error">
		          	Title is required
		          </span>
		        </div>
		        <div class="cell large-12 text-left">
		        	<c:if test="${image_details.image != null}">
			      		<div class="card-image">
						  <img src="${pageContext.request.contextPath}/${image_path}/${image_details.image}" /><br /><br />
						</div>
			      	</c:if>
		          <label for="FileUpload" class="button">Change Image</label>
		          <input type="file" id="FileUpload" name="imageupload" value="${image_details.image}" class="show-for-sr">
		          <input type="submit" class="button success" name="edit_image" value="Update Image">
		          <a class="button" href="${pageContext.request.contextPath}/user/view-gallery/${gallery_details.event_id}/${gallery_details.event_gallery_id}">Cancel</a>
		        </div>
          	</form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>