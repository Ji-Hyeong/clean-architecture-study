plugins {
	// 프레젠테이션 계층에서 스프링 부트 실행까지 담당한다.
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

dependencies {
	// 유스케이스 호출을 위해 애플리케이션 계층에 의존한다.
	implementation(project(":community-application"))
	// 저장소 구현과 빈 구성을 포함하기 위해 인프라 계층에 의존한다.
	implementation(project(":community-infrastructure"))
	// 구성 클래스에서 도메인 포트를 직접 참조하므로 명시적으로 의존한다.
	implementation(project(":community-domain"))
	// 공통 응답 모델을 사용하기 위해 공유 모듈에 의존한다.
	implementation(project(":shared-kernel"))
	// 스프링 부트 웹 실행에 필요한 기본 스타터를 사용한다.
	implementation("org.springframework.boot:spring-boot-starter-web")
	// Kotlin 데이터 클래스를 JSON으로 역직렬화하기 위해 모듈을 추가한다.
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// 스프링 부트 통합 테스트를 위한 의존성이다.
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
