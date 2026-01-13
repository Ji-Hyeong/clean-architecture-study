package dev.jh.blog.community.domain.model

import java.util.UUID

/**
 * 게시글을 식별하기 위한 값 객체이다.
 *
 * - 문자열을 래핑해 식별자 사용 의도를 명확히 한다.
 * - 생성 책임을 한 곳에 두어 규칙을 일관되게 유지한다.
 */
@JvmInline
value class PostId(val value: String) {
	companion object {
		/**
		 * 애플리케이션 내부에서 새 식별자가 필요할 때 사용하는 팩토리다.
		 * UUID를 사용해 충돌 가능성을 낮춘다.
		 */
		fun newId(): PostId = PostId(UUID.randomUUID().toString())
	}
}
