package com.example.mapper;

import com.example.domain.MemberVO;

public interface MemberMapper {

	// 회원가입
	void addMember(MemberVO memberVO);
	
	// db에 해당하는 아이디 개수 받아오기
	int getCountById(String id);
	
	MemberVO getMemberById(String id);
}
