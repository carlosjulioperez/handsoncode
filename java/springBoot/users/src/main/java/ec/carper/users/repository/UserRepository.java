package ec.carper.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.carper.users.model.User;

/**
 * @Repository makes the interface a bean as @Component annotation.
 * Extending of JpaRepository we inherit the save, findAll, findById methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{}
