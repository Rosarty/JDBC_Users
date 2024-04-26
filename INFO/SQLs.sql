

CREATE DATABASE users;


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO users (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO users (name, email) VALUES ('Jane Doe', 'jane.doe@example.com');
INSERT INTO users (name, email) VALUES ('Jim Beam', 'jim.beam@example.com');
INSERT INTO users (name, email) VALUES ('Asya', 'asya@example.com');
