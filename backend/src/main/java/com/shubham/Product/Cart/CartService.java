package com.shubham.Product.Cart;

import com.shubham.Product.Exception.ProductException;
import com.shubham.Product.Products.Products;
import com.shubham.Product.Products.ProductsRepo;
import com.shubham.Product.Users.UserRepo;
import com.shubham.Product.Users.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private  final CartRepo cartRepo;
   private final UserRepo userRepo;
   private  final ProductsRepo productsRepo;

    public CartExtraDto addToCart(String username,Long id,int qty) throws ProductException {
        Users users=userRepo.findByUsername(username);
        Products products=productsRepo.findById(id).orElseThrow(()->new RuntimeException());

        boolean f=false;
        int left=0;
        if (products.getInventory() < qty) {
            left=qty-products.getInventory();
            qty= products.getInventory();
            f=true;
            CartExtraDto cartExtraDto=new CartExtraDto();
            cartExtraDto.setQty(qty);
            cartExtraDto.setLeft(left);
            cartExtraDto.setMessage("QTY IS > THEN INVENTORY");
            Cart cart=new Cart();
            cart.setProducts(products);
            cart.setUsers(users);
            cart.setQty(qty);
            cartRepo.save(cart);
            return cartExtraDto;
        }

        Cart cart=new Cart();
        cart.setProducts(products);
        cart.setUsers(users);
        cart.setQty(qty);
         cartRepo.save(cart);
        return null;

    }
    public List<Cart> myCart(String username){
        return cartRepo.findByUsersUsername(username);
    }
    public void removeFormCart(String username,Long cid) throws ProductException {
        Cart cart=cartRepo.findByUsersUsernameAndId(username,cid);
        if (cart == null) {
            throw new ProductException("Cart not found ");

        }
         cartRepo.delete(cart);
    }

    public List<CartResponseDto> cartResponseDtoList(String username){
        List<CartResponseDto> cart=new ArrayList<>();
        for(Cart c :cartRepo.findByUsersUsername(username))
        {
            CartResponseDto c1=new CartResponseDto();
            c1.setQty(c.getQty());
            c1.setProductname(c.getProducts().getName());
            c1.setRemainingProduct(c.getProducts().getInventory());
            cart.add(c1);

        }
        return cart;
    }
}
