package dev.jh.user.infrastructure.repository

import dev.jh.user.domain.model.User
import dev.jh.user.domain.model.UserId
import dev.jh.user.domain.model.UserProfile
import dev.jh.user.domain.model.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

/**
 * 유저 정보를 RDB에 저장하기 위한 JPA 엔티티다.
 *
 * - 도메인 모델과 분리해 영속성 제약(컬럼/길이/인덱스)을 명시한다.
 * - Kotlin JPA 플러그인이 no-arg 생성자를 제공한다.
 */
@Entity
@Table(name = "users")
class UserJpaEntity(
	@Id
	@Column(name = "id", nullable = false, length = 128)
	val id: String = "",
	@Column(name = "email", nullable = false, length = 255)
	val email: String = "",
	@Column(name = "nickname", nullable = false, length = 50)
	val nickname: String = "",
	@Column(name = "bio", nullable = false, length = 200)
	val bio: String = "",
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	val status: UserStatus = UserStatus.ACTIVE,
	@Column(name = "created_at", nullable = false)
	val createdAt: Instant = Instant.EPOCH,
	@Column(name = "updated_at", nullable = false)
	val updatedAt: Instant = Instant.EPOCH
) {
	companion object {
		/**
		 * 도메인 유저를 JPA 엔티티로 변환한다.
		 */
		fun fromDomain(user: User): UserJpaEntity {
			return UserJpaEntity(
				id = user.id.value,
				email = user.email,
				nickname = user.profile.nickname,
				bio = user.profile.bio,
				status = user.status,
				createdAt = user.createdAt,
				updatedAt = user.updatedAt
			)
		}
	}

	/**
	 * JPA 엔티티를 도메인 유저로 변환한다.
	 */
	fun toDomain(): User {
		return User(
			id = UserId(id),
			email = email,
			profile = UserProfile(nickname = nickname, bio = bio),
			status = status,
			createdAt = createdAt,
			updatedAt = updatedAt
		)
	}
}
