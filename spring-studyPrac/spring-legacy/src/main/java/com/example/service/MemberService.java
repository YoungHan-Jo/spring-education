package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;

@Service
public class MemberService {
	
	private MemberMapper memberMapper; 
	
	
	
	public MemberService(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}



	public void addMember(MemberVO memberVO) {
		
		memberMapper.addMember(memberVO);
		
	}
	
	
	
}
