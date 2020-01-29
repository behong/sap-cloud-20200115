package com.example.demo.xml;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ch.qos.logback.classic.Logger;

public class XmlParseServiceImpl implements ParseService {
	
	private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(XmlParseServiceImpl.class);

	@SuppressWarnings("unused")
	private String httpGetRequest(List<NameValuePair> paramList) throws Exception{
		
		String responseStr = "";
		String url          = "";
		String svcKey      = "%2FtT6Wo1gAPsnvuE%2Bw6spb3DYg%2BhOuQ1HEnwCBeKkDhoeXxbo91wBM0nvSuhZBqjYr6UC1USvtN66utie9NiXBg%3D%3D";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		
		HttpGet    httpGet = null;
		HttpEntity entity  = null;
		
		URI uri = new URI("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd");

		uri = new URIBuilder(uri).addParameters(paramList).build();
		url = uri.toString() + "&ServiceKey=" + svcKey;

		httpGet     = new HttpGet(url);
		response    = httpclient.execute(httpGet);
		
		// 상태값 확인
		log.info("  httpGetRequest 상태값 확인 ",response.getStatusLine().toString());

		entity      = response.getEntity();
		responseStr = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		return responseStr;
	}
	
	
	private List<Address> parseXmlStr(String xmlStr){
		List<Address> addrList = new ArrayList<Address>();
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		// 상태값 확인
		log.info(" 상태값 확인 is == ",is.toString());				
		
		NodeList result = null;

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			// 상태값 확인
			log.info(" 상태값 확인 doc == ",doc.toString());
			
			Node node = doc.getDocumentElement();
			
			// 상태값 확인
			log.info(" 상태값 확인 node == ",node.toString());
			
			result = node.getChildNodes();
			
			// 상태값 확인
			log.info(" 상태값 확인 result == ",result.toString());
			
			for (int i = 0; i < result.getLength(); i++) {
				Node tmpNode = result.item(i);
				
				if (!(tmpNode.getNodeName().equals("cmmMsgHeader"))) {
					
					NodeList addrNodeList = tmpNode.getChildNodes();
					Address addr = new Address();
					
					Map<String, String> addrMap = new HashMap<>();
					for (int j = 0; j < addrNodeList.getLength(); j++) {
						addrMap.put(addrNodeList.item(j).getNodeName(), addrNodeList.item(j).getTextContent());
					}
					
					addr.setLnmAdres ( addrMap.get("lnmAdres"));
					addr.setZipNo    ( addrMap.get("zipNo")   );
					addr.setRnAdres  ( addrMap.get("rnAdres") );
					
					addrList.add(addr);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return addrList;
	}
	
	@Override
	public List<Address> getAddrList(Map<String, Object> map) throws Exception {
		String rtnStr = "";
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("searchSe"     , (String) map.get("searchSe")));
		paramList.add(new BasicNameValuePair("srchwrd"      , (String) map.get("srchwrd")));
		paramList.add(new BasicNameValuePair("countPerPage" , (String) map.get("countPerPage")));
		paramList.add(new BasicNameValuePair("currentPage"  , (String) map.get("currentPage")));
		rtnStr = this.httpGetRequest(paramList);
		return this.parseXmlStr(rtnStr);
	}
	
	@SuppressWarnings("unused")
	private String restTpl(List<NameValuePair> paramList) throws Exception{
		String responseStr = "";
		String url          = "";
		String svcKey      = "서비스키값";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		
		HttpGet    httpGet = null;
		HttpEntity entity  = null;
		
		URI uri = new URI("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd");

		uri = new URIBuilder(uri).addParameters(paramList).build();
		url = uri.toString() + "&ServiceKey=" + svcKey;

		httpGet     = new HttpGet(url);
		response    = httpclient.execute(httpGet);
		
		// 상태값 확인
		//log.info(response.getStatusLine().toString());

		entity      = response.getEntity();
		responseStr = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		return responseStr;
	}	

}
