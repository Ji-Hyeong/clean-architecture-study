package dev.jh.blog.user.application.port.`in`

/**
 * 유저 프로필 조회 및 수정 유스케이스다.
 */
interface UserProfileUseCase {
	/**
	 * 유저 식별자로 프로필을 조회한다.
	 */
	fun get(userId: String): UserResult

	/**
	 * 유저 프로필을 갱신한다.
	 */
	fun update(command: UpdateUserProfileCommand): UserResult
}

/**
 * 유저 프로필 수정 입력 모델이다.
 */
data class UpdateUserProfileCommand(
	val userId: String,
	val nickname: String,
	val bio: String
)
