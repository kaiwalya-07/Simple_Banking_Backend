CREATE TABLE IF NOT EXISTS customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    balance INT,
    nomineeAcc VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS loan (
   amount INT ,
   loan_ac_no VARCHAR(255) PRIMARY KEY,
   cust_ac_no VARCHAR(255),
   interest FLOAT ,
   duration INT,
   MinAmount INT
);

