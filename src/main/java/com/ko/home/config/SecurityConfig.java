package com.ko.home.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ko.home.member.security.LoginSuccess;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 만든것을 실행
	@Autowired
	private LoginSuccess loginSuccess;
	
	@Bean
	// public 을 선언하면 default로 바꾸라는 메세지가 출력됨
	WebSecurityCustomizer webSecurityConfig() {
		// Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				// 이미지나, css, js..등은 따로 무시해라
				// 보안과 상관없기 때문!
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security)throws Exception {
		security
				.cors()
				.and()
				.csrf()
				.disable()
			.authorizeRequests() // 어떤 URL의 요청에 권한을 설정
				.antMatchers("/").permitAll() // 루트 밑 (인덱스)페이지는 누구나 허용한다
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/manager").hasAnyRole("ADMIN", "MANAGER")
				.antMatchers("/qna/list").permitAll()
				.antMatchers("/qna/**").hasRole("MEMBER")
				.anyRequest().permitAll()
				.and() // 이 설정이 끝났다
			.formLogin() // 또 다른 설정시작 -> 로그인 폼 인증 설정
				.loginPage("/member/login") // 내장된 로그인폼을 사용하지 않고 개발자가 만든 로그인폼 요청 URL
				//.loginProcessingUrl("login")	// 로그인을 진행 요청할 form 태그의 action의 주소 지정
				.usernameParameter("id")	// 패스워드 파라미터는 password이지만, 개발자가 다른 파라미터 이름을 사용할 때
				.passwordParameter("pw")	// 아이디 파라미터는 username이지만, 개발자가 다른 파라미터 이름을 사용할 때
				//.defaultSuccessUrl("/")		// 인증(로그인)에 성공할 경우 요청할 URL
				.successHandler(loginSuccess)
				.failureUrl("/member/login?error=true&message=LoginFail")// 인증(로그인)에 실패했을 경우 요청할 URL
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/")      // 로그아웃성공하면 어디로 갈까
				.invalidateHttpSession(true)// 세션정보를 파기 true면 하겠다는뜻
				.deleteCookies("JSESSIONID")
				.permitAll();
				
		return security.build();
	}
	
	// 평문(Clear Text)을 암호화 시켜주는 객체생성
	@Bean
	public PasswordEncoder getEncoder() {
		// BCryptPasswordEncoder : 평문패스워드를 암호화 시켜주는 클래스다
		return new BCryptPasswordEncoder();
	}
	
}
