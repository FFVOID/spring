package com.shopmax.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopmax.constant.Role;
import com.shopmax.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true) // 중복된 값이 들어올 수 없다.
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String address;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		// 패스워드 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		
		// MemberFormDto를 -> Member 엔티티 객체로 변환
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		member.setPassword(password);
		// member.setRole(Role.ADMIN); // 관리자로 가입 할때
		member.setRole(Role.USER); // 이란 사용자로 가입 할때
		
		return member;
	}
}
