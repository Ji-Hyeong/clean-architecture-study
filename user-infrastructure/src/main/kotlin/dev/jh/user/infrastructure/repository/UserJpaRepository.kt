package dev.jh.user.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository

/**
 * 유저 JPA 엔티티에 대한 Spring Data 리포지토리다.
 *
 * - 도메인 포트와 분리해 JPA 전용 조회 메서드를 정의한다.
 */
interface UserJpaRepository : JpaRepository<UserJpaEntity, String> {
	/**
	 * 이메일로 유저를 조회한다.
	 */
	fun findByEmail(email: String): UserJpaEntity?
}
