package com.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@GetMapping("/login") // /member/login
	public String loginForm() {

		System.out.println("loginForm 호출됨 ...");

		return "member/login";
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(String id, String passwd,
			HttpSession session, boolean rememberMe, HttpServletResponse response) {

		// === 아이디 존재여부 체크 ===
		int count = memberService.getCountById(id);

		if (count == 0) { // DB 아이디가 존재하지 않음
			// 선물상자 대가리!
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("아이디가 존재하지 않습니다");
			
			// 선물상자를 만든 이유는 JAVA에서는 JS를 실행할수 없기때문에
			// 사용자가 사용할때 선물상자를 열어서 확인할 수 있게 만듬
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		// === 비밀번호 체크 ===
		
		// memberVO에 memberService안에 있는 getMemberById(id)를 넣어준다.
		MemberVO memberVO = memberService.getMemberById(id);
		System.out.println("memberVO : " + memberVO);
		
		// lombok 있어서 간단하게 사용가능
		String realPasswd = memberVO.getPasswd();

		// 문자를 서로 비교했을때 false이면 밑에 if문 출력
		if (passwd.equals(realPasswd) == false) {
			// headers는 선물상자(ResponseEntity) 안에 있는 서류
			// headers 서류의 위에 부분
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호가 틀렸습니다.");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		// === 아이디 비밀번호 일치 시 로그인 ===
		
		// 세션 등록 "id" = 봉투이름(키) id = 내용물(값)
		session.setAttribute("id", id);
		
		// 로그인상태유지
		if (rememberMe == true) { // true상태일때 로그인 유지 체크 함 
			// 쿠키 등록하기
			Cookie cookie = new Cookie("userId", id); // 쿠키생성
			// 쿠키수명 설정 초 * 분 * 시 * 일
			cookie.setMaxAge(60 * 60 * 24 * 7);
			// 쿠키 적용 경로 설정 여기서 "/"는 webapp이다 즉 모든 경로이다
			cookie.setPath("/");
			// 사용자에게 보낼때 쿠키를 적용시켜서 보내준다.
			response.addCookie(cookie);
		}
		
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

		return "member/login"; // 회원가입 완료 후 index 화면으로
	}

}
