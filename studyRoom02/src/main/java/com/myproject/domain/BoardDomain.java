package com.myproject.domain;

import java.util.Date;

import lombok.Data;


@Data
public class BoardDomain {
	// 글번호, 제목, 작성자, 내용
	private Integer bno;
	private String title;
	private String writer;
	private String content;

	// 카테고리, 댓글 수, 비밀글 여부
	private int category;
	private int replyCnt;
	private boolean secret;

	// 부모글 번호 : 원글은 0, 답글은 부모글 번호
	private Integer parentNo;  

	// 글 그룹번호
	private Integer groupNo;

	// 조회수, 작성일
	private int hitCount;
	private Date regDate;
	
	
}
