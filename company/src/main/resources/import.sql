

INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'IT');
INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'CS');
INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'Software Engineering');

INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Neimat Soliman', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Haidy', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Tokka Ahmed', 'Software developer','Software Engineering');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Alaa Ahmed', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Hany', 'Software developer','IT');

