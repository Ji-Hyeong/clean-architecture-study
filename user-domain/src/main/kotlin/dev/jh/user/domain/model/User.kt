package dev.jh.user.domain.model

import java.time.Instant

/**
 * 유저 도메인 엔티티다.
 *
 * - 외부 인증(SSO) 환경을 가정해 비밀번호는 저장하지 않는다.
 * - 계정 상태와 프로필 변경 책임을 도메인에서 관리한다.
 */
data class User(
	val id: UserId,
	val email: String,
	val profile: UserProfile,
	val status: UserStatus,
	val createdAt: Instant,
	val updatedAt: Instant
) {
	/**
	 * 프로필 정보를 갱신한 새 엔티티를 반환한다.
	 */
	fun updateProfile(nickname: String, bio: String, updatedAt: Instant): User {
		return copy(
			profile = UserProfile(nickname = nickname, bio = bio),
			updatedAt = updatedAt
		)
	}

	/**
	 * 유저를 비활성화 상태로 변경한 새 엔티티를 반환한다.
	 */
	fun deactivate(updatedAt: Instant): User {
		return copy(
			status = UserStatus.DEACTIVATED,
			updatedAt = updatedAt
		)
	}
}
