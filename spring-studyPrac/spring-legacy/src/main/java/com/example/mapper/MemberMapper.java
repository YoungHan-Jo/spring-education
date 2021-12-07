package com.example.mapper;

import com.example.domain.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	void addMember(MemberVO memberVO);
	
	// DB에 해당하는 아이디 개수 받아오기
	int getCountById(String id);
	
	// id에 해당하는 MemberVO 객체 가져오기
	// interface에서 설계하고 memberService에서 구현
	MemberVO getMemberById(String id);
	
	
	
}
