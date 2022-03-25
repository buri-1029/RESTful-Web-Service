package com.buri.restfulservice.domain.user.dto;

import com.buri.restfulservice.domain.user.User;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

	private Long id;
	private String name;
	private String password;
	private String ssn;
	private LocalDate createdAt;

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.ssn = user.getSsn();
		this.createdAt = user.getCreatedAt();
	}
}
