package dev.jh.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * 유저 API 서버의 진입점이다.
 *
 * - 유저 도메인의 컨트롤러와 설정을 스캔한다.
 */
@SpringBootApplication
class UserApplication

/**
 * 유저 API 서버를 실행한다.
 */
fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}
