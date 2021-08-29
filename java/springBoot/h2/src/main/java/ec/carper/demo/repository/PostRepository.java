package ec.carper.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ec.carper.demo.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {}