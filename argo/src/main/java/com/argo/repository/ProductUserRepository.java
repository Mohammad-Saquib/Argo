package com.argo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.argo.user.ProductUser;
import com.argo.user.User;

public interface ProductUserRepository  extends CrudRepository<User, Long> {

		@Query("SELECT u FROM User u WHERE u.username = :username")
		public ProductUser getUserByUsername(@Param("username") String username);
	}

