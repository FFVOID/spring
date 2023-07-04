package com.shopmax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //bean객체를 싱글톤(자원을 공유)으로 사용하게 한다 bean객체 쓸때 같이쓰면좋다
@EnableJpaAuditing // Jpa의 auditing 기능을 활성화
public class AuditConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
}
