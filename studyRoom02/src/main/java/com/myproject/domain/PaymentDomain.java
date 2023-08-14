package com.myproject.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDomain {

	private int payNo; // 결제번호
	private int userNo; // 회원번호
	private int regNo; // 등록번호
	private Date payDate; // 결제날짜
	private int payment; // 납입액
	private String userName; //회원이름
	private String itemName; //상품이름
	
}
