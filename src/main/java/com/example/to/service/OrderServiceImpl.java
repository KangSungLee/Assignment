package com.example.to.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.to.dao.CartDao;
import com.example.to.dao.OrderDao;
import com.example.to.entity.Order;
import com.example.to.entity.OrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final OrderDao orderDao;
	
	@Override
	public void insertOrder(Order order) {
		orderDao.insertOrder(order);
	}

	@Override
	public void insertOrderItem(OrderItem orderItem) {
		orderDao.insertOrderItem(orderItem);
	}

	@Override
	public List<Order> getOrderList(String uid) {
		return orderDao.getOrderList(uid);
	}

	@Override
	public List<OrderItem> getOrderItemList() {
		return orderDao.getOrderItemList();
	}
}
