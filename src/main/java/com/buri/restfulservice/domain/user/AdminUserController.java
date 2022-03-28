package com.buri.restfulservice.domain.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

	private final UserService userService;

	public AdminUserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public MappingJacksonValue retrieveAllUsers() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
			.filterOutAllExcept("id", "name", "createdAt");

		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(userService.getUsers());
		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable Long id) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
			.filterOutAllExcept("id", "name", "createdAt", "password");

		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(userService.getUser(id));
		mapping.setFilters(filters);

		return mapping;
	}
}
