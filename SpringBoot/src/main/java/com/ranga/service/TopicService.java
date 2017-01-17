package com.ranga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ranga.rest.data.Topic;

@Service
public class TopicService {
	private List<Topic> topicList = new ArrayList<Topic>(Arrays.asList(
			new Topic("spring", "Spring Framework",
					"Spring Framework Description"), new Topic("hibernate",
					"Hibernate Framework", "Hibernate Framework Description")));

	public List<Topic> getAllTopics() {
		return topicList;
	}

	public Topic getTopicById(String topicId) {
		for (int idx = 0; idx < topicList.size(); idx++) {
			if (topicList.get(idx).getId().compareTo(topicId) == 0) {
				return topicList.get(idx);
			}
		}

		return null;
	}

	public boolean addTopic(Topic topic) {
		return topicList.add(topic);
	}
	
	public boolean updateTopic(Topic topic, String topicId) {
		for (int idx = 0; idx < topicList.size(); idx++) {
			if (topicList.get(idx).getId().compareTo(topicId) == 0) {
				topicList.set(idx, topic);
				return true;
			}
		}
		
		return false;
	}
}
