package com.ko.home.member;

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
public class MemberVO {
	
	@NotBlank(message = "아이디는 필수입니다.")
	private String id;
	@NotBlank
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\\\\\W)(?=\\\\\\\\S+$).{6,12}")
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
	
}
