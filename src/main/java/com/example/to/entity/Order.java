package com.example.to.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder  
public class Order {
	private int oid;
	private String uid;
	private int tid;
	private int totalPrice;
	private LocalDateTime regDate;
	private String status;
}
