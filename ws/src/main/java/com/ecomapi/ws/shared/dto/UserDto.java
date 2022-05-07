package com.ecomapi.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7357120267566952944L;
	private long id;
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Boolean admin;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private Date first_time_join;

	private Date last_time_join;
	private String[] authorities;
	private boolean Enable;
	private boolean Locked;
	// kula user 3ndu 2 adresse kayjiw 3la chkel array de type Dto
	//private StockDto saveAddToStock;

}
