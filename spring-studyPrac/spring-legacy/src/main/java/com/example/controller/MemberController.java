package com.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	// 단순 페이지 띄우기니까 @GetMapping("/logout")
	@GetMapping("/logout")
	public String logout(HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		// 세션 제거(내리기 & 비우기 & 초기화)
		session.invalidate();
		
		// 쿠키 삭제
		Cookie[] cookies = request.getCookies();
		
		// 쿠키를 배열로 가져오니까 검색이 필요
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					// 사용자에게서 받아온 쿠키의 수명을 0으로 설정
					cookie.setMaxAge(0);
					// 쿠키 적용경로 설정
					cookie.setPath("/");
					// 사용자에게 보낼때 쿠키를 적용시켜서 보내준다.
					response.addCookie(cookie);
				}
			}
		}
		// redirect는 새로운 창을 띄울 때 주소창에 있는
		// 주소도 함께 같이 바뀜 새로고침해도 페이지가 전 페이지로 이동안함
		
		// forward는 화면만 바뀌고 주소창은 변경되지 않음
		// 새로고침을 계속 누르면 주소창에 있는 페이지를 DB에다가 계속
		// 등록을 하게됨
		// 서버도 가중이되서 터져버림
		return "redirect:/member/login";
	}
	
	@GetMapping("/myInfo")
	public String myInfo(HttpSession session,
						 Model model) {
		// 세션에서 오브젝트를 져오는데 앞에 String써서 구체화시켜준다
		String id = (String) session.getAttribute("id");
		
		System.out.println("id : " + id);
		
		MemberVO memberVO =  memberService.getMemberById(id);
		
		model.addAttribute("member", memberVO);
		
		return "member/myInfo";
	}
	
	@GetMapping("/modify")
	public String memberModifyForm(HttpSession session,
			 					   Model model) {
		String id = (String) session.getAttribute("id");
		
		System.out.println("id : " + id);
		
		MemberVO memberVO =  memberService.getMemberById(id);
		
		model.addAttribute("member", memberVO);
		
		return "member/modify";
	}
	
	@PostMapping("/modify")
	public ResponseEntity<String> memberModify(MemberVO memberVO,
			HttpSession session) {
		
		String id = (String) session.getAttribute("id");
		memberVO.setId(id);
		
		MemberVO dbMemberVO =  memberService.getMemberById(memberVO.getId());
		
		System.out.println("dbMemberVO : " + dbMemberVO);
		
		// 1.비밀번호 체크
		String realPasswd = dbMemberVO.getPasswd();
		if (memberVO.getPasswd().equals(realPasswd) == false) { // 비밀번호가 틀렸을 때
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호가 틀렸습니다.");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// ====== 비밀번호 일치 ======
		
		// 2. DB 수정
		memberService.modifyMember(memberVO);
		
		// 3.사용자에게 응답 보내기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원수정 완료", "/member/myInfo");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}
	
	@GetMapping("/passwd")
	public String passwdForm() {
		
		return "member/passwd";
	}
	
	@PostMapping("/passwd")
	public ResponseEntity<String> passwd(String passwd,
			String newPasswd1, String newPasswd2,
			HttpSession session) {
		// 1. 현재 비밀번호가 일치하는지 체크
		String id = (String) session.getAttribute("id");
		MemberVO memberVO =  memberService.getMemberById(id);
		
		// 비밀번호가 틀렸을 때 DB를 뒤져봐야 해서 시간이 오래걸림
		if (passwd.equals(memberVO.getPasswd()) == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호가 틀렸습니다.");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		// 2. 새 비밀번호와 새 비밀번호확인이 일치하는지
		if (newPasswd1.equals(newPasswd2) == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("새 비밀번호가 서로 같지 않습니다.");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		// 3. DB에 있는 비밀번호 수정
		memberService.modifyPasswd(id, newPasswd1);
		
		// 4. 로그아웃 시키기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("비밀번호 변경 완료 다시 로그인하세요", "/member/logout");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}
	
	@GetMapping("/delete")
	public String deleteMemberForm() {
		
		return "member/deleteMember";
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteMember(
			String passwd, HttpSession session) {
		// 1. 비밀번호 체크
		String id = (String) session.getAttribute("id");
		MemberVO memberVO =  memberService.getMemberById(id);
		
		// 비밀번호 틀렸을 때
		if (passwd.equals((String) memberVO.getPasswd()) == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호가 틀렸습니다.");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// 2. 화원탈퇴 처리하기 DB에서 삭제
		memberService.deleteMember(id);
		
		// 3. 로그아웃 시키기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원탈퇴 완료", "/member/logout");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}
}
