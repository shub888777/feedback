package com.shubham.Product.Products;

import com.shubham.Product.Exception.ProductException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {
    final private ProductService productService;

    @PostMapping()
    public Products create(@RequestBody Products products) throws ProductException {
        return productService.addProduct(products);
    }
    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) throws ProductException {
        return productService.getProductById(id);
    }
    @PatchMapping("/{id}")
    public Products updateProduct(@PathVariable Long id,@RequestBody Products products) throws ProductException {
        return productService.updateProduct(id,products);
    }
    @GetMapping
    public List<Products> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/categories/{categories}")
    public List <Products> getProductByCategory(@PathVariable String categories){
        return productService.getProductByCategories(categories);
    }
    @PatchMapping("{id}/isEnabled/{isEnabled}")
    public Products setISEnabled(@PathVariable boolean isEnabled, @PathVariable Long id){
        return productService.EnableProduct(id,isEnabled);
    }
}
