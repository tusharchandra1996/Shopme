package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositorytest {
	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 2);
		User user = new User("name1@codejava.net", "name2020", "Tushar", "Chandra");
		user.addRole(roleAdmin);

		User savedUsed = repo.save(user);
		assertThat(savedUsed.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateNewUserWithTwoRoles() {
		
		User user1 = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssitant = new Role(5);
		user1.addRole(roleEditor);
		user1.addRole(roleAssitant);
		
		User savedUsed = repo.save(user1);
		assertThat(savedUsed.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "ravi@gmail.com";
		User user =repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
}
