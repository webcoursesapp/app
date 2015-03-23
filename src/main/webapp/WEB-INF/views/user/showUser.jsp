<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<spring:message code="showUser.header.label" />
				</h3>
			</div>
		</div>

		<div class="row">
			<!-- profile-widget -->
			<div class="col-lg-12">
				<div class="profile-widget profile-widget-info">
					<div class="panel-body">
						<div class="col-lg-2 col-sm-2">
							<h4>
								<c:out value="${userAttribute.name}" />
								&nbsp;
								<c:out value="${userAttribute.surname}" />
							</h4>
							<div class="follow-ava">
								<img src="${pageContext.request.contextPath}/resources/img/avatars/profile-widget-avatar.jpg" alt="">
							</div>
						</div>
						<div class="col-lg-4 col-sm-4 follow-info">
							<p>
								<c:out value="${userAttribute.description}" />
							</p>
							<p>
								<c:set var="urlValue" value="${userAttribute.url}" />
								<c:out value="${urlValue}" />
							</p>
						</div>
						<div class="col-lg-6 col-sm-6 follow-info weather-category">
							<ul>
								<li class="active">
									<h4>
										<c:out value="${statistic.courses}" />
									</h4> <i class="icon_close_alt2"></i> <spring:message code="showUser.topPanel.addedCoursesLabel" />
								</li>
								<li>
									<h4>
										<c:out value="${statistic.members}" />
									</h4> <i class="icon_check_alt2"></i> <spring:message code="showUser.topPanel.membersLabel" />
								</li>
								<li>
									<h4>
										<c:out value="${statistic.messages}" />
									</h4> <i class="icon_plus_alt2"></i> <spring:message code="showUser.topPanel.messagesLabel" />
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading tab-bg-info">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#profile"> <i class="icon-user"></i> <spring:message
										code="showUser.tabs.profileLabel" />
							</a></li>
							<c:choose>
								<c:when test="${editable}">
									<li class=""><a data-toggle="tab" href="#edit-profile"> <i class="icon-envelope"></i> <spring:message
												code="showUser.tabs.editProfileLabel" />
									</a></li>
								</c:when>
							</c:choose>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${!editable}"> --%>
<%-- 									<li class=""><a data-toggle="tab" href="#send-message"> <i class="icon-envelope"></i> <spring:message --%>
<%-- 												code="showUser.tabs.sendMessageLabel" /> --%>
<!-- 									</a></li> -->
<%-- 								</c:when> --%>
<%-- 							</c:choose> --%>
						</ul>
					</header>
					<div class="panel-body">
						<div class="tab-content">
							<!-- profile -->
							<div id="profile" class="tab-pane active">
								<section class="panel">
									<div class="panel-body bio-graph-info">
										<div class="row">
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.firstName" /> </span>
													<c:out value="${userAttribute.name}" />
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.lastName" /> </span>
													<c:out value="${userAttribute.surname}" />
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.email" /> </span>
													<a href='mailto:<c:out value="${userAttribute.email}" />'><c:out value="${userAttribute.email}" /></a>
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.phone" /> </span>
													<c:out value="${userAttribute.phone}" />
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.websiteUrl" /></span>
													<c:set var="urlValue" value="${userAttribute.url}" />
													<a href='http://<c:out value="${urlValue}" />'><c:out value="${urlValue}" /></a>
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.country" /> </span>
													<c:out value="${userAttribute.country}" />
												</p>
											</div>
											<div class="bio-row">
												<p>
													<span><spring:message code="showUser.tab.profile.birthday" /> </span>
													<fmt:formatDate value="${userAttribute.birthdayDate}" pattern="yyyy/MM/dd" />
												</p>
											</div>
										</div>
									</div>
								</section>
								<section>
									<div class="row"></div>
								</section>
							</div>
							<!-- edit-profile -->
							<div id="edit-profile" class="tab-pane">
								<section class="panel">
									<div class="panel-body bio-graph-info">
										<form:form class="form-horizontal" modelAttribute="userAttribute">
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.firstName" /></label>
												<div class="col-lg-6">
													<form:input type="text" class="form-control" path="name" placeholder="" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.lastName" /></label>
												<div class="col-lg-6">
													<form:input type="text" class="form-control" path="surname" placeholder="" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.descritpion" /></label>
												<div class="col-lg-6">
													<form:textarea class="form-control" cols="30" rows="5" path="description" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.email" /></label>
												<div class="col-lg-6">
													<form:input type="text" class="form-control" path="email" placeholder="" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.phone" /></label>
												<div class="col-lg-6">
													<form:input type="text" class="form-control" path="phone" placeholder="" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.websiteUrl" /></label>
												<div class="col-lg-6">
													<form:input type="text" class="form-control" path="url" placeholder="" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.country" /></label>
												<div class="col-lg-6">
													<form:select class="form-control" items="${countryList}" path="country" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label"><spring:message code="showUser.tab.profile.birthday" /></label>
												<div class="col-lg-6">
													<input type="text" class="form-control datepicker" name="birthdayDate" id="birthdayDate"
														value=<fmt:formatDate value="${userAttribute.birthdayDate}" pattern="yyyy/MM/dd"/> data-date-format="yyyy/mm/dd" />
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-offset-2 col-lg-10">
													<input type="submit" class="btn btn-primary" value="Save" />
												</div>
											</div>
										</form:form>
									</div>
								</section>
							</div>
<!-- 							<div id="send-message" class="tab-pane"> -->
<%-- 								<section class="panel"> --%>
<!-- 									<div class="panel-body bio-graph-info"> -->
<!-- 										<h1> -->
<%-- 											<spring:message code="showUser.messageForm.header" /> --%>
<!-- 										</h1> -->
<%-- 										<form class="form-horizontal"> --%>
<!-- 											<div class="form-group"> -->
<%-- 												<label class="col-lg-2 control-label"><spring:message code="showUser.messageForm.topic" /></label> --%>
<!-- 												<div class="col-lg-6"> -->
<!-- 													<input class="form-control" placeholder="" type="text" /> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<%-- 												<label class="col-lg-2 control-label"><spring:message code="showUser.messageForm.message" /></label> --%>
<!-- 												<div class="col-lg-6"> -->
<!-- 													<textarea class="form-control" placeholder="" cols="30" rows="5"></textarea> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<div class="col-lg-offset-2 col-lg-10"> -->
<!-- 													<button type="submit" class="btn btn-primary"> -->
<%-- 														<spring:message code="showUser.messageForm.send" /> --%>
<!-- 													</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
<%-- 										</form> --%>
<!-- 									</div> -->
<%-- 								</section> --%>
<!-- 							</div> -->
						</div>
					</div>
				</section>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>