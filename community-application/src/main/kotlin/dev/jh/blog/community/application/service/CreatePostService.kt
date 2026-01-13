package dev.jh.blog.community.application.service

import dev.jh.blog.community.application.port.`in`.CreatePostCommand
import dev.jh.blog.community.application.port.`in`.CreatePostResult
import dev.jh.blog.community.application.port.`in`.CreatePostUseCase
import dev.jh.blog.community.domain.model.Post
import dev.jh.blog.community.domain.model.PostId
import dev.jh.blog.community.domain.repository.PostRepository
import java.time.Instant

/**
 * 게시글 생성 유스케이스의 실제 구현체다.
 *
 * - 도메인 모델을 생성하고 저장소 포트를 통해 저장한다.
 * - 트랜잭션 경계는 상위 계층(스프링)에서 관리한다.
 */
class CreatePostService(
	private val postRepository: PostRepository
) : CreatePostUseCase {
	/**
	 * 입력 값을 기반으로 게시글을 생성한다.
	 */
	override fun create(command: CreatePostCommand): CreatePostResult {
		// 간단한 유효성 검사를 수행한다.
		require(command.title.isNotBlank()) { "title must not be blank" }
		require(command.content.isNotBlank()) { "content must not be blank" }

		val now = Instant.now()
		val post = Post(
			id = PostId.newId(),
			title = command.title.trim(),
			content = command.content.trim(),
			createdAt = now
		)

		postRepository.save(post)

		return CreatePostResult(
			id = post.id.value,
			createdAt = now
		)
	}
}
