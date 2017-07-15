DROP TABLE users;
DROP TABLE groups;
DROP TABLE users_groups;

CREATE TABLE users (
  id   INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(16) UNIQUE
);

CREATE TABLE groups (
  id   INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) UNIQUE
);

CREATE TABLE users_groups (
  user_id  INTEGER REFERENCES users (id) ON DELETE CASCADE,
  group_id INTEGER REFERENCES groups (id) ON DELETE CASCADE
);

INSERT INTO users VALUES (default, 'ivanov');

INSERT INTO groups VALUES (default, 'teamA');
