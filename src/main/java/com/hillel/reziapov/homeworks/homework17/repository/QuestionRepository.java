package com.hillel.reziapov.homeworks.homework17.repository;

import com.hillel.reziapov.homeworks.homework17.questionarium.Question;

public interface QuestionRepository {

    boolean save(Question question);

    Question get(int id);

    boolean remove(int id);

    int update(Question question);
}
