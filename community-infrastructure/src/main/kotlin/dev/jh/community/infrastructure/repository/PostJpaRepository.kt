package dev.jh.community.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository

/**
 * 게시글 JPA 엔티티에 대한 Spring Data 리포지토리다.
 */
interface PostJpaRepository : JpaRepository<PostJpaEntity, String>
