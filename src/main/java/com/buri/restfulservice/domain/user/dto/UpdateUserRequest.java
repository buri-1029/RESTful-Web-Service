package com.buri.restfulservice.domain.user.dto;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

	@Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
	private String name;

	@Size(min = 8, message = "비밀번호는 8글자 이상 입력해주세요.")
	private String password;
}
