package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.MemberVO;
import com.example.service.MemberService;
import com.example.util.JScript;

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

	@PostMapping("/login")
	public ResponseEntity<String> login(String id, String passwd) {
		// ===== 아이디 존재여부 체크 =====
		int count = memberService.getCountById(id);
		
		if (count == 0) { // DB에 아이디 존재하지 않음
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
	
			String str = JScript.back("아이디가 존재하지 않습니다.");
			
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// === 비밀번호 체크 ===
		
		
		
		
		
		
		// ====== 아이디 존재여부 체크 종료 =====
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("로그인 완료", "/");
		
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
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
		
		
		
		return "member/login"; // 회원가입 완료 후 로그인 화면으로
	}
	
}
