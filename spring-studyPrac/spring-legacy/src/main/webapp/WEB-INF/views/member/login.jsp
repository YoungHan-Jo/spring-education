<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	로그인 화면
</h1>

<button onclick="location.href='/'">메인으로 돌아가기</button>


<form action="/member/login" method="POST">

<label>아이디</label>
<input type="text" name="id" />
<label>비밀번호</label>
<input type="password" name="passwd" />

<button type="submit">로그인</button>

</form>



<button onclick="location.href='/member/join'">회원가입</button>


</body>
</html>
