<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <h3><i class="fas fa-toolbox"></i> Dashboard</h3><br />
    <div class="callout">
    	Copy to share manually or click the link below to share by email<br />
		<a href="mailto:recipients?subject=${userkey.full_name}'s Event page link&body=Link: ${user_link}"><i class="fas fa-share-alt"></i> ${user_link}</a>
    </div>
    ${message}
    <br />
    <div class="large-12 dashboard-content">
      <div class="grid-x grid-margin-x">

        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell small-6">
              <h5><i class="far fa-calendar-alt"></i> Recent Events</h5>
            </div>
            <div class="cell small-6 text-right">
              <a class="button tiny" href="${pageContext.request.contextPath}/user/user-events">VIEW ALL EVENTS</a>
            </div>
          </div>
          <hr />
          <div class="large-12">
            <table id="table-1" class="display">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${events}" var="event">
						<tr>
	                        <td>#</td>
	                        <td>
	                        	<a href="${pageContext.request.contextPath}/user/view-event/${event.event_id}">${event.title}</a>
	                        	<c:if test="${event.start_date > timenow}">
						           <span class="label primary">Upcoming Event</span>
						        </c:if>
						        <c:if test="${event.start_date == timenow}">
						           <span class="label alert">Event Today</span>
						        </c:if>
						        <c:if test="${event.start_date < timenow}">
						           <span class="label secondary">Past Event</span>
						        </c:if>
	                        </td>
	                        <td>${event.start_date}</td>
	                        <td>
	                          <a class="button tiny" href="${pageContext.request.contextPath}/user/view-event/${event.event_id}">View</a>
	                          <a class="button tiny" href="${pageContext.request.contextPath}/user/edit-event/${event.event_id}">Edit</a>
	                          <a class="button tiny alert delete-confirm" href="${pageContext.request.contextPath}/user/delete-event/${event.event_id}" data-title="${event.title}">Delete</a>
	                          <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-galleries/${event.event_id}">Event Galleries</a>
	                          <a class="button tiny success" href="${pageContext.request.contextPath}/user/event-banners/${event.event_id}">Event Banners</a>
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