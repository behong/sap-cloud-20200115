package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Wcontroller {
	
	@ResponseBody
	@RequestMapping("/json")
    public Map<String, String> jsonReturnSample(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "홍성test");
        map.put("age", "40");
        return map;
        
     //   https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_OPLACCTGDOCITEMCUBE_SRV/A_OperationalAcctgDocItemCube
    }
	
	@RequestMapping("/")
	String welcome() {
		return "index.html";
	}

}
