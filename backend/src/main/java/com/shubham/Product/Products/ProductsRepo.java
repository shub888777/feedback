package com.shubham.Product.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Long> {
    List<Products> findByCategory(String category);
    Products findByName(String name);
}
