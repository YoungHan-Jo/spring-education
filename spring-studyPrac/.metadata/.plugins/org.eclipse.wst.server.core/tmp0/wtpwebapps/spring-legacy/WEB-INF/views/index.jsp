<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>

<!-- EL언어

page < request < session < application

page - 현재 이 jsp 페이지 안에서만 존재함
request - 요청 받고 응답하기 직전 까지만 존재함
session - session에 등록되고 삭제 될 때까지 존재함
application - 프로그램 종료될때까지 존재

'xxxxScope' 생략 시 검색 순서
${pageScope.xxx} - 1 
${requestScope.xxx} - 2
${sessionScope.xxx} - 3
${applicationScope.xxx} - 4 

 -->


<!-- if(id != null) 세션에 아이디가 존재하면(로그인상태) -->
<!-- else 세션에 아이디가 없다(로그아웃상태) -->
<c:choose>
	<c:when test="${not empty sessionScope.id}">
		<button onclick="location.href='/member/myInfo'">내 정보</button>
		<button onclick="location.href='/member/logout'">로그아웃</button>
	</c:when>
	<c:otherwise>
		<button onclick="location.href='/member/login'">로그인</button>
		<button onclick="location.href='/member/join'">회원가입</button>
	</c:otherwise>
</c:choose>




</body>
</html>
