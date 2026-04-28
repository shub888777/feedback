package com.shubham.Product.Products;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user/products")
public class ProductUserController {
    final private ProductService productService;
    @GetMapping("/categories/{categories}")
    public List <Products> getProductByCategory(@PathVariable String categories){
        return productService.getProductByCategories(categories);
    }

}
