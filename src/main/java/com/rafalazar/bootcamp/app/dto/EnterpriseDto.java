package com.rafalazar.bootcamp.app.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EnterpriseDto {
	
	private String id;
	private String ruc;
	private String socialName;
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
	public AccountDto account;

}
