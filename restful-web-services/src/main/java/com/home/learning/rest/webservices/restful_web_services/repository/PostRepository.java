package com.home.learning.rest.webservices.restful_web_services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
}
