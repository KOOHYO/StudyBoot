package com.ko.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ko.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler {

	@Value("${kakao.rest}")
	private String rest;
	
	@Value("${kakao.url}")
	private String url;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 1. 일반 로그인?? 아니면 Social Login 사용??
		
		MemberVO memberVO = (MemberVO) authentication.getPrincipal(); //memberVO
		
		String social = memberVO.getSocial();
		
		if(social != null) {
			if(social.equals("kakao")) {

				// RestTemplate restTemplate = new RestTemplate();
				//response.sendRedirect("https://developers.kakao.com/logout");
				// header X
				// parameter X
				
//				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//				map.add("client_id", rest);
//				
//				HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map);
				
				
				response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+rest+"&logout_redirect_uri="+url);
				log.info("kakao Logout");
//				ResponseEntity<String> res = restTemplate.getForEntity("https://developers.kakao.com/logout", null, String.class);
//				log.info("res => {} ", res);
//				response.sendRedirect("/");
				
			}else if(social.equals("google")) {
				
			}else {
				
			}
		}else {
			log.info("-------- 로그아웃 성공시 실행 --------");
			response.sendRedirect("/");
			
		}
		
//		if(social != null && social.equals("kakao")) {
//			
//		}else if(social != null && social.equals("google")) {
//			
//		}else {
//			
//		}
	}
	
}
