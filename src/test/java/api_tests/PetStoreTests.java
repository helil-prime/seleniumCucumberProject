package api_tests;

import static io.restassured.RestAssured.given;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreTests {

	RestAssured rest;
	String baseUrl;
	String path;
	Response response;
	// JsonPath jpath;

	String requestBody;

	/*
	 * Scenario: Find a pet by status available Given valid endpoint exist When I
	 * send a GET request by "available" to valid endpoint Then Response status code
	 * should be 200 And Content type should be "application/json"
	 */
	@Test
	public void findByAvailability() {

		baseUrl = "https://petstore.swagger.io/v2/pet/findByStatus";

		response = given().queryParam("status", "available").when().get(baseUrl);

		System.out.println("status code :" + response.getStatusCode());
		System.out.println("content type :" + response.getContentType());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getContentType(), "application/json");

	}
	
	
	/*
	 * Scenario: Find a pet by invalid url using status available 
	 * Given valid endpoint exist 
	 * When I send a GET request by "available" to valid endpoint 
	 * Then Response status code should be 404 
	 * And Content type should be "application/json"
	 */
	@Test
	public void findByWithInvalidUrl() {

		baseUrl = "https://petstore.swagger.io/v2/pet/findBy";

		response = given().queryParam("status", "available").when().get(baseUrl);

		System.out.println("status code :" + response.getStatusCode());
		System.out.println("content type :" + response.getContentType());
		assertEquals(response.getStatusCode(), 404);
		assertEquals(response.getContentType(), "application/json");

	}

	/*
	 * Scenario: As a user, I should be able to perform GET request to find a pet by id 
	 * Given I have the GET request URL When I perform GET request to URL by 27225 
	 * Then Response status code should be 200
	 * And content type should be "application/json"
	 * And pet id is 27225, pet name is "booboo"  status is "available"
	 */

	@Test
	public void findByID() {
		baseUrl = "https://petstore.swagger.io/v2/pet/6666";
		
		response = given().when().get(baseUrl).andReturn();
		
		int scode = response.statusCode();
		String contentType = response.getContentType();
		int id = response.path("id");
		String petName = response.path("name");
		String status = response.path("status");
		
		System.out.println("status code :" + scode);
		System.out.println("content type :" + contentType);
		System.out.println("id :" + id);
		System.out.println("pet Name :" + petName);
		System.out.println("status :" + status);
		assertEquals(scode, 200);
		assertEquals(contentType, "application/json");
		assertEquals(id, 6666);
		assertEquals(petName, "booboo");
		assertEquals(status, "available");

	}

	/*
	 * Scenario: As a user, I should be able to perform GET request to find a pet by id 
	 * Given I have the GET request URL When I perform GET request to URL with invalid id 279220 
	 * Then Response status code should be 404 
	 * And content type is application.json 
	 * And the message is "Pet not found"
	 */

	@Test
	public void findByInvalidID() {
		
       baseUrl = "https://petstore.swagger.io/v2/pet/66000";
		
		response = given().when().get(baseUrl).andReturn();
		
		int scode = response.statusCode();
		String contentType = response.getContentType();
		String message = response.path("message");
		System.out.println("status code :" + scode);
		System.out.println("content type :" + contentType);
		System.out.println("pet Name :" + message);
		
		assertEquals(scode, 200);
		assertEquals(contentType, "application/json");
		assertEquals(message, "Pet not found");

	}

	/*
	 * Scenario: As a user, I should be able to perform POST request to add new pet to store 
	 * Given I have the POST request URL and valid request body 
	 * When I perform POST request to URL with request body 
	 * Then Response status code should be 200 And content type is application.json 
	 * And response body match the request body
	 */

	@Test
	public void postNewPet() {
		 baseUrl = "https://petstore.swagger.io/v2/pet";
		    requestBody = "{\r\n"
		    		+ "  \"id\": 22303,\r\n"
		    		+ "  \"category\": {\r\n"
		    		+ "    \"id\": 6,\r\n"
		    		+ "    \"name\": \"sheperd\"\r\n"
		    		+ "  },\r\n"
		    		+ "  \"name\": \"booboo\",\r\n"
		    		+ "  \"photoUrls\": [\r\n"
		    		+ "    \"string\"\r\n"
		    		+ "  ],\r\n"
		    		+ "  \"tags\": [\r\n"
		    		+ "    {\r\n"
		    		+ "      \"id\": 10029,\r\n"
		    		+ "      \"name\": \"DC\"\r\n"
		    		+ "    }\r\n"
		    		+ "  ],\r\n"
		    		+ "  \"status\": \"available\"\r\n"
		    		+ "}";
		    
		    response = given().contentType(ContentType.JSON).body(requestBody)
		    		.post(baseUrl).andReturn();
		    
		    int scode = response.statusCode();
			String contentType = response.getContentType();
			System.out.println("content type :" + contentType);
			System.out.println("status code :" + scode);
			assertEquals(scode, 200);
			assertEquals(contentType, "application/json");
			
			int id = response.path("id");
			String petName = response.path("name");
			String status = response.path("status");
			assertEquals(id, 22303);
			assertEquals(petName, "booboo");
			assertEquals(status, "available");

	}

	/*
	 * Scenario: As a user, I should not be able to perform POST request to invalid data structure
	 * Given I have the POST request URL and invalid request body 
	 * When I perform POST request to URL with request body 
	 * Then Response status code should be 400 invalid input 
	 * And content type is application.json
	 * And message should be "bad input"
	 * 
	 */

	@Test
	public void postNewPetWithInvalidRequestBody() {
		
		baseUrl = "https://petstore.swagger.io/v2/pet";
	    requestBody = "{\r\n"
	    		+ "  \"i\" 22303,\r\n"
	    		+ "  \"category\": {\r\n"
	    		+ "    \"id\": 6,\r\n"
	    		+ "    \"name\": \"sheperd\"\r\n"
	    		+ "  },\r\n"
	    		+ "  \"name\": \"booboo\",\r\n"
	    		+ "  \"photoUrls\": [\r\n"
	    		+ "    \"string\"\r\n"
	    		+ "  ],\r\n"
	    		+ "  \"tags\": [\r\n"
	    		+ "    {\r\n"
	    		+ "      \"id\": 10029,\r\n"
	    		+ "      \"name\": \"DC\"\r\n"
	    		+ "    }\r\n"
	    		+ "  ],\r\n"
	    		+ "  \"status\": \"available\"\r\n"
	    		+ "}";
	    
	    response = given().contentType(ContentType.JSON).body(requestBody)
	    		.post(baseUrl).andReturn();
	    
	    response.getBody().prettyPrint();
	    
	    int scode = response.statusCode();
		String contentType = response.getContentType();
		System.out.println("content type :" + contentType);
		System.out.println("status code :" + scode);
		assertEquals(scode, 400);
		assertEquals(contentType, "application/json");
		
		

	}
}
