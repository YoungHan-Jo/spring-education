package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.MemberVO;
import com.example.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/login") //   /member/login
	public String loginForm() {
		
		System.out.println("loginForm 호출됨 ...");
		
		return "member/login";
	}
	
	@GetMapping("/join") // /member/join 회원가입 화면으로 보내는 메소드
	public String joinForm() {
		
		return "member/join";
	}
	
	@PostMapping("/join") // /member/join 사용자가 입력한 값을 기반으로 회원가입 시키는 메소드
	public String join(MemberVO memberVO) {
		
		System.out.println("memberVO : " + memberVO);
		
		memberService.addMember(memberVO);
		
		System.out.println("회원가입 완료");
		
		return "member/login"; // 회원가입 완료 후 index 화면으로
	}
	
}
