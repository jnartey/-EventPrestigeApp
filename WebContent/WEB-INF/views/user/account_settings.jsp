<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <div class="cell large-12">
      <h5><i class="far fa-user"></i> Account Details</h5>
    </div>
    <hr />
    <div class="pad-l-r">
      ${message}
      <form action="${pageContext.request.contextPath}/user/upload-photo" method="post"  enctype="multipart/form-data" class="grid-x grid-padding-x align-middle" modelAttribute="user">
        <div class="cell large-12">
          <nav aria-label="You are here:" role="navigation">
          <ul class="breadcrumbs">
            <li><a href="dashboard.html">Dashboard</a></li>
            <li>
              <span class="show-for-sr">Current: </span> Account Settings
            </li>
          </ul>
        </nav>
        </div>
        <div class="cell shrink">
          <div class="image-placeholder">
            <c:if test="${user_details.image != null}">
	      		<div class="card-image">
				  <img src="${pageContext.request.contextPath}/${user_path}/passport/${user_details.image}" />
				</div><br />
	      	</c:if>
          </div>
        </div>
        <div class="cell auto">
          <h6>Profile photo</h6>
          <a href="#"><i class="far fa-trash-alt"></i> Remove</a><br />
          <label for="FileUpload" class="button">Choose Photo</label>
          <input type="file" id="FileUpload" name="profile_photo" class="show-for-sr">
          <input type="submit" class="button" name="update_photo" value="Upload">
        </div>
    </form>
    </div>
    <hr />
    <div class="pad-l-r">
      <form action="update-account" method="post" modelAttribute="user" class="grid-x grid-padding-x align-middle" data-abide novalidate>
        <div class="cell large-6">
          <label for="full_name">Full Name</label>
          <input type="text" id="full_name" name="full_name" placeholder="Full Name" value="${user_details.full_name}" required/>
          <span class="form-error">
	         Full Name is required
	      </span>
        </div>
        <div class="cell large-6">
          <label for="email">Email</label>
          <input type="text" id="email" name="email" placeholder="Email" value="${user_details.email}" readonly/>
        </div>
        <div class="cell large-6">
          <label for="dob">Date of Birth</label>
          <input type="text" id="dob" name="dob" placeholder="Date of Birth" data-toggle="datepicker-i" value="${user_details.dob}" required />
          <span class="form-error">
	         Date of birth is required
	      </span>
        </div>
        <div class="cell large-6">
          <label for="phone">Phone</label>
          <input type="text" id="phone" name="phone" placeholder="Phone" value="${user_details.phone}" />
        </div>
        <div class="cell large-6">
          <label for="address">Street Address</label>
          <input type="text" id="address" name="address" placeholder="Address" value="${user_details.address}" />
        </div>
        <div class="cell large-6">
          <label for="cityId">City</label>
          <!-- <select id="city" name="city">
            <option value="">-- City --</option>
          </select> -->
          <input type="text" id="city" name="city" placeholder="City" value="${user_details.city}" />
        </div>
        <div class="cell large-6">
          <label for="stateId">State</label>
          <!-- <select id="state" name="state">
            <option value="">-- State --</option>
          </select> -->
          <input type="text" id="state" name="state" placeholder="State" value="${user_details.state}" />
        </div>
        <div class="cell large-6">
          <label for="countryId">Country</label>
          <!-- <select id="country" name="country">
            <option value="">-- Select Country --</option>
          </select> -->
          <input type="text" id="country" name="country" placeholder="Country" value="${user_details.country}" />
        </div>
        <div class="cell large-12 text-right">
          <input type="submit" class="button" name="update_info" value="Update">
        </div>
      </form>
    </div>
    <hr />
    <div class="pad-l-r">
      <form action="update-password" method="post" class="grid-x grid-padding-x align-middle" modelAttribute="user" data-abide novalidate>
        <div class="cell large-12"><h5>Change Password</h5><br /></div>
        <div class="cell large-3">
          <label for="password">Old Password</label>
          <input type="password" id="password" name="password" placeholder="Old Password" required />
          <span class="form-error">
	         Password is required
	      </span>
        </div>
        <div class="cell large-3">
          <label for="new_password">New Password</label>
          <input type="password" id="new_password" name="new_password" placeholder="New Password" required />
          <span class="form-error">
	         New Password is required
	      </span>
        </div>
        <div class="cell large-3">
          <label for="con_new_password">Confirm New Password</label>
          <input type="password" id="con_new_password" name="con_new_password" placeholder="Confirm New Password" required data-equalto="new_password" />
          <div class="form-error">
            Hey, passwords are supposed to match!
          </div>
        </div>
        <div class="cell large-3 pad-button">
          <input type="submit" class="button" name="update_password" value="Change">
        </div>
      </form>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>