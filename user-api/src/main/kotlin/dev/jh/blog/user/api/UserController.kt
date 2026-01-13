package dev.jh.blog.user.api

import dev.jh.blog.shared.ApiResponse
import dev.jh.blog.user.application.port.`in`.UpdateUserProfileCommand
import dev.jh.blog.user.application.port.`in`.UserDeactivationCommand
import dev.jh.blog.user.application.port.`in`.UserDeactivationUseCase
import dev.jh.blog.user.application.port.`in`.UserProfileUseCase
import dev.jh.blog.user.application.port.`in`.UserProvisioningCommand
import dev.jh.blog.user.application.port.`in`.UserProvisioningUseCase
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * 유저 관련 API를 제공하는 컨트롤러다.
 *
 * - 인증은 OIDC/OAuth2 리소스 서버로 처리한다.
 * - 유저 프로비저닝, 조회, 수정, 탈퇴를 담당한다.
 */
@RestController
class UserController(
	private val userProvisioningUseCase: UserProvisioningUseCase,
	private val userProfileUseCase: UserProfileUseCase,
	private val userDeactivationUseCase: UserDeactivationUseCase
) {
	/**
	 * OIDC 로그인 이후 유저를 최초 등록(프로비저닝)한다.
	 */
	@PostMapping("/users/me")
	@ResponseStatus(HttpStatus.CREATED)
	fun provision(
		@Valid @RequestBody request: ProvisionUserRequest,
		authentication: Authentication
	): ApiResponse<UserResponse> {
		val identity = extractIdentity(authentication)
		val result = userProvisioningUseCase.ensure(
			UserProvisioningCommand(
				subject = identity.subject,
				email = identity.email,
				nickname = request.nickname,
				bio = request.bio
			)
		)
		return ApiResponse.success(result.toResponse(), "created")
	}

	/**
	 * 현재 로그인한 유저 정보를 조회한다.
	 */
	@GetMapping("/users/me")
	fun me(authentication: Authentication): ApiResponse<UserResponse> {
		val identity = extractIdentity(authentication)
		val result = userProfileUseCase.get(identity.subject)
		return ApiResponse.success(result.toResponse(), "ok")
	}

	/**
	 * 현재 로그인한 유저의 프로필을 수정한다.
	 */
	@PutMapping("/users/me")
	fun update(
		@Valid @RequestBody request: UpdateProfileRequest,
		authentication: Authentication
	): ApiResponse<UserResponse> {
		val identity = extractIdentity(authentication)
		val result = userProfileUseCase.update(
			UpdateUserProfileCommand(
				userId = identity.subject,
				nickname = request.nickname,
				bio = request.bio
			)
		)
		return ApiResponse.success(result.toResponse(), "updated")
	}

	/**
	 * 현재 로그인한 유저를 비활성화(탈퇴)한다.
	 */
	@DeleteMapping("/users/me")
	fun deactivate(authentication: Authentication): ApiResponse<UserResponse> {
		val identity = extractIdentity(authentication)
		val result = userDeactivationUseCase.deactivate(
			UserDeactivationCommand(identity.subject)
		)
		return ApiResponse.success(result.toResponse(), "deactivated")
	}

	/**
	 * 인증 정보에서 OIDC 식별자를 추출한다.
	 */
	private fun extractIdentity(authentication: Authentication): OidcIdentity {
		val principal = authentication.principal
		return when (principal) {
			is Jwt -> OidcIdentity(
				subject = principal.subject,
				email = principal.getClaimAsString("email") ?: ""
			)
			is OidcUser -> OidcIdentity(
				subject = principal.subject,
				email = principal.email ?: ""
			)
			else -> error("unsupported principal")
		}
	}
}

/**
 * OIDC 주체 정보를 표현한다.
 */
data class OidcIdentity(
	val subject: String,
	val email: String
)

/**
 * 유저 프로비저닝 요청 DTO다.
 */
data class ProvisionUserRequest(
	@field:NotBlank(message = "nickname must not be blank")
	@field:Size(min = 2, max = 20, message = "nickname length must be 2..20")
	val nickname: String,
	@field:Size(max = 200, message = "bio length must be <= 200")
	val bio: String
)

/**
 * 프로필 수정 요청 DTO다.
 */
data class UpdateProfileRequest(
	@field:NotBlank(message = "nickname must not be blank")
	@field:Size(min = 2, max = 20, message = "nickname length must be 2..20")
	val nickname: String,
	@field:Size(max = 200, message = "bio length must be <= 200")
	val bio: String
)

/**
 * 유저 응답 DTO다.
 */
data class UserResponse(
	val id: String,
	val email: String,
	val nickname: String,
	val bio: String,
	val status: String,
	val createdAt: String,
	val updatedAt: String
)

/**
 * 유스케이스 응답 모델을 API 응답 DTO로 변환한다.
 */
private fun dev.jh.blog.user.application.port.`in`.UserResult.toResponse(): UserResponse {
	return UserResponse(
		id = id,
		email = email,
		nickname = nickname,
		bio = bio,
		status = status,
		createdAt = createdAt.toString(),
		updatedAt = updatedAt.toString()
	)
}
