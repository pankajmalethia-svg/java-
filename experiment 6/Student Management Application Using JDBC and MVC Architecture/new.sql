CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    marks DOUBLE
);
