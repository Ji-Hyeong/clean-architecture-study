package dev.jh.community.application.port.`in`

import java.time.Instant

/**
 * 게시글 생성 유스케이스 포트다.
 *
 * - 외부(컨트롤러)가 호출할 계약을 정의한다.
 * - 구현은 애플리케이션 서비스에서 제공한다.
 */
interface CreatePostUseCase {
	/**
	 * 게시글을 생성하고 결과를 반환한다.
	 */
	fun create(command: CreatePostCommand): CreatePostResult
}

/**
 * 게시글 생성 입력 모델이다.
 *
 * - 검증은 유스케이스 서비스에서 수행한다.
 */
data class CreatePostCommand(
	val title: String,
	val content: String
)

/**
 * 게시글 생성 결과 모델이다.
 */
data class CreatePostResult(
	val id: String,
	val createdAt: Instant
)
