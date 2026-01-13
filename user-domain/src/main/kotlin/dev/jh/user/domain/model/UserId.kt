package dev.jh.user.domain.model

/**
 * 유저 식별자를 표현하는 값 객체다.
 *
 * - OIDC에서 제공되는 subject 값을 그대로 사용해 일관성을 유지한다.
 * - 외부 문자열을 그대로 쓰지 않도록 래핑해 의도를 명확히 한다.
 */
@JvmInline
value class UserId(val value: String) {
	companion object {
		/**
		 * OIDC subject 값을 기반으로 유저 식별자를 생성한다.
		 */
		fun fromSubject(subject: String): UserId = UserId(subject)
	}
}
