package com.ranga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranga.rest.data.Topic;
import com.ranga.service.database.TopicRepository;

@Service
public class TopicService {
//	private List<Topic> topicList = new ArrayList<Topic>(Arrays.asList(
//			new Topic("spring", "Spring Framework",
//					"Spring Framework Description"), new Topic("hibernate",
//					"Hibernate Framework", "Hibernate Framework Description")));

	@Autowired
	TopicRepository topicRepository;
	
	public List<Topic> getAllTopics() {
		List<Topic> list = new ArrayList<Topic>();
		
		Iterator<Topic> topicItr = topicRepository.findAll().iterator();
		while(topicItr.hasNext()){
			list.add(topicItr.next());
		}
		
		return list;
	}

	public Topic getTopicById(String topicId) {
		return topicRepository.findOne(topicId);
	}

	public boolean addTopic(Topic topic) {
		topicRepository.save(topic);
		return true;
	}
	
	public boolean updateTopic(Topic topic, String topicId) {
		topicRepository.save(topic);
		return true;
	}
	
	public boolean deleteTopic(String topicId) {
		topicRepository.delete(topicId);
		return true;
	}
}
