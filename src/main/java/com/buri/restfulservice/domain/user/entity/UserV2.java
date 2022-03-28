package com.buri.restfulservice.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class UserV2 extends User {

	private String grade;

	public UserV2(String grade) {
		super();
		this.grade = grade;
	}
}
