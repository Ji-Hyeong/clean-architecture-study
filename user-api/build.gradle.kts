plugins {
	// 유저 API 서버를 구성하기 위해 스프링 부트를 적용한다.
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

dependencies {
	// 유저 유스케이스 호출을 위해 애플리케이션 계층에 의존한다.
	implementation(project(":user-application"))
	// 유저 인프라 구현을 포함하기 위해 인프라 계층에 의존한다.
	implementation(project(":user-infrastructure"))
	// 공통 응답 모델을 사용하기 위해 공유 모듈에 의존한다.
	implementation(project(":shared-kernel"))
	// 스프링 부트 웹 실행에 필요한 기본 스타터를 사용한다.
	implementation("org.springframework.boot:spring-boot-starter-web")
	// 검증 어노테이션을 사용하기 위해 validation 스타터를 추가한다.
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// 보안 및 OIDC 인증 처리를 위해 시큐리티 스타터를 추가한다.
	implementation("org.springframework.boot:spring-boot-starter-security")
	// OIDC 로그인과 리소스 서버(JWT) 기능을 사용한다.
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	// Kotlin 데이터 클래스를 JSON으로 역직렬화하기 위해 모듈을 추가한다.
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// 스프링 부트 통합 테스트를 위한 의존성이다.
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
