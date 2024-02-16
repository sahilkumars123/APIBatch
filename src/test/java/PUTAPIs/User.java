package PUTAPIs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//{
//  "name" : "sahil kumar",
//  "email" : "sahil126456022657@gmail.com",
//  "gender" : "male",
//  "status" : "active"
//}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	private String name;
	private String email;
	private String gender;
	private String status;
	

}

