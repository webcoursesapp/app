<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<spring:message code="materialsList.header.label" /> <c:out value="${course.title}" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
<!-- 						<h3 class="panel-title"> -->
<%-- 							<spring:message code="materialsList.panel.header.label" /> --%>
<!-- 						</h3> -->
<!-- 						<br /> -->
						<c:url value="add" var="add">
							<c:param name="courseId" value="${course.courseId}" />
						</c:url>
						<a href="<c:out value='${add}'/>"> <input name="submit" class="btn btn-success btn-sm" type="submit"
							value="<spring:message code="addMaterial.form.item.addMaterialSubmit" />" />
						</a>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTable">
								<thead>
									<tr>
										<th><spring:message code="materialsList.table.header.title" /></th>
										<th><spring:message code="materialsList.table.header.description" /></th>
										<th><spring:message code="materialsList.table.header.course" /></th>
										<th><spring:message code="materialsList.table.header.file" /></th>
										<th><spring:message code="materialsList.table.header.edit" /></th>
										<th><spring:message code="materialsList.table.header.remove" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${materials}" var="material">
										<tr class="odd gradeX">
											<td><c:out value="${material.title}" /></td>
											<td><c:out value="${fn:substring(material.description,0,100)}"></c:out>...</td>
											<td><c:url value="/course/show" var="courseShow">
													<c:param name="id" value="${material.course.courseId}" />
												</c:url> <a href="<c:out value='${courseShow}'/>"><c:out value="${material.course.title}" /></a></td>
											<td><c:out value="${material.orgFileName}" /></td>
											<td class="center"><c:url value="edit" var="edit">
													<c:param name="id" value="${material.materialId}" />
												</c:url> <a href="<c:out value='${edit}'/>"><span class="glyphicon glyphicon-pencil"></span></a></td>
											<td class="center"><c:url value="remove" var="remove">
													<c:param name="id" value="${material.materialId}" />
												</c:url> <a href="<c:out value='${remove}'/>"><span class="glyphicon glyphicon-trash"></span></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>