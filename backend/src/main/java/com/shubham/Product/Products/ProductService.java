package com.shubham.Product.Products;

import com.shubham.Product.Exception.ProductException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductsRepo pr;

    public Products getProductById(Long id) throws ProductException {
        return pr.findById(id)
                .orElseThrow(() -> new ProductException("Product not found"));
    }
    public Products addProduct(Products products) throws ProductException {
        Products p1=pr.findByName(products.getName());
        if (p1 != null) {
            throw new ProductException("PRODUCT NAME SHOULD BE UNIQUE");
        }
        return pr.save(products);
    }
    public Products updateProduct(Long id,Products productdto) throws ProductException {
        Products products=pr.findById(id).orElseThrow(()->new ProductException("Product not found"));
        if (productdto.getInventory()!=0){
            products.setInventory(productdto.getInventory());
        }
        if (productdto.getPrice()!=0){
            products.setPrice(productdto.getPrice());
        }

        return pr.save(products);
    }

    public Products EnableProduct(Long id, boolean isEnable){
        Products products=pr.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        products.setEnable(isEnable);
        return pr.save(products);
    }
   public List <Products> getAllProduct(){
        return pr.findAll();
   }
   public List <Products> getProductByCategories(String category){
        return pr.findByCategory( category);
   }


}
