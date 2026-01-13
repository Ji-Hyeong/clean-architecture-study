package dev.jh.blog.user.application.port.`in`

import java.time.Instant

/**
 * SSO 로그인 시 유저를 최초 생성하거나 기존 유저를 조회하는 유스케이스다.
 */
interface UserProvisioningUseCase {
	/**
	 * 주어진 식별 정보로 유저를 보장(존재하지 않으면 생성)한다.
	 */
	fun ensure(command: UserProvisioningCommand): UserResult
}

/**
 * 유저 프로비저닝 입력 모델이다.
 *
 * - subject: OIDC subject
 * - email: OIDC 이메일 클레임
 * - nickname: 초기 프로필 닉네임
 * - bio: 초기 프로필 소개
 */
data class UserProvisioningCommand(
	val subject: String,
	val email: String,
	val nickname: String,
	val bio: String
)

/**
 * 유저 응답 모델이다.
 */
data class UserResult(
	val id: String,
	val email: String,
	val nickname: String,
	val bio: String,
	val status: String,
	val createdAt: Instant,
	val updatedAt: Instant
)
