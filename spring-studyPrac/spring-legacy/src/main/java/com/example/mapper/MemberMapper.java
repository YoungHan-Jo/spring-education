package com.example.mapper;

import com.example.domain.MemberVO;

public interface MemberMapper {

	// 회원가입
	void addMember(MemberVO memberVO);
	
	// db에 해당하는 아이디 개수 받아오기
	int getCountById(String id);
	
	// id에 해당하는 db 검색결과를 MemberVO객체로 받아오기
	MemberVO getMemberById(String id);
	
	// MemberVO 객체를 넣어서 거기에 해당하는 db 수정
	void modifyMember(MemberVO memberVO);
}
