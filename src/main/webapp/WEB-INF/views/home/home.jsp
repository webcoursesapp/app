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

				<br />

			</div>
		</div>
		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
			zalogowany
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-lg-8">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<spring:message code="home.lastAddedCourses.title" />
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-hover">
										<tbody>
											<c:forEach items="${lastAddedCourses}" var="course">
												<tr>
													<td><c:url value="/course/show" var="show">
															<c:param name="id" value="${course.courseId}" />
														</c:url> <a href="<c:out value='${show}'/>"><c:out value="${course.title}" /></a></td>
													<td><c:url value="/user/show" var="showUser">
															<c:param name="id" value="${course.user.userId}" />
														</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${course.user.name}" /> <c:out value="${course.user.surname}" /></a></td>
													<td><fmt:formatDate value="${course.createdAt}" pattern="yyyy/MM/dd" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.table-responsive -->
							</div>
						</div>

					</div>
					<div class="col-lg-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<spring:message code="home.statistics.title" />
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="list-group">
									<a href="#" class="list-group-item"> <i class="fa fa-comment fa-fw"></i> <c:out value="${statistic.courses}" /> <spring:message
											code="home.statistics.courses" /> <span class="pull-right text-muted small"></span>
									</a> <a href="#" class="list-group-item"> <i class="fa fa-twitter fa-fw"></i> <c:out value="${statistic.materials}" /> <spring:message
											code="home.statistics.materials" /> <span class="pull-right text-muted small"></span>
									</a> <a href="#" class="list-group-item"> <i class="fa fa-envelope fa-fw"></i> <c:out value="${statistic.messages}" /> <spring:message
											code="home.statistics.messages" /> <span class="pull-right text-muted small"> </span>
									</a> <a href="#" class="list-group-item"> <i class="fa fa-envelope fa-fw"></i> <c:out value="${statistic.users}" /> <spring:message
											code="home.statistics.users" /> <span class="pull-right text-muted small"> </span>
									</a> <a href="#" class="list-group-item"> <i class="fa fa-tasks fa-fw"></i> <c:out value="${statistic.tests}" /> <spring:message
											code="home.statistics.tests" /> <span class="pull-right text-muted small"> </span>
									</a> <a href="#" class="list-group-item"> <i class="fa fa-tasks fa-fw"></i> <c:out value="${statistic.homeworks}" /> <spring:message
											code="home.statistics.homeworks" /> <span class="pull-right text-muted small"> </span>
									</a>
								</div>
							</div>
							<!-- /.panel-body -->
						</div>
					</div>
					<!-- /.col-lg-4 -->
				</div>
			</c:otherwise>
		</c:choose>
	</tiles:putAttribute>
</tiles:insertDefinition>