<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../elements/header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <h3><i class="fas fa-toolbox"></i> Dashboard</h3><br />
    <nav aria-label="You are here:" role="navigation">
      <ul class="breadcrumbs">
        <li><a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/user/user-events">Events</a></li>
        <li><a href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Galleries</a></li>
        <li>
          <span class="show-for-sr">Current: </span> Edit Gallery
        </li>
      </ul>
    </nav>
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell medium-12">
              <h5>
              	<i class="fas fa-camera-retro fa-fw"></i> Add Gallery | 
              	<a href="${pageContext.request.contextPath}/user/edit-gallery/${event_details.event_id}/${event_gallery.event_gallery_id}">${event_gallery.title}</a></h5>
            </div>
         </div>
       </div>
     </div>
    <hr />
    <div class="pad-l-r">
      <form class="grid-x grid-padding-x align-middle" action="${pageContext.request.contextPath}/user/process-update-gallery/${event_details.event_id}/${event_gallery.event_gallery_id}" method="post" enctype="multipart/form-data" data-abide novalidate>
      	<div class="cell large-12 text-right">
        	<a class="button" href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Cancel</a>
        </div>
        <div class="cell large-12">
          <label for="title">Title</label>
          <input type="text" id="title" name="title" placeholder="Title" value="${event_gallery.title}" required/>
          <span class="form-error">
          	Title is required
          </span>
        </div>
        <div class="cell large-12">
          <label for="description">Description</label>
          <textarea id="description" name="description" placeholder="Description">${event_gallery.description}</textarea>
        </div>
        <div class="cell large-12 text-right">
          <a class="button" href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Cancel</a>
          <input type="submit" class="button" name="update_gallery" value="Update Gallery">
        </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>