package dev.jh.user.domain.repository

import dev.jh.user.domain.model.User
import dev.jh.user.domain.model.UserId

/**
 * 유저 저장소 포트다.
 *
 * - 구현은 인프라 계층에서 제공한다.
 */
interface UserRepository {
	/**
	 * 식별자로 유저를 조회한다.
	 */
	fun findById(id: UserId): User?

	/**
	 * 이메일로 유저를 조회한다.
	 */
	fun findByEmail(email: String): User?

	/**
	 * 유저를 저장하고 결과를 반환한다.
	 */
	fun save(user: User): User
}
