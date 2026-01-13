package dev.jh.blog.community.domain.model

import java.time.Instant

/**
 * 블로그 게시글의 핵심 도메인 엔티티다.
 *
 * - 모든 계층이 이 모델을 공유한다.
 * - 프레임워크 의존성을 가지지 않는다.
 */
data class Post(
	val id: PostId,
	val title: String,
	val content: String,
	val createdAt: Instant
)
