package com.example.demo.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddrController {
	
		@RequestMapping(method = RequestMethod.GET, value = "/xmlTest")
	    public Result<Address> httpGet( HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> map ) throws Exception {
	        
		    ParseService parseService = new XmlParseServiceImpl();
	        Response<Address> context = new Response<Address>();
	        return context.getResponse(parseService, map);
	        
	    }
}
