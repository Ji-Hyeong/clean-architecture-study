package dev.jh.user.application.port.`in`

/**
 * 유저 탈퇴(비활성화) 유스케이스다.
 */
interface UserDeactivationUseCase {
	/**
	 * 유저를 비활성화한다.
	 */
	fun deactivate(command: UserDeactivationCommand): UserResult
}

/**
 * 유저 탈퇴 입력 모델이다.
 */
data class UserDeactivationCommand(
	val userId: String
)
