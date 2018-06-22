<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="elements/header.jsp"%>
<div class="main-content r-top-pad">
  	<c:if test="${current_event == null}">
	  	<div class="banner r-bg">
	  		<div style="background:url(assets/img/white-tint.png);">
	  			<div class="grid-container">
			      <div class="grid-x grid-padding-x align-middle" style="height:500px;">
			        <div class="cell large-4 white-tint padded">
			          <h3>${user_info.full_name}'s event page</h3>
			        </div>
			      </div>
			    </div>
	  		</div>
  	  	</div>
    </c:if>
    <c:if test="${current_event != null}">
      <div class="banner">
	    <div id="slider" style="width:1340px;height:500px;margin:0 auto;margin-bottom: 0px;">
	    	<c:if test="${banners.size() != 0}">
	    		<c:forEach items="${banners}" var="banner" varStatus="bannerCount">
	    			<div class="ls-slide" data-ls="duration:4000; transition2d:4; kenburnsscale:1.2;">
	    			  <c:if test="${bannerCount.count == 1}">
	    			  	<img width="100%" height="500" src="${pageContext.request.contextPath}/assets/img/black-tint.png" class="ls-l" alt="" style="top:50%; left:50%; text-align:initial; font-weight:400; font-style:normal; text-decoration:none; mix-blend-mode:normal; width:100%;" data-ls="showinfo:1; durationin:3000; easingin:easeOutExpo; scalexin:1.5; scaleyin:1.5; static:forever; position:fixed;">
	    			  </c:if>
			          <div style="left:0;text-transform:uppercase;" class="ls-l event-date" data-ls="offsetyin:-300; durationin:750; delayin:600; easingin:easeOutBack; rotatein:180; offsetyout:bottom; durationout:400; parallaxlevel:0;">
			            ${current_event_month}<br />
			            <span class="day">${current_event_day}</span><br />
			            ${current_event_year}<br />
			          </div>
			          <img src="${pageContext.request.contextPath}/uploads/${user_info.slug}/banners/${banner.image}" class="ls-bg" alt="" />
			          <div style="width:400px; top:50%; left:0; white-space:normal;" class="ls-l banner-caption" data-ls="offsetyin:-300; durationin:750; delayin:600; easingin:easeOutBack; rotatein:0; transformoriginin:50% 100% 0; offsetyout:bottom; durationout:400; parallaxlevel:0;">
			            <c:if test="${current_event.start_date > timenow}">
			            	<span class="label primary">Upcoming Event</span>
			            </c:if>
			            <c:if test="${current_event.start_date == timenow}">
			            	<span class="label alert">Event Today</span>
			            </c:if>
			            <c:if test="${current_event.start_date < timenow}">
			            	<span class="label secondary">Past Event</span>
			            </c:if>
			            <h3>${banner.title}</h3>
			            <span class="label secondary"><i class="far fa-clock"></i> ${current_event_time}</span><br /><br />
			            <p>${banner.description}</p>
			            <a href="${pageContext.request.contextPath}/event-details/${user_info.slug}/${current_event.event_id}">read more...</a>
			         </div>
			      </div>
	    		</c:forEach>
	    	</c:if>
	    	<c:if test="${banners.size() == 0}">
	    		<div class="ls-slide" data-ls="duration:4000; transition2d:4; kenburnsscale:1.2;">
		          <img width="100%" height="500" src="assets/img/black-tint.png" class="ls-l" alt="" style="top:50%; left:50%; text-align:initial; font-weight:400; font-style:normal; text-decoration:none; mix-blend-mode:normal; width:100%;" data-ls="showinfo:1; durationin:3000; easingin:easeOutExpo; scalexin:1.5; scaleyin:1.5; static:forever; position:fixed;">
		          <div style="left:0;text-transform:uppercase;" class="ls-l event-date" data-ls="offsetyin:-300; durationin:750; delayin:600; easingin:easeOutBack; rotatein:180; offsetyout:bottom; durationout:400; parallaxlevel:0;">
		            ${current_event_month}<br />
		            <span class="day">${current_event_day}</span><br />
		            ${current_event_year}<br />
		          </div>
		          <div style="width:400px; top:50%; left:0; white-space:normal;" class="ls-l banner-caption" data-ls="offsetyin:-300; durationin:750; delayin:600; easingin:easeOutBack; rotatein:0; transformoriginin:50% 100% 0; offsetyout:bottom; durationout:400; parallaxlevel:0;">
		            <c:if test="${current_event.start_date > timenow}">
		            	<span class="label primary">Upcoming Event</span>
		            </c:if>
		            <c:if test="${current_event.start_date == timenow}">
		            	<span class="label alert">Event Today</span>
		            </c:if>
		            <c:if test="${current_event.start_date < timenow}">
		            	<span class="label secondary">Past Event</span>
		            </c:if>
		            <h3>${current_event.title}</h3>
		            <span class="label secondary"><i class="far fa-clock"></i> ${current_event_time}</span><br /><br />
		            <a href="${pageContext.request.contextPath}/event-details/${user_info.slug}/${current_event.event_id}">read more...</a>
		          </div>
		        </div>
	    	  </c:if>
          	  <%-- <c:if test="${current_event.image != null}">
             	<img class="ls-l" src="${pageContext.request.contextPath}/uploads/${user_info.slug}/medium/${current_event.image}" style="top:50%; left:920px; text-align:initial; font-weight:400; font-style:normal; text-decoration:none; mix-blend-mode:normal; max-width:400px !important; height:auto; max-height:450px;" data-ls="showinfo:1; offsetxin:-800 ; delayin:200; easingout:easeInOutBack; rotateyout:360; transformperspectiveout:1000; parallax:true; parallaxlevel:8;" />
              </c:if> --%>
	     </div>
	  </div>
	</c:if>
  <div class="content">
    <div class="grid-container">
      <c:if test="${current_event != null}">
	      <div class="grid-x grid-padding-x">
	          <div class="cell large-10">
	            <div class="grid-x grid-margin-x">
	              <div class="cell large-7 event-content">
	              	<c:if test="${current_event.start_date > timenow}">
	            		<span class="label primary">Upcoming Event</span><br /><br />
		            </c:if>
		            <c:if test="${current_event.start_date == timenow}">
		            	<span class="label alert">Event Today</span><br /><br />
		            </c:if>
		            <c:if test="${current_event.start_date < timenow}">
		            	<span class="label secondary">Past Event</span><br /><br />
		            </c:if>
	                <h6>DESCRIPTION</h6>
	                <p>
	                  ${current_event.description}
	                </p><br />
	                <h6>DATE & TIME</h6>
		            <p>
		              ${current_event.start_date}
		            </p>
		
		            <h6>LOCATION</h6>
		            <p>
		              ${current_event.address}, ${current_event.city}, ${current_event.state}, ${current_event.country}
		            </p>
		            <a href="${pageContext.request.contextPath}/event-details/${user_info.slug}/${current_event.event_id}">read more...</a>
	              </div>
	              <div class="cell large-5">
	                <c:if test="${current_event.image != null}">
	              		<img src="${pageContext.request.contextPath}/uploads/${user_info.slug}/medium/${current_event.image}" />
	              	</c:if>
	              </div>
	            </div>
	          </div>
	      </div>
      </c:if>
      <c:if test="${current_event == null}">
      	<div class="callout">No events available for ${user_info.full_name}</div>
      </c:if>
    </div>
  </div>
  <div class="content r-top-pad">
  	<c:if test="${events != null}">
	    <div class="grid-container text-left">
	      <h6>OTHER EVENTS</h6><br />
	      <div class="grid-x grid-margin-x">
	      	<c:forEach items="${events}" var="event">
	          <c:if test="${current_event.event_id != event.event_id}">
      			<a href="${pageContext.request.contextPath}/event-details/${user_info.slug}/${event.event_id}" class="cell small-12 medium-4 large-3 event-item">
		            <div class="event-image">
		              <c:if test="${event.image != null}">
		              		<img src="${pageContext.request.contextPath}/uploads/${user_info.slug}/small/${event.image}" />
		              </c:if>
		              <c:if test="${event.image == null}">
		              	<i class="fas fa-history fa-6x"></i>
		              </c:if>
		            </div>
		            <div class="event-desc">
		              <c:if test="${event.start_date > timenow}">
			            <span class="label primary">Upcoming Event</span>
			          </c:if>
			          <c:if test="${event.start_date == timenow}">
			            <span class="label alert">Event Today</span>
			          </c:if>
			          <c:if test="${event.start_date < timenow}">
			          	<span class="label secondary">Past Event</span>
			          </c:if>
		              <span class="date">${event.start_date}</span>
		              <span class="title">${event.title}</span>
		              <span class="location"><i class="fas fa-map-marker-alt"></i> ${event.address}</span>
		            </div>
		          </a>
	      		</c:if>
			</c:forEach>
	      </div>
	      <div class="large-12 text-right">
			<a href="${pageContext.request.contextPath}/events/${user_info.slug}">more..</a>     
	      </div>
	    </div>
    </c:if>
  </div>
</div>
<%@ include file="elements/footer.jsp"%>