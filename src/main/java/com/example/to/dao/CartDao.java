package com.example.to.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.to.entity.Cart;

@Mapper
public interface CartDao {
	@Select("select c.uid, c.iid, c.count, i.name AS name, i.price AS price from cart c join item i on c.iid = i.iid where c.uid=#{uid}")
	List<Cart> getCartList(String uid);
}