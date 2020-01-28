package com.example.demo.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicservice;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics(){
		System.out.println("topics start");
		return topicservice.getAllTopics(); 
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicservice.getTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicservice.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic topic,@PathVariable String id) {
		topicservice.updateTopic(id,topic);
	}	

	@RequestMapping("/topicsTest2")
	public String Test2() {
		System.out.println("TEST2");
		return "test2";
	}
}
