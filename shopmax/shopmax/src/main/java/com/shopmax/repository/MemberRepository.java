package com.shopmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmax.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	//select * from member where email = ? 이메일 중복확인
	Member findByEmail(String email);
}
