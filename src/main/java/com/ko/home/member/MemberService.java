package com.ko.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
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
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberMapper.getLogin(memberVO);
	}
	
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
