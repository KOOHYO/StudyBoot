package com.ko.home.member.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.ko.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler {
	
	@Override
		public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
			// TODO Auto-generated method stub
			log.info("=========== LogoutHandler ===========");
			
			request.getSession().invalidate();
		}
	
}
