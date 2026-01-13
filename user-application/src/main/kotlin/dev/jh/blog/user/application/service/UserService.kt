package dev.jh.blog.user.application.service

import dev.jh.blog.user.application.port.`in`.UserDeactivationCommand
import dev.jh.blog.user.application.port.`in`.UserDeactivationUseCase
import dev.jh.blog.user.application.port.`in`.UserProfileUseCase
import dev.jh.blog.user.application.port.`in`.UserProvisioningCommand
import dev.jh.blog.user.application.port.`in`.UserProvisioningUseCase
import dev.jh.blog.user.application.port.`in`.UserResult
import dev.jh.blog.user.application.port.`in`.UpdateUserProfileCommand
import dev.jh.blog.user.domain.model.User
import dev.jh.blog.user.domain.model.UserId
import dev.jh.blog.user.domain.model.UserProfile
import dev.jh.blog.user.domain.model.UserStatus
import dev.jh.blog.user.domain.repository.UserRepository
import java.time.Instant

/**
 * 유저 관련 유스케이스를 통합 제공하는 서비스다.
 */
class UserService(
	private val userRepository: UserRepository
) : UserProvisioningUseCase, UserProfileUseCase, UserDeactivationUseCase {
	/**
	 * OIDC 로그인 시 유저를 조회하고 없으면 생성한다.
	 */
	override fun ensure(command: UserProvisioningCommand): UserResult {
		require(command.subject.isNotBlank()) { "subject must not be blank" }
		require(command.email.isNotBlank()) { "email must not be blank" }
		require(command.nickname.isNotBlank()) { "nickname must not be blank" }

		val userId = UserId.fromSubject(command.subject)
		val existing = userRepository.findById(userId)
		if (existing != null) {
			return existing.toResult()
		}

		val now = Instant.now()
		val user = User(
			id = userId,
			email = command.email,
			profile = UserProfile(
				nickname = command.nickname.trim(),
				bio = command.bio.trim()
			),
			status = UserStatus.ACTIVE,
			createdAt = now,
			updatedAt = now
		)

		return userRepository.save(user).toResult()
	}

	/**
	 * 유저 식별자로 현재 프로필을 조회한다.
	 */
	override fun get(userId: String): UserResult {
		val id = UserId.fromSubject(userId)
		val user = userRepository.findById(id)
			?: error("user not found")
		return user.toResult()
	}

	/**
	 * 유저 프로필을 갱신한다.
	 */
	override fun update(command: UpdateUserProfileCommand): UserResult {
		require(command.nickname.isNotBlank()) { "nickname must not be blank" }

		val id = UserId.fromSubject(command.userId)
		val user = userRepository.findById(id)
			?: error("user not found")
		val updated = user.updateProfile(
			nickname = command.nickname.trim(),
			bio = command.bio.trim(),
			updatedAt = Instant.now()
		)
		return userRepository.save(updated).toResult()
	}

	/**
	 * 유저를 비활성화 상태로 변경한다.
	 */
	override fun deactivate(command: UserDeactivationCommand): UserResult {
		val id = UserId.fromSubject(command.userId)
		val user = userRepository.findById(id)
			?: error("user not found")
		val updated = user.deactivate(Instant.now())
		return userRepository.save(updated).toResult()
	}

	/**
	 * 도메인 유저를 외부 응답 모델로 변환한다.
	 */
	private fun User.toResult(): UserResult {
		return UserResult(
			id = id.value,
			email = email,
			nickname = profile.nickname,
			bio = profile.bio,
			status = status.name,
			createdAt = createdAt,
			updatedAt = updatedAt
		)
	}
}
