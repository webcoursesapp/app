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
					<spring:message code="groupList.header.label" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:if test="${isOwner}">
							<c:url value="add" var="add" />
							<a href="<c:out value='${add}'/>"> <input name="submit" class="btn btn-success btn-sm" type="submit"
								value="<spring:message code="addGroup.form.title" />" />
							</a>
						</c:if>

						<c:if test="${!isOwner}">
							<h3 class="panel-title">
								<spring:message code="groupList.panel.header.label" />
							</h3>
						</c:if>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTable">
								<thead>
									<tr>
										<th><spring:message code="addGroup.form.item.name" /></th>
										<th><spring:message code="addGroup.form.item.course" /></th>
										<c:choose>
											<c:when test="${isOwner}">
												<th><spring:message code="groupList.panel.showMembers" /></th>
												<th><spring:message code="groupList.panel.addMember" /></th>
												<th><spring:message code="groupList.panel.edit" /></th>
												<th><spring:message code="groupList.panel.remove" /></th>
											</c:when>
											<c:otherwise>
												<th><spring:message code="groupList.panel.members" /></th>
											</c:otherwise>
										</c:choose>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${groupList}" var="group">
										<tr class="odd gradeX">
											<td><c:out value="${group.name}" /></td>
											<td><c:url value="/course/show" var="courseShow">
													<c:param name="id" value="${group.course.courseId}" />
												</c:url> <a href="<c:out value='${courseShow}'/>"><c:out value="${group.course.title}" /></a></td>
											<td><c:choose>
													<c:when test="${group.members.size()>0 }">
														<c:if test="${isOwner}">
															<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<c:out value="${group.groupId}" />"> <span
																class="glyphicon glyphicon-user"></span></a>
														</c:if>
														<c:if test="${isOwner}">
															<div id="collapseOne<c:out value="${group.groupId}" />" class="panel-collapse collapse">
														</c:if>
														<c:forEach items="${group.members}" var="member">
															<c:url value="/user/show" var="userShow">
																<c:param name="id" value="${member.user.userId}" />
															</c:url>
															<a href="<c:out value='${userShow}'/>"><c:out value="${member.user.name}" /> <c:out value="${member.user.surname}" /></a>
															<div class="pull-right">
																<c:if test="${isOwner}">
																	<c:url value="/group/removeMember" var="removeMember">
																		<c:param name="id" value="${member.memberId}" />
																	</c:url>
																	<a href="<c:out value='${removeMember}'/>"><span class="glyphicon glyphicon-trash"></span></a>
																</c:if>
															</div>
															<br />
														</c:forEach>
														<c:if test="${isOwner}">
															</div>
														</c:if>
													</c:when>
													<c:otherwise>
														<span class="glyphicon glyphicon-ban-circle"></span>
													</c:otherwise>
												</c:choose></td>
											<c:if test="${isOwner}">
												<td class="center"><c:url value="addMember" var="addMember">
														<c:param name="id" value="${group.groupId}" />
													</c:url> <a href="<c:out value='${addMember}'/>"><span class="glyphicon glyphicon-plus"></span></a></td>
												<td class="center"><c:url value="add" var="add">
														<c:param name="id" value="${group.groupId}" />
													</c:url> <a href="<c:out value='${add}'/>"><span class="glyphicon glyphicon-pencil"></span></a></td>
												<td class="center"><c:url value="remove" var="remove">
														<c:param name="id" value="${group.groupId}" />
													</c:url> <a href="<c:out value='${remove}'/>"><span class="glyphicon glyphicon-trash"></span></a></td>
											</c:if>
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