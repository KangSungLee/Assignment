package com.example.to.service;

import java.util.List;

import com.example.to.entity.Cart;

public interface CartService {
	
	List<Cart> getCartList(String uid);
	
	int cartTotalPrice(List<Cart> cartList);
}
