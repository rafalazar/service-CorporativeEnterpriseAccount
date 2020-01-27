package com.rafalazar.bootcamp.app.dto;

import lombok.Data;

@Data
public class AccountDto {
	
	private String id;
	private String numberAccount;
	private String typeAccount;
	private Double amount;

}
