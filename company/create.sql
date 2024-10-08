create sequence Department_SEQ start with 1 increment by 50;
create sequence Employee_SEQ start with 1 increment by 50;
create table Department (id bigint not null, department_name varchar(40) not null unique, primary key (id));
create table Employee (id bigint not null, department_name varchar(40), name varchar(64) unique, position varchar(64), primary key (id));
alter table if exists Employee add constraint FKqgfkvrj2onvhlp28i12pcm86f foreign key (department_name) references Department (department_name);
INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'IT');
INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'CS');
INSERT INTO department(id, department_name) VALUES  (nextval('department_seq'), 'Software Engineering');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Neimat Soliman', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Haidy', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Tokka Ahmed', 'Software developer','Software Engineering');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Alaa Ahmed', 'Software developer','CS');
INSERT INTO employee(id, name, position, department_name) VALUES  (nextval('employee_seq'), 'Hany', 'Software developer','IT');
