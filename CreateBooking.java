package restassuredApiTests;

import org.testng.annotations.*;
import org.testng.Assert;


import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;
import java.util.Map;


public class CreateBooking {
	
	
Response response;	
public static HashMap map=new HashMap();
	
	@BeforeClass
	public void postdata()
	{
		map.put("firstname",RestUtils.getFirstName());
		map.put("lastname",RestUtils.getLastName());
		map.put("totalprice",RestUtils.getTotalPrice());
		map.put("depositpaid",RestUtils.getDepositPaid());
		map.put("additionalneeds",RestUtils.getAdditionalNeeds());
		
		
		 Map<String, String> bookingDates = new HashMap();   // Nested hashmap 
		 bookingDates.put("checkin",RestUtils.getCheckInDate());
		 bookingDates.put("checkout",RestUtils.getCheckOutDate());
		 map.put("bookingdates", bookingDates);
		
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/booking" ;
	}
	
	@Test 
	public void testpost()
	{
		
		Response response;	
		
		response = given() 
			.contentType("application/json")
			.accept("application/json")
			.body(map)
			.log().all()
			
		.when()
			.post()
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.extract().response();
	}
	
	@Test (priority = 1) 
	public void validateResponseData()
	{
			  String responsefirstname = response.path("firstname");
			  String requestfirstname = RestUtils.getFirstName();

			  Assert.assertEquals(responsefirstname, requestfirstname);  

			
			
	}

}
