package com.ko.home.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//@Test
	void test()throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("koo2");
		memberVO.setPw(passwordEncoder.encode("koo2"));
		memberVO.setName("구2");
		memberVO.setEmail("koo2@gmail.com");
		int result = memberMapper.setJoin(memberVO);
		assertEquals(1, result);
	}
	
	//@Test
	void setJoinTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("k1");
		memberVO.setPw("k1");
		memberVO.setName("케이원");
		memberVO.setEmail("k1@naver.com");
		
		int result = memberService.setJoin(memberVO);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void getLoginTest()throws Exception{
		String username=null;
		MemberVO memberVO = new MemberVO();
		memberVO.setId("koo");
		memberVO.setPw("koo");
		
		//memberVO = memberService.getLogin(username);
		
		assertNotNull(memberVO);
		
	}

	//@Test
	void getIdCheckTest()throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("koo");
		
		int result = memberService.getIdCheck(memberVO);
		assertEquals(1, result);
	}
	
}
