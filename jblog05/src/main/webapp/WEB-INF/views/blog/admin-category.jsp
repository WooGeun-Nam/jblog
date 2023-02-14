<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${id }/admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>기본</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${categorylist.size() }" />
		      		<c:forEach items="${categorylist }" var="category" varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>
							<td>${category.name }</td>
							<td>${category.count }</td>
							<td>
							<c:if test="${category.defaultView == 'Y' }">
								<img src="${pageContext.request.contextPath}/assets/images/check.png">
							</c:if>
							<c:if test="${category.defaultView == 'N' }">
								<a href="${pageContext.request.contextPath }/${id }/admin/category/change/${category.no }"
									onclick="return confirm('변경 하시겠습니까?');"><input type="button" value="변경"></a>
							</c:if>
							</td>
							<td>
							<c:if test="${category.count < 1 }">
								<a href="${pageContext.request.contextPath }/${id }/admin/category/delete/${category.no }/${category.count }/${category.defaultView }"
							   		onclick="return confirm('삭제 하시겠습니까?');">
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
							</c:if>	
							</td>
						</tr> 
					</c:forEach>				  
				</table>
      			
      			<form action="${pageContext.request.contextPath}/${id }/admin/category/add" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
		      	</form> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp" />
	</div>
</body>
</html>