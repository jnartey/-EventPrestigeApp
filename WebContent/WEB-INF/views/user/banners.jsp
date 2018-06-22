<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../elements/account_header.jsp"%>
<div class="account-content">
  <div class="grid-container">
    <h3><i class="fas fa-toolbox"></i> Dashboard</h3><br />
    <nav aria-label="You are here:" role="navigation">
      <ul class="breadcrumbs">
        <li><a href="dashboard">Dashboard</a></li>
        <li>
          <span class="show-for-sr">Current: </span> Banners
        </li>
      </ul>
    </nav>
    <div class="large-12 dashboard-content">
      ${message}
      <div class="grid-x grid-margin-x">
        <div class="cell large-12 updates">
          <div class="grid-x">
            <div class="cell small-6">
              <h5><i class="far fa-calendar-alt"></i> All Banners</h5>
            </div>
          </div>
          <hr />
          <div class="large-12">
            
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../elements/footer.jsp"%>