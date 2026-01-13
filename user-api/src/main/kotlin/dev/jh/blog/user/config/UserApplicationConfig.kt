package dev.jh.blog.user.config

import dev.jh.blog.user.application.port.`in`.UserDeactivationUseCase
import dev.jh.blog.user.application.port.`in`.UserProfileUseCase
import dev.jh.blog.user.application.port.`in`.UserProvisioningUseCase
import dev.jh.blog.user.application.service.UserService
import dev.jh.blog.user.domain.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 유저 도메인 유스케이스를 스프링 빈으로 조립한다.
 */
@Configuration
class UserApplicationConfig {
	/**
	 * 유저 유스케이스를 단일 서비스로 구성한다.
	 */
	@Bean
	fun userService(userRepository: UserRepository): UserService {
		return UserService(userRepository)
	}

	/**
	 * 프로비저닝 유스케이스를 노출한다.
	 */
	@Bean
	fun userProvisioningUseCase(userService: UserService): UserProvisioningUseCase = userService

	/**
	 * 프로필 유스케이스를 노출한다.
	 */
	@Bean
	fun userProfileUseCase(userService: UserService): UserProfileUseCase = userService

	/**
	 * 탈퇴 유스케이스를 노출한다.
	 */
	@Bean
	fun userDeactivationUseCase(userService: UserService): UserDeactivationUseCase = userService
}
