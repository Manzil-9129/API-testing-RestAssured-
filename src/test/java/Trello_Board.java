import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;

//import io.restassured.http.Method;
import org.hamcrest.Matchers;

import io.restassured.http.Method;
//import io.restassured.RestAssured;
import io.restassured.response.Response;


//import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;


public class Trello_Board {

	

//Post a new board
	@Test(priority=1,groups="Post")
	public void post(ITestContext context) {
		
		HashMap<String,String> data=new HashMap<String,String>();
		data.put("key", Util.Key_Trello);
		data.put("token",Util.Token_Trello);
		data.put("name",Util.Board());
		
		Response response=given().baseUri(Util.URL).queryParams(data).header("Content-Type","application/json")
				.request(Method.POST,"1/boards");
		
	    String body= response.getBody().asString();
	    int Status_code=response.getStatusCode();
	    String board_ID=response.jsonPath().getString("id");
	    String Name=response.jsonPath().getString("name");
	    
	    
	    //set the id of the board as a value to the varible 
	    context.setAttribute("board_id", board_ID);
	    
	    System.out.println(body);
	    
		
		//Validation
		Assert.assertEquals(Status_code,200);
		Assert.assertEquals(Name ,Util.board_name_generated);
	}
	
	
	//Put Request
	@Test(priority=2,groups="put",dependsOnMethods="post")
	public void put(ITestContext context) {
		
		HashMap<String,String> data=new HashMap<String,String>();
		data.put("key", Util.Key_Trello);
		data.put("token",Util.Token_Trello);
		data.put("name",Util.Board());
		
		//Setting the value of ID equal to Board ID
        String Id= (String) context.getAttribute("board_id");
		
        
		Response response=given().baseUri(Util.URL).queryParams(data).pathParam("id",Id)
				.request(Method.PUT,"1/boards/{id}");
		
		
		//Getting the response 
		int status_code=response.getStatusCode();
		String name_check=response.jsonPath().getString("name");
		
		
	  //Validations
		Assert.assertEquals(status_code,200);
		Assert.assertEquals(name_check,Util.board_name_generated);
	}
	
	
	//Get Request 
   @Test(priority=3,groups="get",dependsOnMethods="put")
	public void get(ITestContext context) {
		
	   HashMap<String,String> data1=new HashMap<String,String>();
		data1.put("key", Util.Key_Trello);
		data1.put("token",Util.Token_Trello);
		

		//Setting the value of ID equal to Board ID
        String Id_value= (String) context.getAttribute("board_id");
		
		Response response=given().baseUri(Util.URL).queryParams(data1).pathParam("id",Id_value)
				.request(Method.GET,"1/boards/{id}");	
		
		int status_code=response.getStatusCode();
		String id_check=response.jsonPath().getString("id");
		
		
		
		Assert.assertEquals(status_code,200);
		
	}
	
   //Delete Request
	@Test(priority=4,groups="delete",dependsOnMethods="get")
	public void delete(ITestContext context) {
		
		HashMap<String,String> data2=new HashMap<String,String>();
		data2.put("key", Util.Key_Trello);
		data2.put("token",Util.Token_Trello);
		

		//Setting the value of ID equal to Board ID
        String Id_value= (String) context.getAttribute("board_id");
		
	
		Response response=given().baseUri(Util.URL).queryParams(data2).pathParam("id",Id_value)
		.request(Method.DELETE,"1/boards/{id}");
		
		int Status_code=response.getStatusCode();
		String id_check=response.jsonPath().getString("id");
		
		
		
		Assert.assertEquals(Status_code, 200);
		
	}
	
	
	
	
	
	
	
	
	
}
