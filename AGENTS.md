# AGENTS

## 프로젝트 변경 이력
- 멀티모듈 구조(domain/application/infrastructure/presentation/bootstrap) 도입과 JDK 21 자동 다운로드 설정(foojay resolver, gradle.properties)을 추가하고, 기존 스프링 부트 엔트리 파일을 bootstrap 모듈로 이동.
- foojay toolchain resolver 플러그인 버전을 0.9.0으로 상향해 JDK 자동 다운로드 오류를 해결하도록 조정.
- Homebrew로 OpenJDK 21을 설치하고 Gradle이 해당 경로를 인식하도록 설정, foojay resolver 플러그인은 제거.
- bootstrap 모듈에서 도메인 포트를 직접 참조하므로 domain 모듈 의존성을 명시적으로 추가.
- 실행 모듈을 presentation으로 이동하고 bootstrap 모듈을 제거, Jackson Kotlin 모듈을 추가해 요청 DTO 역직렬화 오류를 해결.
- presentation 모듈에서 도메인 타입을 직접 참조하므로 domain 의존성을 명시적으로 추가.
- presentation 모듈을 api 모듈로 변경해 외부 인터페이스 계층 명칭을 정리.
- 패키지명을 dev.jh.blog.presentation → dev.jh.blog.api 로 변경.
- 커뮤니티/유저 도메인별 클린 아키텍처 모듈 세트로 재구성하고 shared-kernel 모듈을 추가해 공통 응답 모델을 분리.
- 유저 도메인에 OIDC 기반 프로비저닝/조회/수정/탈퇴 유스케이스와 API를 추가하고 보안/검증 설정을 구성.

## 환경/작업 메모
- Gradle 툴체인 자동 다운로드: org.gradle.java.installations.auto-download=true
- 기본 툴체인 버전: Java 21
