package com.shubham.Product.Cart;

import com.shubham.Product.Exception.ProductException;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/pid/{pid}/qty/{qty}")
    public CartExtraDto addCart(@Min (value = 0 ,message = "Product id should be greater the 0")@PathVariable Long pid, Principal principal, @PathVariable int qty) throws ProductException {

         return cartService.addToCart(principal.getName(),pid,qty);
    }

    @GetMapping
    public List<Cart> getMyCart(Principal principal){
        return cartService.myCart(principal.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteMyCart(Principal principal,@Min (value = 0 ,message = "Product id should be greater the 0")

    @PathVariable Long id) throws ProductException {
        cartService.removeFormCart(principal.getName(),id);
        return "CART GOT DELETED";
    }
    @GetMapping("/list")
   public List<CartResponseDto> MyCart(Principal principal){
        return cartService.cartResponseDtoList(principal.getName());
   }


}
