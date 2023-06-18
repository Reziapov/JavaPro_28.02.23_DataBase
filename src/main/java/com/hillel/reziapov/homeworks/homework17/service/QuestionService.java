package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.questionarium.Question;
import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;
import com.hillel.reziapov.homeworks.homework17.repository.QuestionRepository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



public class QuestionService {

    private final QuestionRepository repository;
    private final Random rnd = ThreadLocalRandom.current();

    public QuestionService(QuestionRepository questionRepository) {
        this.repository = questionRepository;
    }

    public List<Question> getAll() {
        return repository.getAll();
    }

    public Question getRandom() {
        List<Question> allQuestions = repository.getAll();

        return getRandom(allQuestions);
    }

    public Question getRandomByTopic(Topic topic) {
        List<Question> topicQuestions = repository.getByTopicId(topic.getId());

        return getRandom(topicQuestions);
    }

    public Question save(Question question) {
        return repository.save(question);
    }

    public Question getById(int id) {
        return repository.getById(id);
    }

    public void remove(int id) {
        repository.remove(id);
    }

    public void update(Question question) {
        repository.update(question);
    }

    private Question getRandom(List<Question> questions) {
        if (questions.isEmpty()) {
            return null;
        }

        int randomIdx = rnd.nextInt(0, questions.size());

        return questions.get(randomIdx);
    }
}
