<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	회원가입 화면
</h1>

<button onclick="location.href='/'">메인으로 돌아가기</button>


<form action="/member/join" method="POST">

<label>아이디</label>
<input type="text" name="id" />
<label>비밀번호</label>
<input type="password" name="passwd" /> <br>
<label>이름</label>
<input type="text" name="name" />
<label>이메일</label>
<input type="email" name="email" />


<button type="submit">회원가입 하기</button>
</form>





</body>
</html>
