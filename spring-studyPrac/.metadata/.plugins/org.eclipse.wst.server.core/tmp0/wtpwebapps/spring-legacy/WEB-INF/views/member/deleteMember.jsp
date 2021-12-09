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
		회원탈퇴
	</h1>

	<button onclick="location.href='/'">메인으로 돌아가기</button>
	<br>
	<button onclick="location.href='/member/myInfo'">내정보 보기</button>
	<button onclick="location.href='/member/modify'">내정보 수정</button>
	<button onclick="location.href='/member/passwd'">비밀번호 변경</button>
	<button onclick="location.href='/member/delete'">회원 탈퇴</button>
	
	<form action="/member/delete" method="POST">
		
		<label>탈퇴하려면 비밀번호 입력하세요</label>
		<input type="password" name="passwd" /><br>
		
		<button type="submit">회원탈퇴 하기</button>
	</form>
	
</body>
</html>
