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
        map.put("name", "111");
        map.put("age", "2222");
        return map;
    }
	
	@RequestMapping("/welcome")
	String welcome() {
		return "welcome";
	}

}
