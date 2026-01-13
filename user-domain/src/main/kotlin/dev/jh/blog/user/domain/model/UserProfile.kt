package dev.jh.blog.user.domain.model

/**
 * 유저 프로필 정보를 표현하는 값 객체다.
 */
data class UserProfile(
	val nickname: String,
	val bio: String
)
