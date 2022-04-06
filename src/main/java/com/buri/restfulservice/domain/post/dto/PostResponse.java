package com.buri.restfulservice.domain.post.dto;

import com.buri.restfulservice.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {

	private Long id;
	private String desc;
	private String writer;

	public PostResponse(Post post) {
		this.id = post.getId();
		this.desc = post.getDesc();
		this.writer = post.getUser()
						  .getName();
	}
}
