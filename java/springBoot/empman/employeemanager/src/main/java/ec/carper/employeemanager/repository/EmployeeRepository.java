package ec.carper.employeemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.carper.employeemanager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	void deleteEmployeeById(Long id);

	Optional<Employee> findEmployeeById(Long id);

}
