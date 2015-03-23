<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="utf-8">
<title>Internetowa platforma kursów online</title>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.css" rel="stylesheet">

<!-- Page-Level Plugin CSS - Dashboard -->
<link href="<%=request.getContextPath()%>/resources/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/plugins/timeline/timeline.css" rel="stylesheet">

<!-- SB Admin CSS -->
<link href="<%=request.getContextPath()%>/resources/css/sb-admin.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/datepicker.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/datepicker3.css" rel="stylesheet">
<!-- Select2-3.4.8 CSS -->
<link href="<%=request.getContextPath()%>/resources/js/select2/select2.css" rel="stylesheet">
</head>
<body>
<div id="switcher">
		<div class="center">
			<a id="theme_select" target="_blank" href="<%=request.getContextPath()%>"> <spring:message
							code="baseLayout.header.title" /></a>
			
			<ul>
				<tiles:insertAttribute name="topNavigation" />
			</ul>
		</div>
	</div>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=request.getContextPath()%>"><spring:message code="baseLayout.header.title" /></a>
			</div>
		</nav>
		<tiles:insertAttribute name="leftMenuBar" />
		<div id="page-wrapper">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

	<!-- Core Scripts - Include with every page -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<!-- Select2-3.4.8 CSS -->
	<script src="<%=request.getContextPath()%>/resources/js/select2/select2.min.js"></script>


	<!-- Page-Level Plugin Scripts - Dashboard -->
	<script src="<%=request.getContextPath()%>/resources/js/plugins/morris/raphael-2.1.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/plugins/morris/morris.js"></script>

	<!-- Page-Level Plugin Scripts - Tables -->
	<script src="<%=request.getContextPath()%>/resources/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- SB Admin Scripts - Include with every page -->
	<script src="<%=request.getContextPath()%>/resources/js/sb-admin.js"></script>

	<!-- Page-Level Demo Scripts - Dashboard - Use for reference -->
	<script src="<%=request.getContextPath()%>/resources/js/demo/dashboard-demo.js"></script>
	
	<!-- BOOTSTRAP FILE INPUT -->
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.file-input.js"></script>
		
</body>
</html>
