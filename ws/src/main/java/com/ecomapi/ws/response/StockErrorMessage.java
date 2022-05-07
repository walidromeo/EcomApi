package com.ecomapi.ws.response;


import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum StockErrorMessage {
	
	MISSING_REQUIRED_FILED("Vous avez pas rempli tous les informations naicessaire. "),
	INTERNAL_SERVER_ERROR("LE SERVUER ECOMAPI IS DOWN"),
	NO_RECORD_FOUND("Ce produit exist pas");
	
	private String errorMessage;

	private StockErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
