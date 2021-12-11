package com.customerapi.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.customerapi.base.TestBase;
import com.customerapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;

public class TC003_Add_Customer extends TestBase {
	String custid;
	String name;
	@Test
	void Create_Customer() {
		
		custid=RestUtils.Custemailid();
		name=RestUtils.CustName();
		RestAssured.baseURI = "https://api.stripe.com";
		// Basic Authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("sk_test_QbRiu7Hnvf9JMlTO1TmLtcoT");
		authscheme.setPassword("");
		RestAssured.authentication = authscheme;

		// Request Object
		httpRequest = RestAssured.given();
		
		
		// httpRequest.pathParam();
		 httpRequest.formParam("email",custid );
		
		httpRequest.formParam("name", name);
		 httpRequest.request(Method.POST, "/v1/customers");
		//Thread.sleep(3);
	}
	@Test
	void checkStatusCode()
	{
		logger.info("**************Checking Status Code************");
		//Status code Validation
		int statusCode=response.statusCode();
		
		Assert.assertEquals(statusCode, 200);
	}
	@Test
	void checkResponseTime()
	{
		logger.info("**************Checking Response Time************");
		long responseTime=response.getTime();
		
		Assert.assertTrue(responseTime<2000);
	}

	
}
