package ec.carper.oms.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.carper.oms.data.model.Product;

/**
 * @Repository makes the interface a bean as @Component annotation.
 * Extending of JpaRepository we inherit the save, findAll, findById methods.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
