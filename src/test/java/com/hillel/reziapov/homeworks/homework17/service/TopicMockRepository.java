package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.exceptions.TopicOperationException;
import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;
import com.hillel.reziapov.homeworks.homework17.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;

public class TopicMockRepository implements TopicRepository {
    private int nextIndex = 6;

    public List<Topic> topics = new ArrayList<>(List.of(
            Topic.builder().id(1).name("MAP").build(),
            Topic.builder().id(2).name("OOP").build(),
            Topic.builder().id(3).name("Collections").build(),
            Topic.builder().id(4).name("ArrayList").build(),
            Topic.builder().id(5).name("SQL").build()
    ));

    @Override
    public Topic save(Topic topic) {
        topic.setId(nextIndex++);
        topics.add(topic);

        return topic;
    }

    @Override
    public Topic getById(int id) {
        for (Topic topic : topics) {
            if (topic.getId() == id) return topic;
        }

        return null;
    }

    @Override
    public void remove(Topic topic) {
        var topicToRemove = getTopicIdx(topic.getId());

        topics.remove(topicToRemove);
    }

    @Override
    public void update(Topic topic) {
        var savedTopicIdx = getTopicIdx(topic.getId());
        topics.set(savedTopicIdx, topic);
    }

    @Override
    public List<Topic> getAll() {
        return topics;
    }

    private int getTopicIdx(int topicIdx) {
        for (int i = 0; i < topics.size(); i++) {
            var topic = topics.get(i);
            if (topic.getId() == topicIdx) {

                return i;
            }
        }
        throw new TopicOperationException("topic with id doesn't exist", null);
    }
}
