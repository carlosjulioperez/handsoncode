package ec.carper.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ec.carper.demo.dto.TopicDTO;
import ec.carper.demo.exception.EntityNotFoundException;
import ec.carper.demo.model.Topic;

public class TopicService {
	
    @PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
    public void updateTopic(long topicId, TopicDTO updated) {
        Topic topic = entityManager.find(Topic.class, topicId);
        if (topic != null) {
             topic.setName(updated.getName());
             entityManager.merge(topic);
        } else {
            throw new EntityNotFoundException(Topic.class, topicId);
        }
    }
}
