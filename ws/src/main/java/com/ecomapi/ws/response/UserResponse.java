package com.ecomapi.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
// f had user response ghadi Dek request li jatni mn exterieur ghadi nrj3ha sous forme de response
// b les données li ana baghi nrj3hum user walakin ghadi nzid wahd id bach n3ref chkun dek user
	private String userId;
	private String firstname;
	private String lastname;
	private String email; 
}
