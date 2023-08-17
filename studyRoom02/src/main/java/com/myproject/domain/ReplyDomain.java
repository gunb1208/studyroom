package com.myproject.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDomain {
	
	private Integer rno; //댓글번호
	private Integer bno; //글번호
	
	private String reply; //댓글 내용
	private String replyer; //댓글쓴이
	
	private Date regDate; //댓글 입력날짜
	
}
