package rest_api_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import org.junit.Assert;

public class PetsStepDefs {
	
	RestAssured rest;
	String baseUrl;
	String path;
	Response response;
	JsonPath jpath;
	
	String requestBody;
	
	
	@Given("Find a pet by status GET request endpoint exists")
	public void get_request_endpoint_exists() {
	    baseUrl ="https://petstore.swagger.io/v2/pet/findByStatus";
	    
	}

	@When("I send a GET request by {string} to valid endpoint")
	public void i_send_a_get_request_by_to_valid_endpoint(String status) {
		response = given().queryParam("status", status)
	    .when().get(baseUrl).then().extract().response();
		
		
		jpath = response.jsonPath();
		System.out.println("status code :" + response.getStatusCode());
	    }
	

	@Then("Response status code should be {int}")
	public void response_status_code_should_be(Integer statuscode) {
		System.out.println("status code :" + response.getStatusCode());
		Assert.assertTrue(statuscode == response.getStatusCode());
		response.then().statusCode(statuscode);
	}

	@Then("Content type should be {string}")
	public void content_type_should_be(String string) {
		System.out.println("content Type: " + response.getContentType());
		response.then().contentType(string);
		System.out.println(jpath.get("name").toString());
				
	}
	
	// scenario 2 
	@Given("I have the GET request URL")
	public void i_have_the_get_request_url() {
		baseUrl = "https://petstore.swagger.io/v2/pet/";
	}
	@When("I perform GET request to URL by {int}")
	public void i_perform_get_request_to_url_by(Integer int1) {
	    response = given().when().get(baseUrl+int1)
	    .andReturn();
	    
	    System.out.println("status code :" + response.getStatusCode());
	    
	    jpath = response.jsonPath();
	    
	    System.out.println(jpath.get("category").toString());
	}
	
	
	// scenario 3
	
	@Given("I have the POST url and content body")
	public void i_have_the_post_url_and_content_body() {
	    baseUrl = "https://petstore.swagger.io/v2/pet";
	    requestBody = "{\r\n"
	    		+ "  \"id\": 22112,\r\n"
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
	}
	
	
	@When("I perform POST request to URL")
	public void i_perform_post_request_to_url() {
	    response = given().contentType(ContentType.JSON).body(requestBody)
	    		.post(baseUrl).andReturn();
	    
	    jpath = response.jsonPath(); 
	}
	
	
	@Then("response body should contain request body data")
	public void response_body_should_contain_request_body_data() {
		 System.out.println(jpath.get("id").toString());
		 System.out.println(jpath.get("category").toString());
		 System.out.println(jpath.get("name").toString());
		 System.out.println(jpath.get("photoUrls").toString());
         System.out.println(jpath.get("tags").toString());
         System.out.println(jpath.get("status").toString());
	}

}
