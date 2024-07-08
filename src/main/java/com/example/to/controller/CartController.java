package com.example.to.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.to.entity.Cart;
import com.example.to.entity.User;
import com.example.to.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	
	@GetMapping("/list")
    public String loginPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("error", "로그인을 해주세요.");
            return "login";
        }
		String uid = user.getUid();
		List<Cart> cartList = cartService.getCartList(uid);
		int totalPrice = cartService.cartTotalPrice(cartList);
		model.addAttribute("uid", uid);
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        return "cartList";
    }
}
