package com.training.boothibernate;

import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
// import com.training.crmrest.entity.Customer;
import com.training.boothibernate.entity.Customer;

import static com.jayway.restassured.RestAssured.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestResourceTesting {

	@BeforeClass
	public static void setUp(){
		//1. port number
		//2. context-path
		//3. host name
		//eg: http://localhost:8080/mvn-crm-rest
		
		String port=System.getProperty("server.port");
		System.out.println(port);
		if(port==null)
			RestAssured.port= Integer.valueOf(8585);
		else
			RestAssured.port= Integer.valueOf(port);
		
		String context_path=System.getProperty("server.base");
		System.out.println(context_path);
		if(context_path==null)
			context_path="/boot-hibernate/";
		RestAssured.basePath=context_path;
		
		String host=System.getProperty("server.host");
		System.out.println(host);
		if(host==null)
			host="http://localhost";
		RestAssured.baseURI=host;
	}
	
	@Test
	public void testURI() {
		
		given().when().get("/api/customers").then().statusCode(200);
	}
	
	//test for a given id
		@Test
		public void invalidCustomerIdTest() {
			given().when().get("/api/customers/100").then().statusCode(404);
		}
		
		//test for contents
		@Test
		public void verifyCustomerDetails() {
			given().when().get("/api/customers/1").then().
				body("firstName",equalTo("First_Primary")).
				body("lastName",equalTo("Last_Primary"));
		}
		
		//test for adding new record (POST)
		@Test
		public void addCustomerTest() {
			//customer info can be initialized as Map
			Customer customer=new Customer();
			customer.setFirstName("CheckPro");
			customer.setLastName("NamePro");
			customer.setEmail("check-pro@mail.com");
			/*Map<String ,String> customer=new HashMap<>();
			customer.put("firstName", "First_Test");
			customer.put("lastName", "Last_Test");
			customer.put("email", "test@mail.com");*/
			
			given()
				.contentType("application/json")
				.body(customer)
				.when().post("/api/customers").then().statusCode(200);
			
		}
	
	
	
}
