package com.example.demo.oauth2;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
 
@RestController
public class OAuthController {
	
	@Autowired
	private OAuthService oAuthservice;
	
	@RequestMapping("/oauth2")
	public String getToken(){
		return oAuthservice.getToken(); 
	}	
	
	/*요청시 XML리런*/
	@RequestMapping("/prototypingcustomerscp")
	public Document prototypingcustomerscp() throws IOException, JSONException {
		
		/*전달 XML string*/
		final String msg = "<ns0:MCNS_0015_Customer_Request xmlns:ns0=\"http://schema.mcns.com/EPR/SAP/MCNS_0015_Customer\">\n" + 
				"    <I_KUNNR>string</I_KUNNR>\n" + 
				"    <I_NAME1>string</I_NAME1>\n" + 
				"</ns0:MCNS_0015_Customer_Request>";
		Document rtnDoc = convertStringToXMLDocument( msg );
		return rtnDoc;
	}
	
	@RequestMapping("/prototypingpsinforcvscp")
	public Document prototypingpsinforcvscp() throws IOException, JSONException {

        HttpClient httpclient = HttpClientBuilder.create().build();
		final String parameters = "?name=더미&group=더미2";
		HttpPost httppost = new HttpPost("https://sk-dev.it-cpi006-rt.cfapps.jp10.hana.ondemand.com/http/WS/PSInfoRcv"+parameters);

		httppost.setHeader(HTTP.CONTENT_TYPE, "application/xml; charset=UTF-8");

		/* oauth2 인증키 가져오기 
		 * Json 으로 변환 후 access_token 만 가져오기
		 * 형식 token {"access_token":"키 데이터"}
		 * */
		
		JSONObject jsonObj = new JSONObject(getToken());
		String apiKey = "Bearer "+ jsonObj.getString("access_token");
		httppost.setHeader("Authorization" ,apiKey);
 
		/*전달 XML string*/
		final String msg = "<urn:ZFM_IV_SEND_DOC_INFO xmlns:urn=\"urn:sap-com:document:sap:rfc:functions\">\n" + 
				"        <ET_HEADER/>\n" + 
				"    	<ET_ITEM/>\n" + 
				"    	<IV_DATUM/>\n" + 
				"    </urn:ZFM_IV_SEND_DOC_INFO>";
		httppost.setEntity(new StringEntity(msg));
 
		HttpResponse httpResponse = httpclient.execute(httppost);
		
        //응답 읽기
		HttpEntity entity = httpResponse.getEntity();
		//응답 문자열 받기
		String resXml = EntityUtils.toString(entity);
		//응답 문자열 xml 변환
		Document rtnDoc = convertStringToXMLDocument( resXml );
		// xml > string
		//String resSci = doc.getFirstChild().getNodeName();		
		return rtnDoc;
	}
	
	@RequestMapping("/httpSci")
	public Document httpPostSci() throws IOException, JSONException {

        HttpClient httpclient = HttpClientBuilder.create().build();
		final String parameters = "?name=더미&group=더미2";
		HttpPost httppost = new HttpPost("https://sk-dev.it-cpi006-rt.cfapps.jp10.hana.ondemand.com/http/skcmcns/Customer"+parameters);

		httppost.setHeader(HTTP.CONTENT_TYPE, "application/xml; charset=UTF-8");

		/* oauth2 인증키 가져오기 
		 * Json 으로 변환 후 access_token 만 가져오기
		 * 형식 token {"access_token":"키 데이터"}
		 * */
		
		JSONObject jsonObj = new JSONObject(getToken());
		String apiKey = "Bearer "+ jsonObj.getString("access_token");
		httppost.setHeader("Authorization" ,apiKey);
 
		/*전달 XML string*/
		final String msg = "<ns0:MCNS_0015_Customer_Request xmlns:ns0=\"http://schema.mcns.com/EPR/SAP/MCNS_0015_Customer\">\n" + 
				"    <I_KUNNR>string</I_KUNNR>\n" + 
				"    <I_NAME1>string</I_NAME1>\n" + 
				"</ns0:MCNS_0015_Customer_Request>";
		httppost.setEntity(new StringEntity(msg));
 
		HttpResponse httpResponse = httpclient.execute(httppost);
		
        //응답 읽기
		HttpEntity entity = httpResponse.getEntity();
		//응답 문자열 받기
		String resXml = EntityUtils.toString(entity);
		//응답 문자열 xml 변환
		Document rtnDoc = convertStringToXMLDocument( resXml );
		// xml > string
		//String resSci = doc.getFirstChild().getNodeName();		
		return rtnDoc;
	}
	
		
	
    private static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }

}
