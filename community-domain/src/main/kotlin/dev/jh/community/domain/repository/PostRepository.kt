package dev.jh.community.domain.repository

import dev.jh.community.domain.model.Post
import dev.jh.community.domain.model.PostId

/**
 * 게시글 저장소 포트다.
 *
 * - 저장/조회 규약만 제공하며 구현은 인프라 계층에 둔다.
 * - 도메인 계층은 구체 구현에 의존하지 않는다.
 */
interface PostRepository {
	/**
	 * 게시글을 저장하고 저장된 결과를 반환한다.
	 */
	fun save(post: Post): Post

	/**
	 * 식별자로 게시글을 조회한다. 없으면 null을 반환한다.
	 */
	fun findById(id: PostId): Post?
}
