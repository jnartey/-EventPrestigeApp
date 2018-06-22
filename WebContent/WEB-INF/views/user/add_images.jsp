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
        <li><a href="${pageContext.request.contextPath}/user/event-galleries/${event_details.event_id}">Galleries</a></li>
        <li><a href="${pageContext.request.contextPath}/user/view-gallery/${event_details.event_id}/${event_gallery.event_gallery_id}">${event_gallery.title}</a></li>
        <li>
          <span class="show-for-sr">Current: </span> Add Images
        </li>
      </ul>
    </nav>
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell medium-12">
              <h5><i class="far fa-images"></i> Add Images | <a href="${pageContext.request.contextPath}/user/view-gallery/${event_details.event_id}/${event_gallery.event_gallery_id}">${event_gallery.title}</a></h5>
            </div>
         </div>
       </div>
     </div>
    <hr />
    <div class="large-12">
      <div id="status-message"></div>
      <form id="dropzone" action="${pageContext.request.contextPath}/user/process-add-images/${event_details.event_id}/${event_gallery.event_gallery_id}" method="post" enctype="multipart/form-data">
          <div class="large-12 text-right button-area">
          	<!-- <input type="submit" id="uploadimages" class="button" name="add_images" value="Upload Images"> -->
       	  	<a class="button" href="${pageContext.request.contextPath}/user/view-gallery/${event_details.event_id}/${event_gallery.event_gallery_id}">Cancel</a>
          </div>
          <div class="success progress" role="progressbar" tabindex="0" aria-valuemin="0" aria-valuemax="100" data-dz-uploadprogress>
			  <div class="progress-meter">
			      <span class="progress-meter-text"></span>
			  </div>
		  </div>
          <div class="upload-area">
          	<div class="dz-message" data-dz-message><span>Drop your files here<br /><i class="fas fa-upload"></i></span></div>
          	<div id="dropzonePreview"></div>
          	<div class="fallback">
		    	<input name="files" type="file" multiple />
		  	</div>
          </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>
<script>
$(document).ready(function() {
	$("form#dropzone").dropzone({
		// url does not has to be written if we have wrote action in the form tag but i have mentioned here just for convenience sake 
	    addRemoveLinks: true,
	    autoProcessQueue: true, // this is important as you dont want form to be submitted unless you have clicked the submit button
	    autoDiscover: false,
	    paramName: 'files', // this is similar to giving name to the input type file like <input type="file" name="files" />
	    previewsContainer: '#dropzonePreview', // we specify on which div id we must show the files
	    clickable: false, // this tells that the dropzone will not be clickable . we have to do it because v dont want the whole form to be clickable 
	    accept: function(file, done) {
	      //console.log("uploaded");
	      done();
	    },
	    totaluploadprogress: function(progress) {
	        $('.progress-meter').css('width', progress + '%');
	        $('.progress-meter-text').html(progress + '%');
	    },
	    error: function(file, msg){
	       alert(msg);
	    },
	    init: function() {
	        var myDropzone = this;
	      	//now we will submit the form when the button is clicked
	      	
	        document.getElementById("uploadimages").onclick = function(e) {
		        e.preventDefault(); //this will prevent the default behaviour of submit button because we want dropzone to submit the form
		        myDropzone.processQueue(); // this will submit your form to the specified action which directs to your jsp upload code
		        // after this, your whole form will get submitted with all the inputs + your files and the jsp code will remain as usual 
		  		//REMEMBER you DON'T have to call ajax or anything by yourself to submit the inputs, dropzone will take care of that
		      };

	    }, // init end
	    successmultiple: function() {
			$("#status-message").html("<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Images uploaded successfully</div>");
		}
	});
});
</script>