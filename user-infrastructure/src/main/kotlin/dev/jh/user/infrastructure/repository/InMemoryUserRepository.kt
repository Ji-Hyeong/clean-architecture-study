package dev.jh.user.infrastructure.repository

import dev.jh.user.domain.model.User
import dev.jh.user.domain.model.UserId
import dev.jh.user.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

/**
 * 인메모리 기반 유저 저장소 구현체다.
 *
 * - 학습 단계에서 영속성 없이 동작 확인을 위해 제공한다.
 */
@Repository
class InMemoryUserRepository : UserRepository {
	private val storage = ConcurrentHashMap<String, User>()

	/**
	 * 식별자로 유저를 조회한다.
	 */
	override fun findById(id: UserId): User? = storage[id.value]

	/**
	 * 이메일로 유저를 조회한다.
	 */
	override fun findByEmail(email: String): User? =
		storage.values.firstOrNull { it.email == email }

	/**
	 * 유저를 저장하고 결과를 반환한다.
	 */
	override fun save(user: User): User {
		storage[user.id.value] = user
		return user
	}
}
