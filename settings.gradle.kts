rootProject.name = "clean-architecture-study"

// 클린 아키텍처 계층을 모듈로 분리한다.
include(":shared-kernel")

include(":community-domain")
include(":community-application")
include(":community-infrastructure")
include(":community-api")

include(":user-domain")
include(":user-application")
include(":user-infrastructure")
include(":user-api")
