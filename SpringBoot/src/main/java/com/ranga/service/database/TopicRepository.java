package com.ranga.service.database;

import org.springframework.data.repository.CrudRepository;

import com.ranga.rest.data.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
