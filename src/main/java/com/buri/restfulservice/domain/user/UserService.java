package com.buri.restfulservice.domain.user;

import com.buri.restfulservice.domain.post.PostRepository;
import com.buri.restfulservice.domain.post.dto.PostRequest;
import com.buri.restfulservice.domain.post.dto.PostResponse;
import com.buri.restfulservice.domain.post.entity.Post;
import com.buri.restfulservice.domain.user.dto.CreateUserRequest;
import com.buri.restfulservice.domain.user.dto.UpdateUserRequest;
import com.buri.restfulservice.domain.user.dto.UserResponse;
import com.buri.restfulservice.domain.user.entity.User;
import com.buri.restfulservice.domain.user.exception.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public UserService(
		UserRepository userRepository,
		PostRepository postRepository
	) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	public List<UserResponse> getUsers() {
		return userRepository.findAll()
							 .stream()
							 .map(UserResponse::new)
							 .collect(Collectors.toList());
	}

	public UserResponse getUser(Long id) {
		return new UserResponse(findUser(id));
	}

	public Long createUser(CreateUserRequest createUserRequest) {
		return userRepository.save(User.createUser(createUserRequest))
							 .getId();
	}

	public Long updateUser(Long id, UpdateUserRequest updateUserRequest) {
		User user = findUser(id);
		user.updateUserInfo(updateUserRequest.getName(), updateUserRequest.getPassword());
		userRepository.save(user);

		return user.getId();
	}

	public void deleteUser(Long id) {
		User user = findUser(id);
		userRepository.delete(user);
	}

	public List<PostResponse> getPostsByUser(Long id) {
		User user = findUser(id);

		return user.getPosts()
				   .stream()
				   .map(PostResponse::new)
				   .collect(Collectors.toList());
	}

	public Long createPost(Long id, PostRequest postRequest) {
		User user = findUser(id);

		return postRepository.save(Post.createPost(postRequest.getDesc(), user))
							 .getId();
	}

	private User findUser(Long id) {
		return userRepository.findById(id)
							 .orElseThrow(() -> new UserNotFoundException(
								 String.format("id[%s] not found", id)));
	}
}
