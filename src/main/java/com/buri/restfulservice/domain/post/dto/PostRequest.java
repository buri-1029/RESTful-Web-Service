package com.buri.restfulservice.domain.post.dto;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

	@Size(min = 10, message = "내용은 10글자 이상 입력해주세요.")
	String desc;
}
