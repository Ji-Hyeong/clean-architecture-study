package dev.jh.community.infrastructure.repository

import dev.jh.community.domain.model.Post
import dev.jh.community.domain.model.PostId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

/**
 * 게시글 정보를 RDB에 저장하기 위한 JPA 엔티티다.
 *
 * - 도메인 모델과 분리해 영속성 제약을 명시한다.
 */
@Entity
@Table(name = "posts")
class PostJpaEntity(
	@Id
	@Column(name = "id", nullable = false, length = 128)
	val id: String = "",
	@Column(name = "title", nullable = false, length = 200)
	val title: String = "",
	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	val content: String = "",
	@Column(name = "created_at", nullable = false)
	val createdAt: Instant = Instant.EPOCH
) {
	companion object {
		/**
		 * 도메인 게시글을 JPA 엔티티로 변환한다.
		 */
		fun fromDomain(post: Post): PostJpaEntity {
			return PostJpaEntity(
				id = post.id.value,
				title = post.title,
				content = post.content,
				createdAt = post.createdAt
			)
		}
	}

	/**
	 * JPA 엔티티를 도메인 게시글로 변환한다.
	 */
	fun toDomain(): Post {
		return Post(
			id = PostId(id),
			title = title,
			content = content,
			createdAt = createdAt
		)
	}
}
