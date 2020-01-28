package com.example.demo.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class OAuthClient {

	private String tokenEndpoint = "https://sk-dev.authentication.jp10.hana.ondemand.com/oauth/token";
	private String client_id = "sb-b576aacf-a876-4620-bfc7-5735740e83fe!b187|it-rt-sk-dev!b134";
	private String client_secret = "vOcDTjqduUB917vhz/I7O0IG/So=";
	private String grant_type = "client_credentials";
	//private String scope = "generate-ads-output";
	private String scope = "";

	public String callService() {

		/* HTTPCLIENT AND HTTPPOST OOBJECT */
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(tokenEndpoint);

		/* AUTHENTICATION CREDENTIALS ENCODING */
		String base64Credentials = Base64.getEncoder().encodeToString((client_id + ":" + client_secret).getBytes());

		/* HEADER INFO */
		httpPost.addHeader("Authorization", "Basic " + base64Credentials);
		//httpPost.addHeader("Authorization", "Basic " + " client_id=" + client_id + "&client_secret=" + client_secret);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

		/* PROXY CONFIG */
		//HttpHost target = new HttpHost("proxy", "", "http");
		//RequestConfig config = RequestConfig.custom().setProxy(target).build();
		//httpPost.setConfig(config);

		/* OAUTH PARAMETERS ADDED TO BODY */
		StringEntity input = null;
		try {
			input = new StringEntity("grant_type=" + grant_type + "&scope=" + scope);
			httpPost.setEntity(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/* SEND AND RETRIEVE RESPONSE */
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* RESPONSE AS STRING */
		String result = null;
		try {
			result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		OAuthClient oauthClient = new OAuthClient();
		String res = oauthClient.callService();
		System.out.println(res);
	}
}