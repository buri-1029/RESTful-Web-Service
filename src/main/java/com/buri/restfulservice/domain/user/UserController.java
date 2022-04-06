package com.buri.restfulservice.domain.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.buri.restfulservice.domain.post.dto.PostRequest;
import com.buri.restfulservice.domain.post.dto.PostResponse;
import com.buri.restfulservice.domain.user.dto.CreateUserRequest;
import com.buri.restfulservice.domain.user.dto.UpdateUserRequest;
import com.buri.restfulservice.domain.user.dto.UserResponse;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
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
	public List<EntityModel<UserResponse>> retrieveAllUsers() {
		return userService.getUsers()
						  .stream()
						  .map(users -> EntityModel.of(
							  users,
							  linkTo(methodOn(UserController.class)
										 .retrieveAllUsers()).withSelfRel()
						  ))
						  .collect(Collectors.toList());

	}

	@GetMapping("/{id}")
	public EntityModel<UserResponse> retrieveUser(@PathVariable Long id) {
		return EntityModel.of(
			userService.getUser(id),
			linkTo(methodOn(UserController.class).retrieveUser(id)).withSelfRel(),
			linkTo(methodOn(UserController.class).retrieveAllUsers()).withRel("all-users")
		);
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

	@GetMapping("/{id}/posts")
	public List<PostResponse> retrieveAllPostsByUser(@PathVariable Long id) {
		return userService.getPostsByUser(id);
	}

	@PostMapping("/{id}/posts")
	public ResponseEntity<Long> createPost(
		@PathVariable Long id,
		@Valid @RequestBody PostRequest request
	) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(
													  userService.createPost(id, request))
												  .toUri();

		return ResponseEntity.created(location)
							 .build();
	}

}
