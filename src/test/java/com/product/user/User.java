package com.product.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
//	{
//	    "name" : "sahil kumar",
//	    "email" : "{{$randomEmail}}",
//	    "gender" : "male",
//	    "status" : "active"
//	}
	
	private Integer id; 
	private String name;
	private String email;
	private String gender;
	private String status;
	
	
	public User(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	

}
