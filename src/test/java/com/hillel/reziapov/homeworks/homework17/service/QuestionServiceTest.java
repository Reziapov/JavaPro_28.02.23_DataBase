package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.questionarium.Question;
import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;
import com.hillel.reziapov.homeworks.homework17.repository.QuestionRepository;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;


public class QuestionServiceTest extends TestCase {

    private final QuestionRepository repository = new QuestionMockRepository();
    private final QuestionService service = new QuestionService(repository);

    @Test
    public void testGetAll() {
        List<Question> questions = service.getAll();

        assertEquals(questions.size(), 6);
    }

    @Test
    public void testGetRandom() {
        Question randomQuestion = service.getRandom();

        assertNotNull(randomQuestion);
    }

    @Test
    public void testGetRandomByTopic() {
        // Repository has questions for topics 1, 2, 3
        Question randomQuestion = service.getRandomByTopic(Topic.builder().id(3).build());
        assertNotNull(randomQuestion);

        randomQuestion = service.getRandomByTopic(Topic.builder().id(4).build());
        assertNull(randomQuestion);
    }

    @Test
    public void testSave() {
        Question newQuestion = Question.builder().text("TEST TEXT").topic_id(4).build();

        // Initially mock repository contains 6 questions
        assertEquals(6, service.getAll().size());
        var savedQuestionId = service.save(newQuestion).getId();
        assertEquals(7, service.getAll().size());

        newQuestion.setId(savedQuestionId);
        Question dbQuestion = service.getById(savedQuestionId);

        assertEquals(newQuestion, dbQuestion);
    }

    @Test
    public void testGetById() {
        Question dbQuestion = service.getById(1);

        assertEquals(dbQuestion.getId().intValue(), 1);
    }

    @Test
    public void testRemove() {
        // Initially mock repository contains 6 questions
        assertEquals(6, service.getAll().size());
        service.remove(2);
        assertEquals(5, service.getAll().size());
        assertNull(service.getById(2));
    }

    @Test
    public void testUpdate() {
        Question updatedQuestion = Question.builder().id(3).text("UPDATED").topic_id(5).build();
        Question dbQuestion = service.getById(3);
        assertNotEquals(updatedQuestion, dbQuestion);

        service.update(updatedQuestion);
        dbQuestion = service.getById(3);
        assertEquals(updatedQuestion, dbQuestion);
    }
}