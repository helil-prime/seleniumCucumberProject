package api_tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.select.Evaluator.ContainsData;
import org.testng.annotations.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.support.FileReader;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

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
	public void postNewPet() throws IOException {
		 baseUrl = "https://petstore.swagger.io/v2/pet";
				 
				 requestBody = "{\"id\":22303,\"category\":{\"id\":6,\"name\":\"sheperd\"},\"name\":\"booboo\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":10029,\"name\":\"DC\"}],\"status\":\"available\"}";
				 
		    
		    response = given().contentType(ContentType.JSON).body(requestBody)
		    		.post(baseUrl);
		    
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
			
			System.out.println("Response body as String: " + response.getBody().asString());
			System.out.println("Response body as Pretty String: " + response.getBody().asPrettyString());

	}
	
	
	// New add ons 
	
	
	@Test
	public void postNewPetWithChainValidation() throws IOException {
		 baseUrl = "https://petstore.swagger.io/v2/pet";
		    
		 
		 requestBody = "{\"id\":22303,\"category\":{\"id\":6,\"name\":\"sheperd\"},\"name\":\"booboo\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":10029,\"name\":\"DC\"}],\"status\":\"available\"}";
		 
		   
		 given().contentType(ContentType.JSON).body(requestBody)
		    		.post(baseUrl)
		    		.then()
		    		.assertThat()
		    		.body("id", equalTo(22303))
		    		.and().body("category.id", equalTo(6))
		    		.and().body("name", is("booboo"))
		    		.and().body("tags[0].name", equalTo("DC"));
		    
//		    int scode = response.statusCode();
//			String contentType = response.getContentType();
//			System.out.println("content type :" + contentType);
//			System.out.println("status code :" + scode);
//			assertEquals(scode, 200);
//			assertEquals(contentType, "application/json");
//			
//			int id = response.path("id");
//			String petName = response.path("name");
//			String status = response.path("status");
//			assertEquals(id, 22303);
//			assertEquals(petName, "booboo");
//			assertEquals(status, "available");
//			
//			System.out.println("Response body as String: " + response.getBody().asString());
//			System.out.println("Response body as Pretty String: " + response.getBody().asPrettyString());
			
			//response.then().body("tags.name", equalTo("[DC]"));

	}
	
	
	@Test
	public void postNewPetWithFile() throws IOException {
		 baseUrl = "https://petstore.swagger.io/v2/pet";
				 
				 File postRequest = new File("./src/test/resources/jsonFiles/createPetRequest.json");
				 

				 String content = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsonFiles/createPetRequest.json")));
				 
				System.out.println(" file content: "+ content);
		    
		    response = given().contentType(ContentType.JSON).body(postRequest)
		    		.post(baseUrl);
		    
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
			
			System.out.println("Response body as String: " + response.getBody().asString());
			System.out.println("Response body as Pretty String: " + response.getBody().asPrettyString());
			
			//response.then().body("tags.name", equalTo("[DC]"));

	}
	
	
	@Test
	public void postNewPetWithFile2() throws IOException {
		 baseUrl = "https://petstore.swagger.io/v2/pet";
				 
				 File postRequest = new File("./src/test/resources/jsonFiles/createPetRequest2.json");
				 

				 String content = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsonFiles/createPetRequest2.json")));
				 
				System.out.println(" file content: "+ content);
		    
		    response = given().contentType(ContentType.JSON).body(postRequest)
		    		.post(baseUrl);
		    
		    response.getBody().prettyPrint();
		    
		    int scode = response.statusCode();
			String contentType = response.getContentType();
			System.out.println("content type :" + contentType);
			System.out.println("status code :" + scode);
			assertEquals(scode, 200);
			assertEquals(contentType, "application/json");
			
			int id = response.path("id");
			String petName = response.path("name");
			String status = response.path("status");
			assertEquals(id, 23333);
			assertEquals(petName, "booboo");
			assertEquals(status, "available");
			
			//System.out.println("Response body as String: " + response.getBody().asString());
			//System.out.println("Response body as Pretty String: " + response.getBody().asPrettyString());
			
			//response.then().body("tags.name", equalTo("[DC]"));

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
		 File postRequest = new File("./src/test/resources/jsonFiles/invalidAddPetRequestBody.json");
	    response = given().contentType(ContentType.JSON).body(postRequest)
	    		.post(baseUrl).andReturn();
	    
	    response.getBody().prettyPrint();
	    
	    int scode = response.statusCode();
		String contentType = response.getContentType();
		System.out.println("content type :" + contentType);
		System.out.println("status code :" + scode);
		assertEquals(scode, 500);
		assertEquals(contentType, "application/json");

	}
	
	
	// put mehods
	
	@Test
	public void putRequest() {
		
		baseUrl = "https://petstore.swagger.io/v2/pet";
		 File putRequest = new File("./src/test/resources/jsonFiles/putRequestBody.json");
	    response = given().contentType(ContentType.JSON).body(putRequest)
	    		.put(baseUrl).andReturn();
	    
	    response.getBody().prettyPrint();
	    
	    int scode = response.statusCode();
		String contentType = response.getContentType();
		System.out.println("content type :" + contentType);
		System.out.println("status code :" + scode);
		assertEquals(scode, 200);
		assertEquals(contentType, "application/json");

	}
	
	//delete method
	
	@Test
	public void deleteRequest() {
		int petId = 22303;
		baseUrl = "https://petstore.swagger.io/v2/pet/";
		
	    response = given().header("api_key", "newpet").accept("application/json")
	    		.when()
	    		.delete(baseUrl + petId).andReturn();
	    
	    response.getBody().prettyPrint();
	    
	    int scode = response.statusCode();
		String contentType = response.getContentType();
		System.out.println("content type :" + contentType);
		System.out.println("status code :" + scode);
		assertEquals(scode, 200);
		assertEquals(contentType, "application/json");
		
		assertEquals(response.getBody().path("message"), petId + "");

	}
	
	
}
