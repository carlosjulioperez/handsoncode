DROP TABLE IF EXISTS EMPLOYEE;
  
CREATE TABLE EMPLOYEE (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250),
  email VARCHAR(250),
  job_title VARCHAR(250),
  phone VARCHAR(250),
  image_url VARCHAR(250),
  employee_code VARCHAR(250) NOT NULL
);