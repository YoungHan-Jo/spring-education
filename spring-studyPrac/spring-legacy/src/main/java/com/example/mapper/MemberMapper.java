package com.example.mapper;

import com.example.domain.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	void addMember(MemberVO memberVO);
	
	// DB에 해당하는 아이디 개수 받아오기
	int getCountById(String id);
	
	// id에 해당하는 db 검색결과를 MemberVO 객체 가져오기
	// interface에서 설계하고 memberService에서 구현
	MemberVO getMemberById(String id);
	
	// MeberVO 객체로 넣어서 거기에 해당하는 db 수정
	void modifyMember(MemberVO memberVO);
}
