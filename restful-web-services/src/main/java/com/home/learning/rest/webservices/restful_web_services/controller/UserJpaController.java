package com.home.learning.rest.webservices.restful_web_services.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.home.learning.rest.webservices.restful_web_services.dao.UserJpaService;
import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.entity.User;
import com.home.learning.rest.webservices.restful_web_services.exception.PostNotFoundException;
import com.home.learning.rest.webservices.restful_web_services.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {

	@Autowired
	private UserJpaService userJpaService;

	public UserJpaController(UserJpaService userJpaService) {
		this.userJpaService = userJpaService;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userJpaService.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userJpaService.findOne(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with ID: " + id);
		} else {
			EntityModel<User> entityModel = EntityModel.of(user.get());

			WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			entityModel.add(webMvcLinkBuilder.withRel("all-users"));

			return entityModel;
		}
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws Exception {
		User savedUser = userJpaService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable int id) {
		userJpaService.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		return userJpaService.retrievePostsForUser(id);
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post)
			throws Exception {
		Post savedPost = userJpaService.createPostsForUser(id, post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{userId}/posts/{postId}")
	public EntityModel<Post> retrievePostsForUser(@PathVariable int userId, @PathVariable int postId) {
		Optional<Post> post = userJpaService.retrievePostForUser(userId, postId);

		if (post.isEmpty()) {
			throw new PostNotFoundException("Post not found with ID: " + postId);
		}
		EntityModel<Post> entityModel = EntityModel.of(post.get());

		WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(PostJpaController.class).retrieveAllPosts());
		entityModel.add(webMvcLinkBuilder.withRel("all-posts"));

		return entityModel;

	}
}
