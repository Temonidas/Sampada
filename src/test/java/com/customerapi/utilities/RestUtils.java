package com.customerapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	

public static String Custemailid()
{
	String generatedString=RandomStringUtils.randomAlphabetic(1);
	return("Preeti"+generatedString+"@gmail.com");
}
public static String CustName()
{
	String generatedString=RandomStringUtils.randomAlphabetic(1);
	return generatedString;
}
}
