package com.ko.home.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
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
		MemberVO memberVO = new MemberVO();
		memberVO.setId("koo");
		memberVO.setPw("koo");
		
		memberVO = memberService.getLogin(memberVO);
		
		assertNotNull(memberVO);
		
	}

}
