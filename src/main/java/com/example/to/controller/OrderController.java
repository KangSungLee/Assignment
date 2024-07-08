package com.example.to.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.to.entity.Cart;
import com.example.to.entity.Order;
import com.example.to.entity.OrderItem;
import com.example.to.entity.User;
import com.example.to.service.CartService;
import com.example.to.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final CartService cartService;
	
    @PostMapping("/insert")
    public String insertOrder(@RequestParam("uid") String uid,
                              @RequestParam("totalPrice") int totalPrice,
                              @RequestParam("tid") int tid) {
        Order order = Order.builder()
        			  .uid(uid).tid(tid).totalPrice(totalPrice)
        			  .build();
        orderService.insertOrder(order);
        List<Cart> cartList = cartService.getCartList(uid);
        for(Cart item : cartList) {
        	OrderItem orderItem = OrderItem.builder()
        						  .oid(order.getOid()).iid(item.getIid()).name(item.getName())
        						  .count(item.getCount()).price(item.getPrice() / item.getCount())
        						  .build();
        	orderService.insertOrderItem(orderItem);
        }
        
        return "redirect:/order/list"; 
    }
    
    @GetMapping("/list")
	public String orderList(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("error", "로그인을 해주세요.");
            return "login";
        }
		String uid = user.getUid();
		List<Order> order = orderService.getOrderList(uid);
		if (!order.isEmpty()) {
	        List<OrderItem> orderItem = orderService.getOrderItemList();
	        
	        model.addAttribute("uid", uid);
	        model.addAttribute("order", order);
	        model.addAttribute("orderItem", orderItem);
	    }
        return "orderList";
    }
}