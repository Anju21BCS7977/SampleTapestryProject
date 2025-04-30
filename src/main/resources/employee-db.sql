CREATE TABLE employees (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
age INTEGER,
address VARCHAR(255)
);

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

-- Create the users table
CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);


ALTER TABLE employees
    ADD COLUMN username VARCHAR(50),
        ADD CONSTRAINT employee_userid_fk FOREIGN KEY (username) REFERENCES users(username);


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

CREATE TABLE roles (
id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES
('ADMIN'),
('CLIENT');

ALTER TABLE users
    ADD COLUMN role_id int default 2,
        ADD CONSTRAINT users_role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id);
        ALTER TABLE employees ADD COLUMN designation VARCHAR(100);
        update employees set designation = 'EMPLOYEE';


