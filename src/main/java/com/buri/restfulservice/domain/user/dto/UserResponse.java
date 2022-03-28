package com.buri.restfulservice.domain.user.dto;

import com.buri.restfulservice.domain.user.entity.User;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @JsonFilter("UserInfo")
// @JsonIgnoreProperties(value = {"password", "ssn"}) // 외부에 노출하고 싶지 않을 때
public class UserResponse {

	private Long id;
	private String name;
	private LocalDate createdAt;
	private String password;
	private String ssn;


	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.ssn = user.getSsn();
		this.createdAt = user.getCreatedAt();
	}
}
