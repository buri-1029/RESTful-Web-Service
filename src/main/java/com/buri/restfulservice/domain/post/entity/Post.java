package com.buri.restfulservice.domain.post.entity;

import com.buri.restfulservice.domain.user.entity.User;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String desc;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	private Post(String desc, User user) {
		this.desc = desc;
		this.user = user;
	}

	public static Post createPost(String desc, User user) {
		return new Post(desc, user);
	}
}
