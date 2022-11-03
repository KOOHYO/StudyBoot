package com.ko.home;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ko.home.board.qna.QnaMapper;
import com.ko.home.board.qna.QnaVO;
import com.ko.home.member.MemberVO;

@Controller
public class HomeController {
	
	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.defalut}")
	private String app;

	//slf4j
	//private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		
		log.info("------------------------");
		Enumeration<String> en = session.getAttributeNames();
		
		// hasMoreElements() : Elements가 있습니까?
		while(en.hasMoreElements()) {
			// nextElement() : 있으면 주세요
			String key = en.nextElement();
			// SPRING_SECURITY_CONTEXT 란 키가 나옴
			log.info("Key => {} ", key);
		}
		
		// SecurityContextImpl 클래스가 있다
		// SecurityContext 타입을 구현한 SecurityContext 타입의 SecurityContextImpl 클래스이기 때문에
		// SecurityContext 를 써도 무방하다
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(context != null) {
			log.info("Obj => {} ", context);		
		}
		
		log.info("------------------------");
		log.info("message : {} ", message);
		log.info("default : {} ", app);
		log.info("------------------------");
		
//		log.error("Error Message");
//		log.warn("Warn Message");
//		log.info("Info Message");
//		log.debug("Debug Message");
//		log.trace("Trace Message");
		
//		List<QnaVO> ar = qnaMapper.getList();
		
//		log.info("List : {} size {}", ar, ar.size());
		
		return "index";
	}
	
}
