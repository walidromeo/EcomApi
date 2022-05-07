package com.ecomapi.ws.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	@NotBlank(message = "Ce Champ Doit pas etre vide !!")
	@Size(min = 3, message = "Ce Champo doit avaoir au mois 3 character !!")
	private String firstname;

	@NotBlank(message = "Ce Champ Doit pas etre vide !!")
	@Size(min = 3, message = "Ce Champo doit avair au mois 3 character !!")
	private String lastname;

	@NotBlank(message = "Ce Champ Doit pas etre vide !!")
	@Email(message = "Ce Champ doit resprecter une format Email !!")
	private String email;
	private Boolean admin;

	@NotBlank(message = "Ce Champ Doit pas etre vide !!")
	@Size(min = 7, max = 12, message = "Ce Champ doit etre entre 7 et 12 character !!")
	private String password;

}
