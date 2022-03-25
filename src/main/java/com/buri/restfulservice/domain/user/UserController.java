package com.buri.restfulservice.domain.user;

import com.buri.restfulservice.domain.user.dto.CreateUserRequest;
import com.buri.restfulservice.domain.user.dto.UpdateUserRequest;
import com.buri.restfulservice.domain.user.dto.UserResponse;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> retrieveAllUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> retrieveUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@PostMapping()
	public ResponseEntity<Long> createUser(@Valid @RequestBody CreateUserRequest request) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(userService.createUser(request))
												  .toUri();

		return ResponseEntity.created(location)
							 .build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Long> updateUser(
		@PathVariable Long id,
		@Valid @RequestBody UpdateUserRequest request
	) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(
													  userService.updateUser(id, request))
												  .toUri();

		return ResponseEntity.created(location)
							 .build();
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
