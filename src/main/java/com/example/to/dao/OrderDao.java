package com.example.to.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.example.to.entity.Order;
import com.example.to.entity.OrderItem;

@Mapper
public interface OrderDao {
	@Insert("insert into `order` (uid, tid, totalPrice) values (#{uid}, #{tid}, #{totalPrice})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="oid", before=false, resultType=int.class)
    void insertOrder(Order order);
    
    @Insert("insert into orderItem (oid, iid, name, count, price) values (#{oid}, #{iid}, #{name}, #{count}, #{price})")
    void insertOrderItem(OrderItem orderItem);
    
    @Select("select * from `order` where uid=#{uid} and isDeleted = 0")
	List<Order> getOrderList(String uid);
    
    @Select("select * from orderItem where isDeleted = 0")
    List<OrderItem> getOrderItemList();
}
