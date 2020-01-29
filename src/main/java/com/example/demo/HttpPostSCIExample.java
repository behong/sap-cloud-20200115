package com.example.demo;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



public class HttpPostSCIExample {
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY_PERSON = "1";
    //private final DefaultHttpDestination destination = DestinationAccessor.getDestination("DEST_SCI").asHttp().decorate(DefaultHttpDestination::new);
    
	private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) Version/7.0.3 Safari/7046A194A";
 
	public String postMessage() throws IOException {
        //final HttpClient httpClient = new DefaultHttpClientFactory().createHttpClient(destination);
        HttpClient httpclient = HttpClientBuilder.create().build();
		final String parameters = "?name=tecbar&group=middleschool";
		HttpPost httppost = new HttpPost("https://sk-dev.it-cpi006-rt.cfapps.jp10.hana.ondemand.com/http/skcmcns/Customer"+parameters);
		httppost.setHeader(HTTP.USER_AGENT, USER_AGENT);
		httppost.setHeader(HTTP.CONTENT_TYPE, "application/xml; charset=UTF-8");
		httppost.setHeader("Authorization" ,"Bearer eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vc2stZGV2LmF1dGhlbnRpY2F0aW9uLmpwMTAuaGFuYS5vbmRlbWFuZC5jb20vdG9rZW5fa2V5cyIsImtpZCI6ImtleS1pZC0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiJhY2VhMWJhY2RkNzk0NzJlYjRmY2FjMmVhZDc5NTllZiIsImV4dF9hdHRyIjp7ImVuaGFuY2VyIjoiWFNVQUEiLCJ6ZG4iOiJzay1kZXYiLCJzZXJ2aWNlaW5zdGFuY2VpZCI6ImI1NzZhYWNmLWE4NzYtNDYyMC1iZmM3LTU3MzU3NDBlODNmZSJ9LCJzdWIiOiJzYi1iNTc2YWFjZi1hODc2LTQ2MjAtYmZjNy01NzM1NzQwZTgzZmUhYjE4N3xpdC1ydC1zay1kZXYhYjEzNCIsImF1dGhvcml0aWVzIjpbInVhYS5yZXNvdXJjZSIsIml0LXJ0LXNrLWRldiFiMTM0LkVTQk1lc3NhZ2luZy5zZW5kIl0sInNjb3BlIjpbInVhYS5yZXNvdXJjZSIsIml0LXJ0LXNrLWRldiFiMTM0LkVTQk1lc3NhZ2luZy5zZW5kIl0sImNsaWVudF9pZCI6InNiLWI1NzZhYWNmLWE4NzYtNDYyMC1iZmM3LTU3MzU3NDBlODNmZSFiMTg3fGl0LXJ0LXNrLWRldiFiMTM0IiwiY2lkIjoic2ItYjU3NmFhY2YtYTg3Ni00NjIwLWJmYzctNTczNTc0MGU4M2ZlIWIxODd8aXQtcnQtc2stZGV2IWIxMzQiLCJhenAiOiJzYi1iNTc2YWFjZi1hODc2LTQ2MjAtYmZjNy01NzM1NzQwZTgzZmUhYjE4N3xpdC1ydC1zay1kZXYhYjEzNCIsImdyYW50X3R5cGUiOiJjbGllbnRfY3JlZGVudGlhbHMiLCJyZXZfc2lnIjoiOTJlMmZlMjUiLCJpYXQiOjE1ODAyNzQ1MDYsImV4cCI6MTU4MDI3ODcwNiwiaXNzIjoiaHR0cDovL3NrLWRldi5sb2NhbGhvc3Q6ODA4MC91YWEvb2F1dGgvdG9rZW4iLCJ6aWQiOiJmYTM1ZmE1NC04N2QwLTQ1ZTQtYjk1My1iYTEyYjMyZTI0NjgiLCJhdWQiOlsidWFhIiwiaXQtcnQtc2stZGV2IWIxMzQuRVNCTWVzc2FnaW5nIiwic2ItYjU3NmFhY2YtYTg3Ni00NjIwLWJmYzctNTczNTc0MGU4M2ZlIWIxODd8aXQtcnQtc2stZGV2IWIxMzQiXX0.SnwYfFdFSpGUoMq87wWucK9B9AO5wz6yKjabFzUzEhuZ2wko67lZqU_02wtY3f9R3eWVitNPRgJMzR-nONmhjYlmczGEFsOUgx47KQz_dfQ6fV4OIUVWYh1vjXZiO7cycG53rzdVIExg8P9adyXE-ejHWuuw4YO408Jxy305VEmywUMe3jHbFgPvpRekMEN00UW32Ycweq_jeekkGZsVvG6PF8KyOGjGFyYHaHf4hzhXA2cXfnHk9KZl8vJowFskXDHIbSEwpd-vhGfTRKw-I2yLh710rSwEmbiP_Hiu3CIRFladnG8Enzi8tOfph7EGCw-6cW0l9ryDmTGfFYkfAXszMIycq3gIFxP6tBOetq6nxV2bPTt9Mu9t-8ZQyHDZl-tSbzk5MLNBXgVwLxsIBxfvbONEm-xEfjzGU0GC5MltEwbDQfXXzzfOHLOqzu40UImep8Mlj8rIplc3XTL6W8TVToMCsdQjh91DJCHtVqt6fi26AJB0YTQmOkg3GiOL2rhOEHPsnP0CE5M0mIgAppZ5EKgO5A_93Pld1Sou8Ew68pgC0DFuLKEVIdesQpoydIr870seNpYzUllvrNX3KPkb9rfU_PhLXMVpWbIDir8u07XAOW3oe03Szoyl1hUGDH_216Txi0COl9gc_gNZwKQIc6VnyOf_s4_3T97b7Mk");
 
		final String msg = "<ns0:MCNS_0015_Customer_Request xmlns:ns0=\"http://schema.mcns.com/EPR/SAP/MCNS_0015_Customer\">\n" + 
				"    <I_KUNNR>string</I_KUNNR>\n" + 
				"    <I_NAME1>string</I_NAME1>\n" + 
				"</ns0:MCNS_0015_Customer_Request>";
		httppost.setEntity(new StringEntity(msg));
 
		HttpResponse httpResponse = httpclient.execute(httppost);
		HttpEntity entity = httpResponse.getEntity();
		
		return EntityUtils.toString(entity);
	}
 
	public static void main(String[] args) {
		try {
			HttpPostSCIExample instance = new HttpPostSCIExample();
			String response = instance.postMessage();
			System.out.println(response);
		} catch (IOException ex) {
			// process the exception
		}
	}
 
}
