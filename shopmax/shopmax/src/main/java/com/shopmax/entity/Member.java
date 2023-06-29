package com.shopmax.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopmax.constant.Role;
import com.shopmax.dto.MemberFormDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true) //중복된 값이 들어올 수 없다 이메일로가입하기떄문
	private String email;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	//MemberFormDto를 => Member 엔티티 객체로 변환
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		//패스워드 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());
	
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		member.setPassword(password);
		member.setRole(Role.ADMIN);
		
		return member;
	}
}
