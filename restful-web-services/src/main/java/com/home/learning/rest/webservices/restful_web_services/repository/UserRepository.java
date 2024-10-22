package com.home.learning.rest.webservices.restful_web_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.learning.rest.webservices.restful_web_services.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
