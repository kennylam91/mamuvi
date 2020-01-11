<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Mamuvi</title>
<script src="<c:url value='/static/angular/angular.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<%-- <script src="<c:url value='/static/js/service' />"></script> --%>
<script src="<c:url value='/static/js/controller/testController.js' />"></script>
</head>
<body ng-controller="TestController as ctrl">
	Welcome to mamuvi {{ctrl.welcome}}
	<input ng-model="ctrl.welcome" />

</body>
</html>