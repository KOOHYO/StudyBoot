package com.ko.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("mypage")
	public String getMyPage()throws Exception{
		return "member/myPage";
	}
	
	@ResponseBody
	@GetMapping("idCheck")
	public int getIdCheck(MemberVO memberVO)throws Exception{
		int result = memberService.getIdCheck(memberVO);
		
		return result;
	}
	
	@GetMapping("logout")
	public String setLogout(HttpSession session)throws Exception{
		log.info("====== 내가만든 로그아웃 ======");
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
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute(memberVO);
//		return "member/join";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(ModelAndView mv, @Valid MemberVO memberVO, BindingResult bindingResult)throws Exception{

//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입하는 JSP로 foward
//			log.info("======== 검증 에러 발생 ========");
//			mv.setViewName("member/join");
//			return mv;
//		}
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			log.info("====== 검증 에러 발생 ======");
			mv.setViewName("member/join");
			// ===================================
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError error : errors) {
				log.info("error => {} ", error);
				log.info("Field => {} ", error.getField());
				log.info("Message => {} ", error.getRejectedValue());
				log.info("Default => {} ", error.getDefaultMessage());
				log.info("Code => {} ", error.getCode());
				// error 필드명이 속성명이되고 message가 값이 된다
				mv.addObject(error.getField(), error.getDefaultMessage());
				log.info("==========================================================");
			}
			
			return mv;
		}
		
		//ModelAndView mv = new ModelAndView();
		
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
