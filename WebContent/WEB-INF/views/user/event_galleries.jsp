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
        <li>
          <span class="show-for-sr">Current: </span> Galleries
        </li>
      </ul>
    </nav>
    <div class="large-12 dashboard-content">
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell medium-6">
              <h5><i class="fas fa-camera-retro fa-fw"></i> Galleries | <a href="${pageContext.request.contextPath}/user/user-events/${event_details.event_id}">${event_details.title}</a></h5>
            </div>
            <div class="cell medium-6 text-right">
            	<a class="button tiny success" href="${pageContext.request.contextPath}/user/create-gallery/${event_details.event_id}">Create Gallery</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/view-event/${event_details.event_id}">Go to Event</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/user-events">Back</a>
            </div>
          </div>
          <hr />
          <div class="large-12">
            <table id="table-1" class="display">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${galleries}" var="gallery">
						<tr>
	                        <td>#</td>
	                        <td><a href="${pageContext.request.contextPath}/user/view-gallery/${gallery.event_gallery_id}">${gallery.title}</a></td>
	                        <td>
	                          <a class="button tiny" href="${pageContext.request.contextPath}/user/view-gallery/${gallery.event_id}/${gallery.event_gallery_id}">View</a>
	                          <a class="button tiny" href="${pageContext.request.contextPath}/user/update-gallery/${gallery.event_id}/${gallery.event_gallery_id}">Edit</a>
	                          <a class="button tiny alert delete-confirm" href="${pageContext.request.contextPath}/user/delete-gallery/${gallery.event_id}/${gallery.event_gallery_id}" data-title="${gallery.title}">Delete</a>
	                          <a class="button tiny success" href="${pageContext.request.contextPath}/user/add-images/${gallery.event_id}/${gallery.event_gallery_id}">Add Images</a>
	                        </td>
	                    </tr>
					</c:forEach>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>