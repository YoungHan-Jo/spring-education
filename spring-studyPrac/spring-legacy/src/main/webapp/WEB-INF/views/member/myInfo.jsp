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
	내 정보
</h1>

<button onclick="location.href='/'">메인으로 돌아가기</button>
<br>
<button onclick="location.href='/member/myInfo'">내정보 보기</button>
<button onclick="location.href='/member/modify'">내정보 수정</button>
<button onclick="location.href='/member/passwd'">비밀번호 변경</button>
<button onclick="location.href='/member/delete'">회원 탈퇴</button>

<div>id : ${ member.id }</div>
<div>이름 : ${ member.name }</div>
<div>이메일 : ${ member.email }</div>

</body>
</html>
