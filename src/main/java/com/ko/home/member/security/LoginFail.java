package com.ko.home.member.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFail implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("=========== 로그인 실패 ===========");
		log.info("ClassName => {} ",exception.getClass().toString()); // 익셉션 클래스 이름만 찍기
		log.info("LocalMessage => {} ", exception.getLocalizedMessage());
		log.info("Caus => {} ", exception.getCause());
		log.info("Message => {} ", exception.getMessage());
//		String name = exception.getClass().toString();
//		// . 까지 포함되어 있어 인덱스번호+1 해야함
//		name = name.substring(name.lastIndexOf("."));
//		if(name.equals(".BadCredentialsException")) {
//			name="비번 틀림";
//		}
		
		String result = null;
		// 참조변수명 instanceOf 클래스명 (instance : 객체)
		if(exception instanceof BadCredentialsException) {
			result = "비밀번호가 틀립니다.";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			result = "아이디가 없습니다.";
		}else {
			result = "로그인 실패";
		}
		
		// Redirect방식으로 JSP를 바로 찾아감
		//redirect는 인코딩이 들어가야함!
//		result = URLEncoder.encode(result, "UTF-8");
//		response.sendRedirect("/member/login?error=true&message="+result);
		
		// forward방식으로 JSP로 바로 찾아감
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		
		// forward방식으로 Controller POST 메서드를 요청 함
//		request.setAttribute("msg", result);
//		request.getRequestDispatcher("/member/login").forward(request, response);
		
	}
	
}
