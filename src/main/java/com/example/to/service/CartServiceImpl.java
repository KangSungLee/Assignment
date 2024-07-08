package com.example.to.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.to.dao.CartDao;
import com.example.to.entity.Cart;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartDao cartDao;
	
	@Override
	public List<Cart> getCartList(String uid) {
		List<Cart> cartList = cartDao.getCartList(uid);
        
        for (Cart cart : cartList) {
            int price = cart.getPrice() * cart.getCount();
            cart.setPrice(price);
        }
        return cartList;
	}

	@Override
	public int cartTotalPrice(List<Cart> cartList) {
		int totalPrice = 0;
		for (Cart cart : cartList) {
		    totalPrice += cart.getPrice();
		}
		return totalPrice;
	}

}
