package dev.jh.blog.community.api.post

import dev.jh.blog.community.application.port.`in`.CreatePostCommand
import dev.jh.blog.community.application.port.`in`.CreatePostUseCase
import dev.jh.blog.shared.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * 게시글 생성 API를 제공하는 컨트롤러다.
 *
 * - 프레젠테이션 계층은 HTTP 요청/응답만 책임진다.
 * - 비즈니스 로직은 유스케이스로 위임한다.
 */
@RestController
class PostController(
	private val createPostUseCase: CreatePostUseCase
) {
	/**
	 * 게시글 생성 요청을 처리한다.
	 */
	@PostMapping("/posts")
	@ResponseStatus(HttpStatus.CREATED)
	fun create(@RequestBody request: CreatePostRequest): ApiResponse<CreatePostResponse> {
		val result = createPostUseCase.create(
			CreatePostCommand(
				title = request.title,
				content = request.content
			)
		)

		val response = CreatePostResponse(
			id = result.id,
			createdAt = result.createdAt.toString()
		)

		return ApiResponse.success(response, "created")
	}
}

/**
 * 게시글 생성 요청 DTO다.
 */
data class CreatePostRequest(
	val title: String,
	val content: String
)

/**
 * 게시글 생성 응답 DTO다.
 */
data class CreatePostResponse(
	val id: String,
	val createdAt: String
)
