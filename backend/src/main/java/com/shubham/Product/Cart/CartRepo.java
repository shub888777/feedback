package com.shubham.Product.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findByUsersUsername(String username);
    Cart findByUsersUsernameAndId(String username, Long id);

}
