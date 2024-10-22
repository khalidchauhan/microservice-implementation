package com.home.learning.rest.webservices.restful_web_services.dao;

import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.home.learning.rest.webservices.restful_web_services.entity.Post;
import com.home.learning.rest.webservices.restful_web_services.entity.User;
import com.home.learning.rest.webservices.restful_web_services.exception.UserNotFoundException;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();

	private static int usersCount = 0;

	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30), List.of(new Post())));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25), List.of(new Post())));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20), List.of(new Post())));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User newUser) throws Exception {
		// Validate input user
		if (Objects.isNull(newUser)) {
			throw new InvalidObjectException("User object is null");
		}

		// Check if the user already exists by ID
		if (Objects.nonNull(newUser.getId())) {
			Predicate<User> userIdMatches = (existingUser) -> existingUser.getId().equals(newUser.getId());

			boolean userAlreadyExists = users.stream().anyMatch(userIdMatches);

			if (userAlreadyExists) {
				throw new Exception("User object is already present");
			}
		}

		// Add the new user to the list and return it
		newUser.setId(++usersCount);
		users.add(newUser);
		return newUser;
	}

	public User findOne(int id) {
		// Define a predicate to check if a user's ID matches the given ID
		Predicate<User> userIdMatches = (user) -> user.getId().equals(id);

		// Stream through users and find the first matching user
		return users.stream().filter(userIdMatches).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		// Attempt to remove a user with the specified ID
		Predicate<User> userIdMatches = (user) -> user.getId().equals(id);

		boolean userRemoved = users.removeIf(userIdMatches);
	    
	    // If no user was removed, throw an exception
	    if (!userRemoved) {
	        throw new UserNotFoundException("User not found with ID: " + id);
	    }
		
	}
}
