CREATE DATABASE companydb;
USE companydb;

CREATE TABLE Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Salary DOUBLE
);

-- Insert sample data
INSERT INTO Employee (Name, Salary) VALUES
('Riya Sharma', 50000),
('Aman Gupta', 60000),
('Neha Patel', 55000);
