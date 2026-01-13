package dev.jh.community.infrastructure.repository

import dev.jh.community.domain.model.Post
import dev.jh.community.domain.model.PostId
import dev.jh.community.domain.repository.PostRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

/**
 * 간단한 메모리 기반 저장소 구현체다.
 *
 * - 학습 단계에서 영속성 없이 동작 확인을 위해 제공한다.
 * - 실서비스에서는 JPA 구현 등으로 교체된다.
 */
@Repository
class InMemoryPostRepository : PostRepository {
	private val storage = ConcurrentHashMap<String, Post>()

	/**
	 * 게시글을 저장하고 결과를 반환한다.
	 */
	override fun save(post: Post): Post {
		storage[post.id.value] = post
		return post
	}

	/**
	 * 식별자로 게시글을 조회한다.
	 */
	override fun findById(id: PostId): Post? = storage[id.value]
}
