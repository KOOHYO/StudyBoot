package com.ko.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ko.home.interceptors.StudyInterceptor;
import com.ko.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired //IOC(Inversion Of Controll)
	private TestInterceptor testInterceptor;
	
	@Autowired
	private StudyInterceptor studyInterceptor;
	
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// TODO Auto-generated method stub
			
			//method Chaining 세미콜론 찍지말고 엔터치고 .으로 이어간다
			//적용할 Interceptor 등록
			registry.addInterceptor(testInterceptor)
			//Interceptor를 적용할 URL 등록
					.addPathPatterns("/qna/**")
					.addPathPatterns("/notice/**")
			//제외할 URL등록
					.excludePathPatterns("/qna/detail")
					.excludePathPatterns("/qna/write");
			
			//추가 Interceptor 등록
			registry.addInterceptor(studyInterceptor)
					.addPathPatterns("/qna/**");
			
			//Interceptor 순서
			
			//WebMvcConfigurer.super.addInterceptors(registry);
		}
	
}
