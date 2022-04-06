package com.buri.restfulservice.domain.user.entity;

import com.buri.restfulservice.domain.post.entity.Post;
import com.buri.restfulservice.domain.user.dto.CreateUserRequest;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String password;
	private String ssn;
	private LocalDate createdAt;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	private User(String name, String password, String ssn) {
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.createdAt = LocalDate.now();
	}

	public static User createUser(CreateUserRequest createUserRequest) {
		return new User(createUserRequest.getName(), createUserRequest.getPassword(),
						createUserRequest.getSsn()
		);
	}

	public void updateUserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

}
