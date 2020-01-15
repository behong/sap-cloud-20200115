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
        map.put("name", "È«¼º");
        map.put("age", "40");
        return map;
    }
	
	@RequestMapping("/welcome")
	String welcome() {
		return "welcome";
	}

}
