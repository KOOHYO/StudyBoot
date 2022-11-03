package com.ko.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

// DI(의존성), IOC
@Service
@Slf4j
public class MemberSecurityService implements UserDetailsService {

	// 이 변수가 없으면 MemberSecurityService가 아무것도 하지 못함 -> 의존성 DI
	// 이걸 사용하기위해 IOC기법을 사용함
	@Autowired
	private MemberMapper memberMapper;
	
	// 추상 메서드는 선언부를 건들이면 안됀다!
	// 오버라이드는 선언부를 건들지 않고 
	// 문법적으로 맞지 않기 때문
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId(username);
		log.info("========== 로그인 시도중 ==========");
		MemberVO memberVO = memberMapper.getLogin(username);
		log.info("MemberVO => {} ", memberVO);
		return memberVO;
	}
	
}
