package restassuredApiTests;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;


public class CreateToken {
	
	public static HashMap map=new HashMap();
	
	@BeforeClass
	public void postdata()
	{
		map.put("username",RestUtils.getUserName());
		map.put("password",RestUtils.getPassword());
		
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/auth" ;

	}

	@Test
	public void testpost()
	{
		given()
			.contentType("application/json")
			.body(map)
		
		.when()
			.post()
		
		.then()
			.statusCode(200)
			.and()
			.statusLine("HTTP/1.1 200 OK")
			.log().all();
	}
	
	

}
