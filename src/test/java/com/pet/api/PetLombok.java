package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetLombok {
	
//	{
//	    "id": 200,
//	    "category": {
//	        "id": 1,
//	        "name": "Dog"
//	    },
//	    "name": "Ronney",
//	    "photoUrls": [
//	        "https://www.dog.com"
//	    ],
//	    "tags": [
//	        {
//	            "id": 10,
//	            "name": "red"
//	        }
//	    ],
//	    "status": "available"
//	}
	
	
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;
	

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Category{
		private Integer id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Tag{
		private Integer id;
		private String name;
	}

}
