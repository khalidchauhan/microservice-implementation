package com.home.learning.rest.webservices.restful_web_services.dao;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.repository.PostRepository;

@Service
public class PostJpaService {
	
	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post save(Post newPost) throws Exception {
		// Validate input post
		if (Objects.isNull(newPost)) {
			throw new InvalidObjectException("Post object is null");
		}

		// Check if the post already exists by ID
		if (Objects.nonNull(newPost.getId())) {
			Predicate<Post> postIdMatches = (existingPost) -> existingPost.getId().equals(newPost.getId());

			boolean postAlreadyExists = postRepository.findAll().stream().anyMatch(postIdMatches);

			if (postAlreadyExists) {
				throw new Exception("Post object is already present");
			}
		}

		// Add the new user to the list and return it
		return postRepository.save(newPost);
	}

	public Optional<Post> findOne(int id) {
		return postRepository.findById(id);
	}

	public void deleteById(int id) {
		postRepository.deleteById(id);
	}
}
