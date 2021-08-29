package ec.carper.employeemanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author carper
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Table(name="EMPLOYEE")
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(nullable = false, updatable = false)
	private Long id;
	private String name;
	private String email;
    @Column(name="job_title")
	private String jobTitle;
	private String phone;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="employee_code", nullable = false, updatable = false)
	private String employeeCode;
}
