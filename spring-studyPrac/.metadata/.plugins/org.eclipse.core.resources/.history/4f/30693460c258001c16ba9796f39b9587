package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public void addMember(MemberVO memberVO) {
		
		memberMapper.addMember(memberVO);
	}
	
	public int getCountById(String id) {
		
		return memberMapper.getCountById(id);
	}
	
	public MemberVO getMemberById(String id) {
		return memberMapper.getMemberById(id);
	}
	
	public void modifyMember(MemberVO memberVO) {
		memberMapper.modifyMember(memberVO);
	}
	
}
