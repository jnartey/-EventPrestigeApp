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
        <li><a href="${pageContext.request.contextPath}/user/event-banners">Banners</a></li>
        <li>
          <span class="show-for-sr">Current: </span> ${banner_details.title}
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
              	<i class="far fa-images"></i>
              	Banner | <a href="${pageContext.request.contextPath}/user/view-banner/${banner_details.event_id}/${banner_details.banner_id}">${banner_details.title}</a>
              </h5>
            </div>
            <div class="cell medium-6 text-right">
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/view-event/${event_details.event_id}">Go to Event</a>
            	<a class="button tiny success" href="${pageContext.request.contextPath}/user/update-banner/${event_details.event_id}/${banner_details.banner_id}">Edit Banner</a>
            	<a class="button tiny" href="${pageContext.request.contextPath}/user/event-banners/${banner_details.event_id}">Back</a>
            </div>
          </div>
          <hr />
          <div class="large-12">
          	<p>${banner_details.description}</p>
	      	<c:if test="${banner_details.image != null}">
	      		<div class="card-image">
				  <img src="${pageContext.request.contextPath}/${user_path}/banners/${banner_details.image}" />
				</div><br />
	      	</c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>