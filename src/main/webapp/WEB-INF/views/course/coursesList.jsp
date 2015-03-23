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
					<spring:message code="coursesList.header.label" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="coursesList.panel.title" />
						</h3>
					</div>
					<c:if test="${not empty error}">
						<div class="alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${error}
						</div>
					</c:if>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th><spring:message code="coursesList.table.header.title" /></th>
										<th><spring:message code="coursesList.table.header.author" /></th>
										<th><spring:message code="coursesList.table.header.createdDate" /></th>
										<th><spring:message code="coursesList.table.header.access" /></th>
										<th><spring:message code="coursesList.table.header.showMembers" /></th>
										<th><spring:message code="coursesList.table.header.actions" /></th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${courseList}" var="course">
										<tr>
											<td><c:url value="show" var="show">
													<c:param name="id" value="${course.courseId}" />
												</c:url> <a href="<c:out value='${show}'/>"><c:out value="${course.title}" /></a></td>
											<td><c:url value="/user/show" var="showUser">
													<c:param name="id" value="${course.user.userId}" />
												</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${course.user.name}" /> <c:out value="${course.user.surname}" /></a></td>
											<td><fmt:formatDate value="${course.createdAt}" pattern="yyyy/MM/dd" /></td>
											<td><c:choose>
													<c:when test="${course.password eq ''}">
														<spring:message code="coursesList.table.header.access.public" />
													</c:when>
													<c:otherwise>
														<spring:message code="coursesList.table.header.access.private" />
													</c:otherwise>
												</c:choose></td>
											<c:choose>
												<c:when test="${(isMember || course.isMember) || course.user.email == pageContext.request.userPrincipal.name}">
													<td><c:url value="/group/list" var="groupList">
															<c:param name="id" value="${course.courseId}" />
														</c:url> <a href="<c:out value='${groupList}'/>"><input name="submit" class="btn btn-primary btn-sm" type="submit"
															value="<spring:message code="coursesList.table.showUsers" />" /></a></td>
												</c:when>
												<c:otherwise>
													<td><span class="glyphicon glyphicon-ban-circle"></span></td>
												</c:otherwise>
											</c:choose>
											<!-- JOIN TO COURSE -->
											<td><c:choose>
													<c:when test="${!isMember && !course.isMember && course.user.email != pageContext.request.userPrincipal.name}">
														<c:url value="join" var="join">
															<c:param name="id" value="${course.courseId}" />
														</c:url>
														<a href="<c:out value='${join}'/>"> <input name="submit" class="btn btn-success btn-sm" type="submit"
															value="<spring:message code="coursesList.table.join" />" />
														</a>
													</c:when>
												</c:choose> <c:choose>
													<c:when test="${course.user.email == pageContext.request.userPrincipal.name}">
														<c:url value="edit" var="edit">
															<c:param name="id" value="${course.courseId}" />
														</c:url>
														<a href="<c:out value='${edit}'/>"> <span class="glyphicon glyphicon-pencil"></span>
														</a>
														<c:url value="changeActive" var="changeActive">
															<c:param name="id" value="${course.courseId}" />
														</c:url>
														<a href="<c:out value='${changeActive}'/>"> 
															<c:choose>
																<c:when test="${course.active}">
																	<span class="glyphicon glyphicon-stop"></span>
																</c:when>
																<c:otherwise>
																	<span class="glyphicon glyphicon-play"></span>
																</c:otherwise>
															</c:choose>
														</a>
														<c:url value="/material/list" var="showMaterials">
															<c:param name="id" value="${course.courseId}" />
														</c:url>
														<a href="<c:out value='${showMaterials}'/>"> <span class="glyphicon glyphicon-list"></span>
														</a>
														<c:url value="remove" var="remove">
															<c:param name="id" value="${course.courseId}" />
														</c:url>
														<a href="<c:out value='${remove}'/>"><span class="glyphicon glyphicon-trash"></span> </a>
													</c:when>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>