package dev.jh.blog.community

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * 애플리케이션 진입점이다.
 *
 * - 스프링 부트 자동 설정과 컴포넌트 스캔을 활성화한다.
 * - 멀티모듈 구성에서도 동일 패키지 기준으로 스캔되도록 최상위 패키지에 둔다.
 */
@SpringBootApplication
class CommunityApplication

/**
 * 스프링 부트 애플리케이션을 실행한다.
 */
fun main(args: Array<String>) {
	runApplication<CommunityApplication>(*args)
}
