<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl은 html페이지에서 JS를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	
	<!-- EL언어 객체 범위 종류
	 page 영역        -> 현재 이 jsp 페이지 안에서만 존재함
	 request 영역     -> 요청 받고 응답하기 직전 까지만 존재함
	 session 영역 	  -> session에 등록되고 삭제 될 때까지 존재함 //로그아웃하면 사라짐
	 application 영역 -> 프로그램이 종료 될 때까지 존재
	 
	 사용 방법 (생략 시 검색 순서)
	 ${pageScope.xxx}    -> 1
	 ${requestScope.xxx} -> 2
	 ${sessionScope.xxx} -> 3
	 ${application.xxx}  -> 4
	 
	 생략도 가능
	 작은 곳부터 차례대로 검색해서 넘어감
	 곂쳐지는게 있다면 작은거만 실행함
	 그럴 경우엔 ???Scope를 붙여줘야함
	 ${ id }
	 -->
	
	<!-- when == if -->
	<!-- MemberController에 있는 id를 불러올 수 있음 -->
	<!-- 이게 EL언어임 -->
	<!-- if(id != null) id가 빈값이 아닐때 실행 -->
	<!-- otherwise == else -->
	<!-- 주석 안에다가 작성 하면 에러 뜸 거지같은 프로그램 -->
	<c:choose>
		<c:when test="${ not empty sessionScope.id }">
		<button onclick="location.href='/member/myInfo'">내정보</button>
			<button onclick="location.href='/member/logout'">로그아웃</button>
		</c:when>
		<c:otherwise>
			<button onclick="location.href='/member/login'">로그인</button>
			<button onclick="location.href='/member/join'">회원가입</button>
		</c:otherwise>
	</c:choose>

</body>
</html>
