CREATE TABLE employees (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
age INTEGER,
address VARCHAR(255),
);
drop table employees;

INSERT INTO employees
(name, age, address) VALUES
('Anjali Sharma', 28, 'Delhi'),
('Rohit Verma', 34, 'Mumbai'),
('Priya Mehta', 26, 'Bangalore'),
('Amit Singh', 31, 'Hyderabad'),
('Neha Kapoor', 29, 'Chennai'),
('Siddharth Rao', 33, 'Pune'),
('Kavya Nair', 27, 'Kochi'),
('Rahul Jain', 30, 'Ahmedabad'),
('Sneha Reddy', 25, 'Bhubaneswar'),
('Karan Malhotra', 32, 'Chandigarh'),
('Ishita Desai', 24, 'Surat'),
('Aditya Narang', 35, 'Nagpur'),
('Divya Sinha', 28, 'Lucknow'),
('Manish Gupta', 29, 'Noida'),
('Riya Banerjee', 27, 'Kolkata'),
('Tushar Chawla', 31, 'Jaipur'),
('Megha Dutta', 26, 'Guwahati'),
('Varun Joshi', 33, 'Indore'),
('Tanvi Ahuja', 30, 'Bhopal'),
('Nikhil Roy', 32, 'Visakhapatnam');
ALTER TABLE employees
ADD COLUMN username VARCHAR(50),
ADD CONSTRAINT employee_userid_fk FOREIGN KEY (username) REFERENCES users(username);

-- Create the users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
-- Insert 20 user records
INSERT INTO users (username, password) VALUES
('user01', 'Pass@1234'),
('user02', 'Hello@5678'),
('user03', 'Turing#2024'),
('user04', 'Kane!7890'),
('user05', 'Code$1122'),
('user06', 'Java#4455'),
('user07', 'Logic@6677'),
('user08', 'Secure@8901'),
('user09', 'Math#3344'),
('user10', 'Dev!7788'),
('user11', 'Data@9988'),
('user12', 'Work#5566'),
('user13', 'Try@1020'),
('user14', 'Build!7789'),
('user15', 'Node@8080'),
('user16', 'React#4321'),
('user17', 'Analyz@2222'),
('user18', 'Learn@3434'),
('user19', 'Skill#6565'),
('user20', 'Task!9999');
alter table users
drop column role_id
ALTER TABLE users
ADD COLUMN role_id int default 2,
ADD CONSTRAINT users_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id);

alter table users
drop column role


select * from users;
select * from employees;
SELECT * FROM employee WHERE id = ?;  -- Replace ? with the actual employee ID you're testing.
CREATE TABLE roles (
id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
select * from roles;
INSERT INTO roles (name) VALUES
('ADMIN'),
('CLIENT')
UPDATE users SET role_id = 2 WHERE username IN ('user06', 'user11','user17');

UPDATE users
SET role_id = 1  -- 1 for ADMIN
WHERE username IN ('user06', 'user11', 'user17');
SELECT username, role_id FROM users
WHERE username IN ('user06', 'user11', 'user17');
update employees set username=null;
delete from users;

INSERT INTO users (username, password) values
('user01', 'Pass@1234')
select * from employees
select * from users
select * from employees where username is not null;
ALTER TABLE employees ADD COLUMN designation VARCHAR(100);
update employees set designation = 'EMPLOYEE';
ALTER TABLE employees ADD COLUMN dob DATE;
UPDATE employees SET dob = '1995-04-29'; -- Set same birthday for all
ALTER TABLE employees ADD COLUMN gender VARCHAR(10);
update employees set gender = 'MALE';

ALTER TABLE employees
ADD COLUMN search_vector tsvector
GENERATED ALWAYS AS (to_tsvector('english', name)) STORED;

CREATE INDEX idx_employees_search_vector
ON employees
USING GIN (search_vector);

SELECT id, name, search_vector FROM employees;

SELECT * FROM employees
WHERE search_vector @@ to_tsquery('Man:*');



