<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<spring:message code="testList.header.label" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTable">
								<thead>
									<tr>
										<th><spring:message code="testList.table.header.title" /></th>
										<th><spring:message code="testList.table.header.course" /></th>
										<th><spring:message code="testList.table.header.edit" /></th>
										<th><spring:message code="testList.table.header.addQuestion" /></th>
										<th><spring:message code="testList.table.header.remove" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${testList}" var="test">
										<tr class="odd gradeX">
											<td><c:url value="/test/show" var="testShow">
													<c:param name="id" value="${test.testId}" />
												</c:url> <a href="<c:out value='${testShow}'/>"><c:out value="${test.title}" /></a></td>
											<td><c:url value="/course/show" var="courseShow">
													<c:param name="id" value="${test.course.courseId}" />
												</c:url> <a href="<c:out value='${courseShow}'/>"><c:out value="${test.course.title}" /></a> <c:url value="/test/add" var="addTest">
													<c:param name="courseId" value="${test.course.courseId}" />
												</c:url>
												<div class="pull-right">
													<a href="<c:out value='${addTest}'/>"><span class="glyphicon glyphicon-plus"></span></a>
												</div></td>
											<td class="center"><c:url value="edit" var="edit">
													<c:param name="id" value="${test.testId}" />
												</c:url> <a href="<c:out value='${edit}'/>"><span class="glyphicon glyphicon-pencil"></span></a></td>
											<td class="center"><c:url value="/question/add" var="addQuestion">
													<c:param name="id" value="${test.testId}" />
												</c:url> <a href="<c:out value='${addQuestion}'/>"><span class="glyphicon glyphicon-plus"></span></a></td>
											<td class="center"><c:url value="remove" var="remove">
													<c:param name="id" value="${test.testId}" />
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