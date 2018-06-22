<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="elements/header.jsp"%>
<div class="grid-x grid-padding-x align-center-middle login-content">
    <form id="custom-form" class="cell large-3" action="login" method="post" data-abide novalidate>
  	  ${message}
      <div class="input-group">
        <span class="input-group-label"><i class="far fa-envelope fa-fw"></i></span>
        <input type="text" id="email" name="email" placeholder="Email" required pattern="email" />
        <span class="form-error">
            Email is required
        </span>
      </div>
      <div class="input-group">
        <span class="input-group-label"><i class="fas fa-key fa-fw"></i></span>
        <input type="password" id="password" name="password" placeholder="Password" required/>
        <span class="form-error">
            Password is required
        </span>
      </div>
      <input type="submit" name="login" class="button" value="Login" />
    </form>
</div>
<%@ include file="elements/footer.jsp"%>