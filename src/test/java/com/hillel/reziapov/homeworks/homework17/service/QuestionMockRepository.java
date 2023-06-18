package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.exceptions.QuestionOperationException;
import com.hillel.reziapov.homeworks.homework17.questionarium.Question;
import com.hillel.reziapov.homeworks.homework17.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionMockRepository implements QuestionRepository {

    private int nextIdx = 7;
    private final List<Question> questions = new ArrayList<>(List.of(
            Question.builder().id(1).text("What is multithreading and why is it important in Java programming?").topic_id(1).build(),

            Question.builder().id(2).text("How to use JDBC (Java Database Connectivity) objects for interacting with databases?").topic_id(2).build(),
            Question.builder().id(3).text("How to connect to a database using Java?").topic_id(2).build(),

            Question.builder().id(4).text("What is the role and application of object-oriented programming in Java?").topic_id(3).build(),
            Question.builder().id(5).text("What is the structure of a basic Java program, and what are classes, methods, and variables?").topic_id(3).build(),
            Question.builder().id(6).text("What is Java and what are its key features?").topic_id(3).build()
    ));

    @Override
    public Question save(Question question) {
        question.setId(nextIdx++);
        questions.add(question);

        return question;
    }

    @Override
    public Question getById(int id) {
        for (Question question : questions) {
            if (question.getId() == id) {

                return question;
            }
        }

        return null;
    }

    @Override
    public void remove(int id) {
        var savedQuestionIdx = getQuestionIdx(id);

        questions.remove(savedQuestionIdx);
    }

    @Override
    public void update(Question question) {
        var savedQuestionIdx = getQuestionIdx(question.getId());

        questions.set(savedQuestionIdx, question);
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

    @Override
    public List<Question> getByTopicId(int topicId) {
        List<Question> result = new ArrayList<>();

        for (Question question : questions) {
            if (question.getTopic_id() == topicId) {
                result.add(question);
            }
        }

        return result;
    }

    private int getQuestionIdx(int questionId) {
        for (int i = 0; i < questions.size(); i++) {
            var topic = questions.get(i);
            if (topic.getId() == questionId) {

                return i;
            }
        }
        throw new QuestionOperationException("topic with id doesn't exist", null);
    }
}
