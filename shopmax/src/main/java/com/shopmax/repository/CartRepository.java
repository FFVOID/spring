package com.shopmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmax.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
