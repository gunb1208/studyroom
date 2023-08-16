package com.myproject.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private String buyerName; // 구매자이름
	private String fname; // 등록번호
	private int amount; // 납입액
	
}
