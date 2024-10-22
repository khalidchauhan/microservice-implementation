package com.home.learning.rest.webservices.restful_web_services.dao;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.entity.User;
import com.home.learning.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.home.learning.rest.webservices.restful_web_services.repository.PostRepository;
import com.home.learning.rest.webservices.restful_web_services.repository.UserRepository;

@Service
public class UserJpaService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User newUser) throws Exception {
		// Validate input user
		if (Objects.isNull(newUser)) {
			throw new InvalidObjectException("User object is null");
		}

		// Check if the user already exists by ID
		if (Objects.nonNull(newUser.getId())) {
			Predicate<User> userIdMatches = (existingUser) -> existingUser.getId().equals(newUser.getId());

			boolean userAlreadyExists = userRepository.findAll().stream().anyMatch(userIdMatches);

			if (userAlreadyExists) {
				throw new Exception("User object is already present");
			}
		}

		// Add the new user to the list and return it
		return userRepository.save(newUser);
	}

	public Optional<User> findOne(int id) {
		return userRepository.findById(id);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public List<Post> retrievePostsForUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		return postRepository.findByUser(user.get());
	}

	public Post createPostsForUser(int id, Post post) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		if (Objects.isNull(post)) {
			throw new InvalidObjectException("Post object is null");
		}
		post.setUser(user.get());
		return postRepository.save(post);
	}

	public Optional<Post> retrievePostForUser(int userId, int postId) {
		return postRepository.findById(postId);
	}
}
