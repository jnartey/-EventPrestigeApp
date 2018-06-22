<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="footer white-tint">
  <div class="grid-container">
    <p>&copy; 2018 Event Pretige inc. All rights reserved | <a href="#">Terms & Conditions</a></p>
  </div>
</div>
<spring:url value="/assets/js/vendor/jquery.js" var="jQuery" />
<script src="${jQuery}"></script>
<spring:url value="/assets/js/vendor/what-input.js" var="whatInput" />
<script src="${whatInput}"></script>
<spring:url value="/assets/js/vendor/foundation.min.js" var="foundationJs" />
<script src="${foundationJs}"></script>

<!-- External libraries: jQuery & GreenSock -->
<spring:url value="/assets/layerslider/js/greensock.js" var="greensock" />
<script src="${greensock}"></script>

<!-- LayerSlider script files -->
<spring:url value="/assets/layerslider/js/layerslider.transitions.js" var="layersliderTransitions" />
<script src="${layersliderTransitions}"></script>
<spring:url value="/assets/layerslider/js/layerslider.kreaturamedia.jquery.js" var="layerslider" />
<script src="${layerslider}"></script>
<spring:url value="/assets/datatables/datatables.min.js" var="datatableJs" />
<script src="${datatableJs}"></script>
<spring:url value="/assets/datepicker/dist/Intimidatetime.min.js" var="datepickerJSI" />
<script src="${datepickerJSI}"></script>
<spring:url value="/assets/datepicker-i/dist/datepicker.min.js" var="datepickerJS" />
<script src="${datepickerJS}"></script>
<spring:url value="/assets/jquery-confirm/dist/jquery-confirm.min.js" var="confirmJS" />
<script src="${confirmJS}"></script>
<spring:url value="/assets/js/dropzone.js" var="dropzone" />
<script src="${dropzone}"></script>
<spring:url value="/assets/fancybox/dist/jquery.fancybox.min.js" var="fancyboxJS" />
<script src="${fancyboxJS}"></script>
<spring:url value="/assets/js/app.js" var="mainScripts" />
<script src="${mainScripts}"></script>