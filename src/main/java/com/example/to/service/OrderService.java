package com.example.to.service;

import java.util.List;

import com.example.to.entity.Order;
import com.example.to.entity.OrderItem;

public interface OrderService {
	void insertOrder(Order order);
	
	void insertOrderItem(OrderItem orderItem);
	
	List<Order> getOrderList(String uid);
	
	List<OrderItem> getOrderItemList();
}
