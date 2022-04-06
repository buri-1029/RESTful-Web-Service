package com.buri.restfulservice.domain.post;

import com.buri.restfulservice.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
