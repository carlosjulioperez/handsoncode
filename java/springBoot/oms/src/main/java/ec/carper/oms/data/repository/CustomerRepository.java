package ec.carper.oms.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.carper.oms.data.model.Customer;

/**
 * @Repository makes the interface a bean as @Component annotation.
 * Extending of JpaRepository we inherit the save, findAll, findById methods.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
