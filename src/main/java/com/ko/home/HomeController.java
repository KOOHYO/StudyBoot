package com.ko.home;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ko.home.board.qna.PostVO;
import com.ko.home.board.qna.QnaMapper;
import com.ko.home.board.qna.QnaVO;
import com.ko.home.interceptors.TestInterceptor;
import com.ko.home.member.MemberVO;
import com.ko.home.util.TestInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.defalut}")
	private String app;
	
	@Value("${kakao.rest}")
	private String rest;

	//slf4j
	//private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/arrow")
	public void arrow() {
		// Java 에서는 Lamda식(JS 에서는 Arrow Funcion) 이라고 부름
		// m(message) 라는 익명함수를 호출 그 안에서 프린트문을 씀
		TestInterface t = (m)->System.out.println(m);
		t.info("test");
		// 위 메서드와 아래 메서드가 같다 원래는 아래 메서드형식으로 썼었음
		TestInterface t2 = new TestInterface() {
			
			@Override
			public void info(String message) {
				System.out.println(message);
				
			}
		};
		t2.info("test");
	}
	
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
	
	@GetMapping("/web")
	public String webClientTest() {
		
		WebClient webClient = WebClient.builder()
							 		   .baseUrl("https://jsonplaceholder.typicode.com/")
							 		   .build()
							 		   ;
		Flux<PostVO> res = webClient.get()
				 					.uri("posts")
				 					.retrieve()
				 					.bodyToFlux(PostVO.class)
				 					;
		PostVO postVO = res.blockFirst();
		
		//public void test(PostVO postVO){}
		// a.test(postVO)
		
		// 화살표함수(에로우펑션) : 한번 쓰고 말 메서드를 익명함수처럼 씀
		res.subscribe((s)->{
			PostVO pvo = s;
			log.info("ID : {} ", s.getId());
		});
		
		log.info("Result => {} ", postVO);
		
		return "";
	}
	
	@GetMapping("/address")
	@ResponseBody
	public String address()throws Exception{
		// kakao 지도 요청
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+rest);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 100");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class, req);
		
		return res.getBody();
		
	}
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		// 1. Header
		HttpHeaders headers = new HttpHeaders();
		// heager : Key:value
		// headers.add("key", "value");
		// headers.set헤더명("값");
		
		// 2. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		
		// 3. 요청 정보 객체(1,2 번을 모음)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
		//PostVO postVO = response.getBody();
		log.info("Posts => {} ", posts);
		
		// 4. 전송 후 결과
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
