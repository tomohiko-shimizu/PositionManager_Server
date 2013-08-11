CREATE TABLE Member(
  id LONG PRIMARY KEY  AUTO_INCREMENT,
  educationNumber  VARCHAR(256),
  name VARCHAR(256),
  password VARCHAR(256)
);

CREATE TABLE Position(
  x INTEGER,
  y INTEGER,
  room INTEGER,
  date TIMESTAMP,
  memebr_id LONG,
  FOREIGN KEY(memebr_id) REFERENCES Member(id)
);

        
