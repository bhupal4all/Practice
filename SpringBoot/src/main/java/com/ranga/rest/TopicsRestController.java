package com.ranga.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ranga.rest.data.Topic;
import com.ranga.service.TopicService;
import com.ranga.service.database.TopicRepository;

@RestController
public class TopicsRestController {

	@Autowired
	TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{topicId}")
	public Topic getTopicById(@PathVariable("topicId") String topicId) {
		return topicService.getTopicById(topicId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/topics/{topicId}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable("topicId")String topicId) {
		topicService.updateTopic(topic, topicId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/topics/{topicId}")
	public void remvoeTopic(@PathVariable("topicId")String topicId) {
		topicService.deleteTopic(topicId);
	}
	
}
