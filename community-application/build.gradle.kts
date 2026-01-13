plugins {
	// 애플리케이션 계층은 도메인에 의존하며, 프레임워크 의존은 피한다.
	kotlin("jvm")
}

dependencies {
	// 유스케이스는 도메인 모델과 포트를 통해 동작한다.
	implementation(project(":community-domain"))
}
