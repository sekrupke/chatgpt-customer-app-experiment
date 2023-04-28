CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'password1234!ABC';

GRANT ALL PRIVILEGES ON customerDB.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

USE customerDB;

CREATE TABLE customers (
                           id INT PRIMARY KEY,
                           salutation VARCHAR(10),
                           title VARCHAR(150),
                           first_name VARCHAR(150),
                           last_name VARCHAR(150),
                           birth_date DATE,
                           street VARCHAR(150),
                           house_number VARCHAR(10),
                           postal_code VARCHAR(10),
                           city VARCHAR(150),
                           phone VARCHAR(20),
                           mobile VARCHAR(20),
                           fax VARCHAR(20),
                           email VARCHAR(150),
                           newsletter BOOLEAN
);