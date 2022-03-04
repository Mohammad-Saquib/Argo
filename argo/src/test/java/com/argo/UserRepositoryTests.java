package com.argo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.argo.repository.RoleRepository;
import com.argo.repository.UserRepository;
import com.argo.user.Role;
import com.argo.user.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("ravikumar@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Ravi");
		user.setLastName("Kumar");
		user.setDOB("07-02-2022");
		user.setContactNumber("8477942894");
		user.setAddress("Buladshahr");
		User savedUser = userRepo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testAddRoleToNewUser() {
		Role roleAdmin = roleRepo.findByName("Admin");
		
		User user = new User();
		user.setEmail("mikes.gates@gmail.com");
		user.setPassword("mike2020");
		user.setFirstName("Mike");
		user.setLastName("Gates");
		user.setDOB("12-04-2021");
		user.setContactNumber("9897620850");
		user.setAddress("Buladshahr");
		user.addRole(roleAdmin);
		
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}
	
	@Test
	public void testAddRoleToExistingUser() {
		User user = userRepo.findById(1L).get();
		Role roleUser = roleRepo.findByName("User");
		Role roleCustomer = new Role(3); 		
		user.addRole(roleUser);
		user.addRole(roleCustomer);
		
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getRoles().size()).isEqualTo(2);		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "argo@gmail.com";
		User user = userRepo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
