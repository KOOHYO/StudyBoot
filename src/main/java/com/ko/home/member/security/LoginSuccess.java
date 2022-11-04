package com.ko.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// AuthenticationSuccessHandler를 구현
@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler {

	// DB조작을 하고싶으면 여기다가 매퍼를 불러온다
	
	// 오버라이딩 방법
	// 1. Source에 Override..
	// 2. Ctrl+Space
	@Override
	// on : ~했을때 
	// Authentication : 인증
	// onAuthenticationSuccess : 인증이 성공 했을때 실행하는 메서드
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// Security Context와 같은값
		// 사용자의 정보를 꺼내옴
		log.info("======== 로그인 성공 ========");
		log.info("Aut => {} ", authentication);
		log.info("ID : {} ", request.getParameter("id"));
		// authentication.getPrincipal() : MemberVO
		// 체크를 하면 on 안하면 null
		log.info("Check -> {} ", request.getParameter("rememberId"));
		
		String check = request.getParameter("rememberId");
		
		if(check != null) {
			Cookie cookie = new Cookie("userId", request.getParameter("id"));
			// 아이디 저장
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60); // 초단위, MaxAge : 유효기간
			cookie.setPath("/"); // 쿠키의 경로 : 같은 도메인(하나의 영역) 내에서 어느 URL까지 사용 가능한가 적용
			
			response.addCookie(cookie);
			
		}
		response.sendRedirect("/");
	}
}
