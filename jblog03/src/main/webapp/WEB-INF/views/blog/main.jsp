<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content" style="
				  background-color: white;
				  border: 2px solid #3879D9;
				  padding: 0.5rem;
				  line-height: 1rem;
				  /*border-radius: 0.5rem;*/
				">
					<h2 style="color:gray">카테고리 : ${category }</h2> <br>
					<c:if test="${empty postvo.title }">
						<h4>해당 카테고리에 글이 없습니다.</h4>
					</c:if>
					<c:if test="${not empty postvo.title }">
						<h4>${postvo.title }</h4>
					</c:if>
					<p>
						${fn:replace(postvo.contents, newline, "<br>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postlist }" var="post" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${id}?postno=${post.no }&category=${category }">${post.title }</a> <span>${post.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.profile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categorylist }" var="category" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${id}?category=${category.name }">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp" />
	</div>
</body>
</html>