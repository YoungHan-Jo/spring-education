package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private int num;
	private String memberId;
	private String subject;
	private String content;
	private int viewCount;
	private Date regDate;
	private int reRef;
	private int reLev;
	private int reSeq;

}
