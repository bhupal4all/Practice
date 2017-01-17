package com.ranga.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ranga.rest.data.Topic;

@Service
public class TopicService {
	private List<Topic> topicList = Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
			new Topic("hibernate", "Hibernate Framework", "Hibernate Framework Description"));
	
	public List<Topic> getAllTopics() {
		return topicList;
	}
}
