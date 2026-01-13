plugins {
	// 유저 애플리케이션 계층은 도메인에 의존한다.
	kotlin("jvm")
}

dependencies {
	// 유저 유스케이스는 유저 도메인에만 의존한다.
	implementation(project(":user-domain"))
}
