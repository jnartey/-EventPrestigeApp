<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="elements/header.jsp"%>
<div class="grid-x grid-padding-x align-center-middle create-account">
    <div class="cell large-5 info-container">
      <h2>Create Account</h2>
      <p>All fields marked <span class="mandatory">*</span> are mandatory</p>
    </div>
    <div class="cell large-12"></div>
   
    <form id="custom-form" class="cell large-5" action="create-user" method="post" modelAttribute="user" data-abide novalidate>
      <div class="input-group">
        <span class="input-group-label"><i class="far fa-user-circle fa-fw"></i> <span class="mandatory">*</span></span>
        <input type="text" id="full_name" name="full_name" placeholder="Full Name" required />
        <span class="form-error">
           Full Name is required
        </span>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="far fa-envelope fa-fw"></i> <span class="mandatory">*</span></span>
        <input type="email" id="email" name="email" placeholder="Email" required pattern="email" /><br />
        <span class="form-error">
           Email is required
        </span>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="fas fa-key fa-fw"></i> <span class="mandatory">*</span></span>
        <input type="password" id="password" name="password" placeholder="Password" required />
        <span class="form-error">
           Password is required
        </span>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="fas fa-key fa-fw"></i> <span class="mandatory">*</span></span>
        <input type="password" id="conpassword" name="conpassword" placeholder="Confirm Password" required data-equalto="password" /><br />
        <div class="form-error">
            Hey, passwords are supposed to match!
        </div>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="far fa-calendar-alt fa-fw"></i> <span class="mandatory">*</span></span>
        <input type="text" id="dob" name="dob" placeholder="Date of Birth" data-toggle="datepicker-i" autocomplete="off" required />
        <span class="form-error">
           Date of Birth is required
        </span>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="fas fa-phone fa-fw"></i> &nbsp;</span>
        <input type="text" id="phone" name="phone" placeholder="Phone" />
      </div>
      <input type="hidden" id="user_role" name="user_role" value="2" />
      <input type="submit" name="login" class="button" value="Create Account" />
    </form>
</div>
<%@ include file="elements/footer.jsp"%>