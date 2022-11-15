package com.ko.home.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User {
	
	@NotBlank(message = "아이디는 필수입니다.")
	private String id;
	@NotBlank
	@Pattern(regexp = "비밀번호 재확인")
	private String pw;
	private String pwCheck;
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	private boolean enabled;
	
	@Range(max=150, min=0)
	private int age;
	
	@Past
	private Date birth;
	
	private List<MemberRoleVO> memberRoleVOs;
	
	// ========= Social Login =========
	// kakao, naver, google 등
	private String social;

	// OAuth2User, Token 등 저보 저장
	// MemberSocialService의 auth2User.getAttributes()임
	private Map<String, Object> attributes;
	
	
	@Override
    // ?는 any 를 뜻함, extends GrantedAuthority 를 상속받는 아무타입이면 된다.
    // <? super T> T나 T의 부모타입을 허용하겠다 라는 뜻
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MemberRoleVO memberRoleVO : memberRoleVOs) {
			// SimpleGrantedAuthority 객체 생성 후 안에 String 타입의 Role을 넣어줌
			// authorities.add List에 데이터를 추가
			authorities.add(new SimpleGrantedAuthority(memberRoleVO.getRoleName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pw;
	}

	@Override
	public String getUsername() {
		// ID 반환
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정의 만료 여부
		// true  : 만료 안됨
		// false : 만료 됨, 로그인 불가
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부
		// true  : 계정이 잠기지 않음
		// false : 계정이 잠김, 로그인 불가
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부
		// true  : 만료 안됨
		// false : 만료 됨, 로그인 안됨
		return true;
	}
	
	//isEnabled
	public boolean isEnabled() {
		// 계정 사용 여부
		// true  : 계정 활성화(계정 사용 가능)
		// false : 계정 비활성화(계정 사용 불가, 로그인 불가)
		return true;
	}

	// MemberSocialService의 auth2User.getAttributes()임
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}
	
}
