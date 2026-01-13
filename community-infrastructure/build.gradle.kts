plugins {
	// 인프라 계층은 스프링 빈 등록을 위해 스프링 플러그인을 사용한다.
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	// 스프링 의존성 버전을 부트 BOM으로 정렬한다.
	implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
	// 도메인에 정의된 포트를 구현하기 위해 도메인 모듈을 참조한다.
	implementation(project(":community-domain"))
	// @Repository 같은 스테레오타입을 쓰기 위해 스프링 컨텍스트를 추가한다.
	implementation("org.springframework:spring-context")
}
