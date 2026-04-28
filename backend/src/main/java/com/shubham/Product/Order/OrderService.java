package com.shubham.Product.Order;

import com.shubham.Product.Cart.Cart;
import com.shubham.Product.Cart.CartRepo;
import com.shubham.Product.Exception.ProductException;
import com.shubham.Product.Products.Products;
import com.shubham.Product.Products.ProductsRepo;
import com.shubham.Product.Users.UserRepo;
import com.shubham.Product.Users.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepo orderRepo;
    private UserRepo userRepo;
    private CartRepo cartRepo;
    private ProductsRepo productsRepo;

    @Transactional
    public Order addToOrder(OrderRequestDto orderRequestDto,String username) throws ProductException {
        Cart cart=cartRepo.findByUsersUsernameAndId(username, orderRequestDto.getCartid());
        if (cart!=null) {
            Users users=userRepo.findByUsername(username);
            Products products=productsRepo.findById(cart.getProducts().getId()).get();
            Order order=new Order();
            order.setAddress(orderRequestDto.getAddress());
            order.setPrice(cart.getQty()*cart.getProducts().getPrice());
            order.setProducts(cart.getProducts());
            if (cart.getQty()> products.getInventory()) {
                throw new ProductException("Cart qty is greater then products in Inventory9");
            }
            if(!products.isEnable()){
                throw new ProductException("Product is not Available");
            }
            order.setQty(cart.getQty());
            order.setUsers(users);
            cartRepo.delete(cart);
            products.setInventory(products.getInventory()-cart.getQty());
            productsRepo.save(products);
            return orderRepo.save(order);

        }
        System.out.println("here");
        return new Order();
    }
    public List<Order> getAllOrder(){
        return orderRepo.findAll();
    }

}
