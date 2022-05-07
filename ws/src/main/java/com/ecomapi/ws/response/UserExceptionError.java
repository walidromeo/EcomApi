package com.ecomapi.ws.response;

public enum UserExceptionError {

	MISSING_REQUIRED_FILED("Vous Avez Pas saisie un champx obligatoire"),
	Not_Connected_To_Server("Vous Avez Pas saisie un champx obligatoire");

	private String missingError;

	private UserExceptionError(String missingError) {
		this.missingError = missingError;
	}

	public String getMissingError() {
		return missingError;
	}

	public void setMissingError(String missingError) {
		this.missingError = missingError;
	}
	
}
