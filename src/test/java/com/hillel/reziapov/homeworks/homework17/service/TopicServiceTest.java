package com.hillel.reziapov.homeworks.homework17.service;

import com.hillel.reziapov.homeworks.homework17.questionarium.Topic;
import com.hillel.reziapov.homeworks.homework17.repository.TopicRepository;
import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class TopicServiceTest extends TestCase {

    private final TopicRepository repository = new TopicMockRepository();
    private final TopicService service = new TopicService(repository);

    public void testGetById() {
        Topic dbTopic = service.getById(3);

        assertEquals(3, dbTopic.getId().intValue());
    }

    public void testSave() {
        // Initially repository has 5 topics
        assertEquals(5, service.getAll().size());
        Topic newTopic = Topic.builder().name("TEST").build();
        service.save(newTopic);
        assertEquals(6, service.getAll().size());
    }

    public void testUpdate() {
        Topic updatedTopic = Topic.builder().id(3).name("UPDATED").build();
        Topic dbTopic = service.getById(3);
        assertNotEquals(updatedTopic, dbTopic);

        service.update(updatedTopic);
        dbTopic = service.getById(3);
        assertEquals(updatedTopic, dbTopic);
    }

    public void testRemove() {
        // Initially repository has 5 topics
        assertEquals(5, service.getAll().size());
        service.remove(Topic.builder().id(3).build());
        assertEquals(4, service.getAll().size());

        Topic dbTopic = service.getById(3);
        assertNull(dbTopic);
    }
}