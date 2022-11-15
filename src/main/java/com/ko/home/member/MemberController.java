package com.ko.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("logoutResult")
	public String socialLogout()throws Exception{
		return "redirect:../";
	}
	
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession session, String pw)throws Exception{
		//1. Social, 일반 구분 social이 null이면 일반 kakao면 Social
		ModelAndView mv = new ModelAndView();
		SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = context.getAuthentication();
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		int result = memberService.setDelete(memberVO);
		
		if(result>0) {
			mv.setViewName("redirect:./logout");
		}else {
			// 탈퇴 실패
		}
		return mv;
	}
	
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
	
//	@GetMapping("logout")
//	public String setLogout(HttpSession session)throws Exception{
//		log.info("====== 내가만든 로그아웃 ======");
//		session.invalidate();
//
//		return "redirect:../";
//	}
	
	@GetMapping("login")
							// @RequestParam : 이 파라미터가 안넘어오면 이 파라미터를 매개변수로 받지 말자
	public void getLogin(@RequestParam(defaultValue = "false", required = false) boolean error, String message, Model model)throws Exception{
		//Controller에서 받아서 JSP로 다시 보내도 됨
		
		if(error) {
			model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
	}

	// Spring Security가 로그인 처리를 해준다
	// id = username, pw = password
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception{
		log.info("========= Login POST =========");
		return "member/login";
	}
	
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
