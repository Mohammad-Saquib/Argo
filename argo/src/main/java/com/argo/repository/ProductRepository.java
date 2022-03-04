package com.argo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.argo.user.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
