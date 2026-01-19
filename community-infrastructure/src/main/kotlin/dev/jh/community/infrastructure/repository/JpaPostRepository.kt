package dev.jh.community.infrastructure.repository

import dev.jh.community.domain.model.Post
import dev.jh.community.domain.model.PostId
import dev.jh.community.domain.repository.PostRepository
import org.springframework.stereotype.Repository

/**
 * JPA 기반 게시글 저장소 어댑터다.
 *
 * - 도메인 포트를 Spring Data JPA로 연결한다.
 */
@Repository
class JpaPostRepository(
	private val postJpaRepository: PostJpaRepository
) : PostRepository {
	/**
	 * 게시글을 저장하고 도메인 모델로 반환한다.
	 */
	override fun save(post: Post): Post {
		val saved = postJpaRepository.save(PostJpaEntity.fromDomain(post))
		return saved.toDomain()
	}

	/**
	 * 식별자로 게시글을 조회한다.
	 */
	override fun findById(id: PostId): Post? {
		return postJpaRepository.findById(id.value).orElse(null)?.toDomain()
	}
}
