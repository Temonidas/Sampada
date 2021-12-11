package com.customerapi.testcases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.customerapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;

public class TC001_Get_All_Customers extends TestBase {

	public String responsebody=null;
	@BeforeClass
	void Get_All_Customers() throws InterruptedException  {
		logger.info("**************Started TC001_Get_All_Customers*************");

		
		RestAssured.baseURI = "https://api.stripe.com";
		// Basic Authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("sk_test_QbRiu7Hnvf9JMlTO1TmLtcoT");
		authscheme.setPassword("");
		RestAssured.authentication = authscheme;

		// Request Object
		httpRequest = RestAssured.given();
		
		// Response Object
		response = httpRequest.request(Method.GET, "/v1/customers");
		Thread.sleep(3);
	}
	@Test
	void checkResponseBody()
	{
		logger.info("**************Checking Response Body************");
		responsebody=response.getBody().asString();
		logger.info("Response Body=>"+responsebody);
		Assert.assertTrue(responsebody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("**************Checking Status Code************");
		//Status code Validation
		int statusCode=response.statusCode();
		System.out.println(statusCode);
		logger.info("Status Code is:"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("**************Checking Response Time************");
		long responseTime=response.getTime();
		logger.info("Response Time is:"+responseTime);
		
		if(responseTime>2000)
		
			logger.warn("Response time is greater then 2000");
			Assert.assertTrue(responseTime>2000);
		
	
	}
	@Test
	void checkStatusLine() {
		logger.info("**************Checking Status Line************");
		String statusline=response.statusLine();
		logger.info("Status Line is:"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("**************Checking Content Type************");
		String contentType=response.header("Content-Type");
		logger.info("Content Type is:"+contentType);
		Assert.assertEquals(contentType,"application/json");
		
	}
	@Test
	void checkServerType()
	{
		logger.info("**************Checking Server Type************");
		String serverType=response.header("Server");
		logger.info("Server Type is:"+serverType);
		
	}
		@Test
		void checkContentLength()
		{
			logger.info("**************Checking Content Length************");
			String contentLength=response.header("Content-Length");
			logger.info("Content Length is:"+contentLength);
			
			if(Integer.parseInt(contentLength)<15000)
				logger.warn("Content Length is less then 15000");
			
			Assert.assertTrue(Integer.parseInt(contentLength)<15000);
		}
		@AfterClass
		void teardown()
		{
			logger.info("**************Finished TC001_Get_All_Customers*************");
		}

	}

