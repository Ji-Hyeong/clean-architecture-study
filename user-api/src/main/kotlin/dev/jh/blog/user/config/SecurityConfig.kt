package dev.jh.blog.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * OIDC 기반 인증을 적용하는 보안 설정이다.
 *
 * - 리소스 서버(JWT)와 OIDC 로그인 모두를 활성화한다.
 * - 실제 로그인은 외부 IdP에서 수행한다.
 */
@Configuration
class SecurityConfig {
	/**
	 * 기본 보안 필터 체인을 구성한다.
	 */
	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.csrf { it.disable() }
			.authorizeHttpRequests {
				it.requestMatchers("/users/**").authenticated()
				it.anyRequest().permitAll()
			}
			.oauth2Login { }
			.oauth2ResourceServer { it.jwt { } }

		return http.build()
	}
}
