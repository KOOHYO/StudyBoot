package com.ko.home.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

	@Bean 
	public LocaleResolver locale() {
		
		// 둘중 하나만 만들어서 리턴해주면 됨
		
		//1. Session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2. Cookie
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		
		return cResolver;
	}
	
	// Interceptor 객체
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=ko
		return changeInterceptor;
	}
	
	// ***-context.xml
	// 레거시에서
	// 라이브러리로 받은건 어노테이션을 붙일 수 없어서 bean을 씀
	// <bean class="" id=""> == new 생성자()
	// 스프링부트에서는 @Bean 어노테이션을 씀
	// 따로 이름을 지정하지않았으면 클래스명에서 앞글자를 소문자로 쓴 string이다
	// 라이브러리로 받은 class의 객체를 만들기 위해 Bean을 씀
	// !! 메서드명이 아님 !!
	//	@Bean("my")
	//	public String getString() {
	//		return new String();
	//	}
}
