package com.pratik.service.impl;

import com.pratik.model.Cart;
import com.pratik.model.CartItem;
import com.pratik.model.Food;
import com.pratik.model.User;
import com.pratik.repository.CartItemRepository;
import com.pratik.repository.CartRepository;
import com.pratik.repository.FoodRepository;
import com.pratik.request.AddCartItemRequest;
import com.pratik.service.CartService;
import com.pratik.service.FoodService;
import com.pratik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for(CartItem cartItem : cart.getCartItemList()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.getCartItemList().add(cartItem);
        cartRepository.save(cart);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem Not Found...");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem Not Found...");
        }
        CartItem item = cartItem.get();
        cart.getCartItemList().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long cartTotal = 0L;
        for (CartItem cartItem : cart.getCartItemList()){
            cartTotal += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return cartTotal;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("Cart Not Found...");
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        Cart cart = findCartByUserId(jwt);
        cart.getCartItemList().clear();
        return cartRepository.save(cart);
    }
}
