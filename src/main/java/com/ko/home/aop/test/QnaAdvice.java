package com.ko.home.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.ko.home.board.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class QnaAdvice {
	
	@After("execution(* com.ko.home.board.qna.QnaService.get*(..))")
	public void beforeTest(JoinPoint joinPoint) {
		//joinPoint는 Around외에걸 쓸때 씀, proceed()가 없음
		log.info("----------------- before -----------------");
		log.info("Args : {} ", joinPoint.getArgs());
		log.info("Kind : {} ", joinPoint.getKind());
		
	}

	@Around("execution(* com.ko.home.board.qna.QnaService.set*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint)throws Throwable{
		
		log.info("----------------- before -----------------");
		//point-cut의 클래스객체 : QnaService
		log.info("Target : {} ", joinPoint.getTarget());
		//point-cut의 클래스객체 : QnaService
		log.info("This : {} ", joinPoint.getThis());
		//point-cut으로 전달되는 매개변수의 인자값(Argument의 약자 : 매개변수)
		log.info("Args : {} ", joinPoint.getArgs());
		Object [] objs = joinPoint.getArgs();
		QnaVO qnaVO = (QnaVO) objs[0]; //다형성
		
		
		//QnaService의 setAdd호출
		Object obj = joinPoint.proceed();
		log.info("----------------- after -----------------");
		//result안의 값을 받음(QnaService의 setAdd메서드의 리턴값이 옴)
		log.info("Obj : {} ", obj);
		
		return obj;
	}
	
}
