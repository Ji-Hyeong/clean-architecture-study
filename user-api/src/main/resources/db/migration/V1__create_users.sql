-- 유저 정보를 영속 저장하기 위한 기본 테이블 생성이다.
CREATE TABLE users (
    id VARCHAR(128) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    bio VARCHAR(200) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

-- 이메일은 유저를 식별하는 주요 키이므로 유니크 제약을 둔다.
CREATE UNIQUE INDEX users_email_uindex ON users (email);
