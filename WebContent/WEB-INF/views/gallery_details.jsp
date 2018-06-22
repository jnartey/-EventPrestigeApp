<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="elements/header.jsp"%>
<div class="main-content r-top-pad">
  <div class="banner">
  	<c:if test="${event_details == null}">
  		<div style="background:url(${pageContext.request.contextPath}/assets/img/white-tint.png);">
  			<div class="grid-container">
		      <div class="grid-x grid-padding-x align-middle" style="height:500px;">
		        <div class="cell large-4 white-tint padded">
		          <h3>${user_info.full_name}'s event page</h3>
		        </div>
		      </div>
		    </div>
  		</div>
    </c:if>
    <c:if test="${event_details != null}">
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
	    </div>
	</c:if>
  </div>
  <div class="content">
    <div class="grid-container">
      <nav aria-label="You are here:" role="navigation">
         <ul class="breadcrumbs">
           <li><a href="${pageContext.request.contextPath}/events/${user_info.slug}">Events</a></li>
           <li><a href="${pageContext.request.contextPath}/event-details/${user_info.slug}/${event_details.event_id}">${event_details.title}</a></li>
           <li>
             <span class="show-for-sr">Current: </span> ${gallery_details.title}
           </li>
         </ul>
      	</nav>
        <div class="grid-x grid-margin-x">
          <div class="cell large-12">
            <div class="grid-x grid-margin-x">
              <div class="cell large-12 event-content">
                <h6>DESCRIPTION</h6>
                <p>
                  ${gallery_details.description}
                </p>
              </div>
            </div>
          </div>
        </div>
    </div>
  
  	<c:if test="${images != null}">
	    <div class="grid-container text-left">
	      <div class="grid-x">
	      	<c:forEach items="${images}" var="image">
     		  <a data-fancybox="${gallery_details.title}" href="${pageContext.request.contextPath}/uploads/${user_folder}/galleries/${image.image}" class="cell small-6 medium-3 large-2 event-item r-margin">
	            <div class="gallery-image">
	              <c:if test="${image.image != null}">
	              		<img src="${pageContext.request.contextPath}/uploads/${user_folder}/galleries/small/${image.image}" />
	              </c:if>
	            </div>
	          </a>
			</c:forEach>
	      </div>
	    </div>
    </c:if>
  </div>
</div>
<%@ include file="elements/footer.jsp"%>