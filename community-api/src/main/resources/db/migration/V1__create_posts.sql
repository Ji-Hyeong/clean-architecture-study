-- 게시글 정보를 영속 저장하기 위한 기본 테이블 생성이다.
CREATE TABLE posts (
    id VARCHAR(128) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

-- 조회 효율을 위해 생성일 인덱스를 추가한다.
CREATE INDEX posts_created_at_index ON posts (created_at DESC);
