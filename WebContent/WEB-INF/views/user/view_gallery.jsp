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
        <li>
          <span class="show-for-sr">Current: </span> ${gallery_details.title}
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
            	<a class="button tiny success" href="${pageContext.request.contextPath}/user/add-images/${gallery_details.event_id}/${gallery_details.event_gallery_id}">Add Images</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/view-event/${event_details.event_id}">Go to Event</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/event-galleries/${gallery_details.event_id}">Back</a>
            </div>
          </div>
          <hr />
          <div class="large-12">
          	<p>${gallery_details.description}</p>
          	<c:if test="${images != null}" >
          		<div class="grid-x grid-margin-x">
				 	<c:forEach items="${images}" var="image">
				 		<div class="cell small-12 medium-4 large-3 event-wrap">
					 		<a href="#" class="event-item">
								<c:if test="${image.image == null}" >
									<div class="event-image">
					                  <i class="fas fa-history fa-6x"></i>
					                </div>
								</c:if>
				                <c:if test="${image.image != null}">
				                	<div class="event-image">
				                		<img src="${pageContext.request.contextPath}/${image_path}/small/${image.image}" />
				                	</div>
								</c:if>
				                <div class="event-desc">
				                  <span class="title">${image.title}</span>
				                </div>
				            </a>
				            <a class="delete-image image-icon delete-confirm" href="${pageContext.request.contextPath}/user/delete-image/${gallery_details.event_id}/${gallery_details.event_gallery_id}/${image.event_image_id}" data-title="${image.title}"><i class="far fa-trash-alt"></i></a>
				            <a class="edit-image image-icon" href="${pageContext.request.contextPath}/user/edit-image/${gallery_details.event_id}/${gallery_details.event_gallery_id}/${image.event_image_id}"><i class="far fa-edit"></i></a>
				 		</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${images == null}" >
			 	<div class="callout text-center">
			 		No images available for ${gallery_details.title} 
			 		<a href="${pageContext.request.contextPath}/user/add-images/${gallery_details.event_id}/${gallery_details.event_gallery_id}">click here to add images</a>
			 	</div>
			</c:if> 
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>