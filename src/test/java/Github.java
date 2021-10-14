import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Method;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Github { 

     
	
 
	@Test(priority=3,dependsOnMethods="patch_request")
	public void Getrequest() {
		
	//Specify the base URI 
	RestAssured.baseURI="https://api.github.com/repos";
	
	//Request Object		
	RequestSpecification http= RestAssured.given();
   
	
	//Response Object
	Response response=http.request(Method.GET,"/Manzil-9129/"+Util.github_patch);
	
	
	
	//Convert Json Fromat to String Format
	String responsebody1=response.getBody().asString();
	int status=response.getStatusCode();
	String body_return=response.jsonPath().getString("name");
	
	System.out.println(responsebody1);
	
	System.out.println(status);
	Assert.assertEquals(status,200);
	Assert.assertEquals(body_return,Util.github_patch);
	
	}
	
	
	@Test(priority=1,groups="data")
	public void post() {
		JSONObject body=new JSONObject();
		
		body.put("name",Util.Board());
			
		Response response=given().baseUri("https://api.github.com/user").auth().preemptive()
		.basic("Manzil-9129","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")
		.header("Content-Type","application/json").body(body.toJSONString()).when()
		.request(Method.POST,"/repos");
		
		
	
	    String responsebody=response.getBody().asString();
		int Status=response.getStatusCode();
		String bodyvalidate=response.jsonPath().get("name");
		
		
		System.out.println(bodyvalidate);
		System.out.println(responsebody);
		System.out.println(Status);
		
		Assert.assertEquals(Status,201);
		Assert.assertEquals(bodyvalidate,Util.board_name_generated);
		
		
		}
	

	
	@Test(priority=2,groups="Patch")
	public void patch_request() {
		
		//Adds data using Map interface map<String,JSONvalue>
		JSONObject object1=new JSONObject();
		
		object1.put("name",Util.github());
		
		
		Response response=given().baseUri("https://api.github.com/repos").auth().preemptive()
		.basic("Manzil-9129","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")
		.header("Content-Type","Application/Json").body(object1.toJSONString()).when()
		.request(Method.PATCH,"/Manzil-9129/"+Util.board_name_generated);
		
		String response_body=response.getBody().asString();
		String response_validate=response.jsonPath().getString("name");
		int response_statuscode=response.getStatusCode();
		String response_header_validate=response.getHeader("Server");
		
		
		System.out.println(response_body);
		System.out.println(response_statuscode);
		System.out.println(response_header_validate);
		
		//Validation Part
		Assert.assertEquals(response_validate,Util.github_patch);
		Assert.assertEquals(response_statuscode,200);
		Assert.assertEquals(response_header_validate,"GitHub.com");
		
		
	}
	
	
	
	


	@Test(priority=4,groups="delete")
	public void delete() {
		
		
		Response response=given().baseUri("https://api.github.com/repos")
				.auth().preemptive().basic("Manzil-9129","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")
				.request(Method.DELETE,"/Manzil-9129/"+Util.github_patch);
				
		 
		String response_body=response.getBody().asPrettyString();
		int response_Status=response.getStatusCode();
		
		System.out.println(response_Status);
		
		Assert.assertEquals(response_Status,204);
		
		
	}
	
	
	
}
	
	

