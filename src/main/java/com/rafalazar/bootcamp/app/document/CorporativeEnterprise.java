package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="corporative_enterprise")
public class CorporativeEnterprise {
	
	@Id
	private String id;
	private String ruc;
	private String numAccount;
	private String razonSocial;
	private String address;
	private Double amount;
	private Date createAt;

}
