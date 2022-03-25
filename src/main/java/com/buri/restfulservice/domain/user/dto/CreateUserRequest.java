package com.buri.restfulservice.domain.user.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

	@Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
	private String name;

	@Size(min = 8, message = "비밀번호는 8글자 이상 입력해주세요.")
	private String password;

	@Pattern(regexp = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4]\\d{6}",
		message = "주민등록번호 형식에 맞게 입력해주세요.")
	private String ssn;
}
