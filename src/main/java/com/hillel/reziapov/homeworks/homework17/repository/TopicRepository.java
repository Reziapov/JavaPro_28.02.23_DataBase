package com.hillel.reziapov.homeworks.homework17.repository;

import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;

public interface TopicRepository {

    boolean save(Topic topic);

    Topic get(int id);

    boolean remove(int id);

    int update(Topic topic);
}
