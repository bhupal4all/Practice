package com.ranga.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranga.rest.data.Topic;
import com.ranga.service.TopicService;

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
}
