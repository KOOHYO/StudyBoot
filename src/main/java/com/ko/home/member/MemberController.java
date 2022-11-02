package com.ko.home.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@GetMapping("idCheck")
	public int getIdCheck(MemberVO memberVO)throws Exception{
		int result = memberService.getIdCheck(memberVO);
		
		return result;
	}
	
	@GetMapping("logout")
	public String setLogout(HttpSession session)throws Exception{
		
		session.invalidate();

		return "redirect:../";
	}
	
	@GetMapping("login")
	public String getLogin()throws Exception{
		
		return "member/login";
	}

	// Spring Security가 로그인 처리를 해준다
	// id = username, pw = password
//	@PostMapping("login")
//	public ModelAndView getLogin(MemberVO memberVO, HttpSession session)throws Exception{
//		ModelAndView mv = new ModelAndView();
//		
//		memberVO = memberService.getLogin(memberVO);
//		
//		String message = "❗로그인을 실패했습니다..❗";
//		String url = "./login";
//		if(memberVO != null) {
//			message = "🎉로그인을 성공했습니다!!🎉";
//			url = "../";
//			session.setAttribute("member", memberVO);
//		}
//		
//		mv.addObject("message", message);
//		mv.addObject("url", url);
//		mv.setViewName("common/result");
//		
//		return mv;
//	}
	
	@GetMapping("join")
	public String setJoin()throws Exception{
		
		return "member/join";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO)throws Exception{

		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setJoin(memberVO);
		
		String message = "❗회원가입을 실패했습니다..❗";
		String url = "./join";
		if(result!=0) {
			message = "🎉회원가입을 축하합니다!!🎉";
			url = "./login";
		}
		mv.addObject("message", message);
		mv.addObject("url", url);
		mv.setViewName("common/result");
		
		return mv;
	}
	
}
