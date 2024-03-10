package com.pratik.service;

import com.pratik.model.Order;
import com.pratik.model.User;
import com.pratik.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest order, User user) throws Exception;

    Order updateOrder(Long orderId, String orderStatus) throws Exception;

    void cancelOrder(Long orderId) throws Exception;

    Order findOrderById(Long orderId) throws Exception;

    List<Order> getUsersOrders(Long userId) throws Exception;

    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
}
