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

import com.home.learning.rest.webservices.restful_web_services.dao.PostJpaService;
import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.exception.PostNotFoundException;

import jakarta.validation.Valid;

@RestController
public class PostJpaController {

	@Autowired
	private PostJpaService postJpaService;

	public PostJpaController(PostJpaService postJpaService) {
		this.postJpaService = postJpaService;
	}

	@GetMapping("/jpa/posts")
	public List<Post> retrieveAllPosts() {
		return postJpaService.findAll();
	}

	@GetMapping("/jpa/posts/{id}")
	public EntityModel<Post> retrievePost(@PathVariable int id) {
		Optional<Post> post = postJpaService.findOne(id);

		if (post.isEmpty()) {
			throw new PostNotFoundException("Post not found with ID: " + id);
		} else {
			EntityModel<Post> entityModel = EntityModel.of(post.get());

			WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllPosts());
			entityModel.add(webMvcLinkBuilder.withRel("all-posts"));

			return entityModel;
		}
	}

	@PostMapping("/jpa/posts")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws Exception {
		Post savedPost = postJpaService.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/posts/{id}")
	public void deleteById(@PathVariable int id) {
		postJpaService.deleteById(id);
	}

}
