package dev.jh.community.config

import dev.jh.community.application.port.`in`.CreatePostUseCase
import dev.jh.community.application.service.CreatePostService
import dev.jh.community.domain.repository.PostRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 애플리케이션 계층의 유스케이스를 스프링 빈으로 조립한다.
 *
 * - 애플리케이션 계층은 스프링에 의존하지 않으므로 이곳에서 조립한다.
 */
@Configuration
class ApplicationConfig {
	/**
	 * 게시글 생성 유스케이스를 구성한다.
	 */
	@Bean
	fun createPostUseCase(postRepository: PostRepository): CreatePostUseCase {
		return CreatePostService(postRepository)
	}
}
