package dev.jh.user.infrastructure.repository

import dev.jh.user.domain.model.User
import dev.jh.user.domain.model.UserId
import dev.jh.user.domain.repository.UserRepository
import org.springframework.stereotype.Repository

/**
 * JPA 기반 유저 저장소 어댑터다.
 *
 * - 도메인 포트(UserRepository)를 Spring Data JPA로 연결한다.
 * - 매핑 로직은 엔티티 변환 함수에 위임한다.
 */
@Repository
class JpaUserRepository(
	private val userJpaRepository: UserJpaRepository
) : UserRepository {
	/**
	 * 식별자로 유저를 조회한다.
	 */
	override fun findById(id: UserId): User? {
		return userJpaRepository.findById(id.value).orElse(null)?.toDomain()
	}

	/**
	 * 이메일로 유저를 조회한다.
	 */
	override fun findByEmail(email: String): User? {
		return userJpaRepository.findByEmail(email)?.toDomain()
	}

	/**
	 * 유저를 저장하고 도메인 모델로 반환한다.
	 */
	override fun save(user: User): User {
		val saved = userJpaRepository.save(UserJpaEntity.fromDomain(user))
		return saved.toDomain()
	}
}
