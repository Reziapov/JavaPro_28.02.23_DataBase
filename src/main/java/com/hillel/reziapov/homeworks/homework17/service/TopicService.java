package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;
import com.hillel.reziapov.homeworks.homework17.repository.TopicRepository;

import java.util.List;

public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic getById(int id) {
        return topicRepository.getById(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void update(Topic topic) {
        topicRepository.update(topic);
    }

    public void remove(Topic topic) {
        topicRepository.remove(topic);
    }

    public List<Topic> getAll() {
        return topicRepository.getAll();
    }


}
