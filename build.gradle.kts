import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
	// 모든 모듈에서 동일한 버전을 쓰기 위해 루트에만 선언하고, 각 모듈에서 선택 적용한다.
	kotlin("jvm") version "2.2.21" apply false
	kotlin("plugin.spring") version "2.2.21" apply false
	id("org.springframework.boot") version "4.0.1" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

group = "dev.jh"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

allprojects {
	// 모든 모듈이 동일한 리포지토리 설정을 공유하도록 한다.
	repositories {
		mavenCentral()
	}
}

subprojects {
	plugins.withId("org.jetbrains.kotlin.jvm") {
		// Kotlin/JVM 플러그인 적용 이후에만 toolchain 및 컴파일 옵션을 설정한다.
		extensions.configure<JavaPluginExtension> {
			// JDK 21 툴체인을 기본으로 사용하도록 고정한다.
			toolchain {
				languageVersion = JavaLanguageVersion.of(21)
			}
		}

		extensions.configure<KotlinJvmProjectExtension> {
			compilerOptions {
				// 널 안전성과 어노테이션 기본 타겟을 엄격하게 유지한다.
				freeCompilerArgs.addAll(
					"-Xjsr305=strict",
					"-Xannotation-default-target=param-property"
				)
			}
		}
	}

	tasks.withType<Test> {
		// JUnit 5 플랫폼을 공통 테스트 러너로 사용한다.
		useJUnitPlatform()
	}
}
