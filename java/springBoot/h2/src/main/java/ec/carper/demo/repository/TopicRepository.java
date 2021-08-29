package ec.carper.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ec.carper.demo.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Long> {}
