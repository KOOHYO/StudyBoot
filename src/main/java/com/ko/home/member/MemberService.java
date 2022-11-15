package com.ko.home.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Value("${kakao.admin}")
	private String adminkey;
	
	public int setDelete(MemberVO memberVO)throws Exception{
		// 1. WebClient 생성
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://kapi.kakao.com/")
									   .build()
										;
		//2. parameter
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("target_id_type", "user_id");
		map.add("target_id", memberVO.getId());
		
		Mono<String> res = webClient.post()
								    .uri("v1/user/unlink")
								    .body(BodyInserters.fromFormData(map))
								    .header("Authorization", "KakaoAK "+adminkey)
								    .header("Content-Type", "application/x-www-form-urlencoded")
								    .retrieve()
								    .bodyToMono(String.class)
								    ;
		log.info("WebClientResult => {} ", res.block());
		
		return 1;
		
	}
	
	public int setDelete2(MemberVO memberVO)throws Exception{
		int result=0;
		RestTemplate restTemplate = new RestTemplate();
		
		//--Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //application/x-www-form-urlencoded 과 같음
		// 여기서 KaKaoAK 다음 띄어쓰기 한번 꼭 해야함! 주의
		headers.add("Authorization","KakaoAK "+adminkey);
		
		//--parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getId());
		
		//--요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		
		//--전송 후 결과 처리
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		
		log.info("res => {} ", res.getBody());
		
		if(res.getBody() != null) {
			result=1;
		}
		return result;
	}
	
	// 아이디 중복확인
	// 사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false;
		// check=false : 검증성공(error 없음)
		// check=true  : 검증실패(error 있음)
		
		// 1. password 가 일치하는지 검증
		// hasErrors() = 에러가 있습니까? 물어보는 메서드다
		// 여기서 false = 어노테이션으로 검증을 성공 했을때 에러가 없다는 뜻
		check = bindingResult.hasErrors();
		
		// 2. password 가 일치하는 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			check=true;
			// 에러메세지
			// bindingResult.rejectValue("멤버변수명(path)", "properties의 key(코드)");
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		// 3. id 중복 인지 검증
		int result = memberMapper.getIdCheck(memberVO);
		if(result>0) {
			check=true;
			
			bindingResult.rejectValue("id", "member.id.equal");
		}
		
		return check;
	}
	
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberMapper.getIdCheck(memberVO);
	}
	
	// 로그인
//	public MemberVO getLogin(String username)throws Exception{
//		return memberMapper.getLogin(username);
//	}
	
	// 회원가입
	public int setJoin(MemberVO memberVO)throws Exception{
		int result = memberMapper.setJoin(memberVO);
		//강제로 Exception 발생시킴
		if(result<1) {
			throw new Exception();
		}
		
		result = memberMapper.setMemberRole(memberVO);
		
		if(result<1) {
			throw new Exception();
		}
		
		return result;
	}
	
}
