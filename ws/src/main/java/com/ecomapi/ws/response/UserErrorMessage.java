package com.ecomapi.ws.response;


import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum UserErrorMessage {
	MISSING_REQUIRED_FILED_USER("Vous avez ratté un champ obligatoire "),
	INTERNAL_SERVER_ERROR("LE SERVUER ECOMAPI IS DOWN "),
	NO_RECORD_FOUND("Utilisateur exist pas");
	
	private String errorMessage;

	private UserErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
