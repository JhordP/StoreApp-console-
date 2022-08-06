
/* USE LINE BELOW JUST IF YOU WANT TO CREATE THE DATA BASE "TEST". 
   OTHERWISE MODIFY THE CONNECTION_STRING VAR ON data/DBConnection.java(line 6) CHANGING "/test?" for "/yourDB?"
/*CREATE DATABASE test;*/ 

USE test;
CREATE TABLE item(
item_id INT NOT NULL auto_increment,
item_name VARCHAR(40),
item_price DOUBLE,
PRIMARY KEY(item_id)
);

INSERT INTO item(item_name, item_price) VALUES ("product", 0.01);
SELECT * FROM item;
/*DROP TABLE item;*/