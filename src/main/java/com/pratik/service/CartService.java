package com.pratik.service;

import com.pratik.model.Cart;
import com.pratik.model.CartItem;
import com.pratik.request.AddCartItemRequest;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    Long calculateCartTotals(Cart cart) throws Exception;

    Cart findCartById(Long id) throws Exception;

    Cart findCartByUserId(String jwt) throws Exception;

    Cart clearCart(String jwt) throws Exception;
}
