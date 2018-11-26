--liquibase formatted sql

--changeset sample-docs:1

CREATE TABLE sample_user(
  id SERIAL PRIMARY KEY,
  login VARCHAR(25) NOT NULL,
  password VARCHAR(35) NOT NULL,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,

  UNIQUE (login)
);

INSERT INTO sample_user VALUES (2, 'ydabo', 'password_DABO', 'Yakhya','Dabo');
INSERT INTO sample_user VALUES (4, 'ldiop', 'password_DIOP','Lamine','Diop');
INSERT INTO sample_user VALUES (6, 'mfall', 'rKrpbSV8X34QHbBv','Makhta','Fall');

CREATE TABLE profile_type(
  id SERIAL PRIMARY KEY,
  name VARCHAR(10) NOT NULL,
  description VARCHAR(50) NOT NULL,

  UNIQUE (name)
);

INSERT INTO profile_type VALUES (1, 'ADMIN', 'ADMIN');
INSERT INTO profile_type VALUES (2, 'GUEST', 'GUEST');


CREATE TABLE user_profile_type(
  sample_user_id INT REFERENCES sample_user(id),
  user_profile_type_id INT REFERENCES profile_type(id),

  UNIQUE (sample_user_id, user_profile_type_id)
);

INSERT INTO user_profile_type VALUES (2,1);
INSERT INTO user_profile_type VALUES (4,2);
INSERT INTO user_profile_type VALUES (6,2);

--changeset sample-docs:2 dbms:postgresql

-- Initialising sequences to avoid collusion with dummy sample DATA

SELECT setval('sample_user_id_seq', 1000);
ALTER SEQUENCE sample_user_id_seq OWNED BY sample_user.id;

--changeset sample-docs:3

CREATE TABLE nationality(
  id SERIAL PRIMARY KEY,
  code VARCHAR(3) NOT NULL,
  name VARCHAR(20) NOT NULL,
  UNIQUE (code)
);

INSERT INTO nationality (code,name) VALUES ('US', 'United States');
INSERT INTO nationality (code,name) VALUES ('UK', 'United Kingdom');
INSERT INTO nationality (code,name) VALUES ('FRA', 'France');
INSERT INTO nationality (code,name) VALUES ('GER', 'Germany');

CREATE TABLE student(
  id SERIAL PRIMARY KEY,
  personal_number VARCHAR(20) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  date_of_birth DATE NOT NULL,
  nationality_id INT NOT NULL REFERENCES nationality(id),
  education VARCHAR(1) NOT NULL,
  scholarship_holder BOOLEAN DEFAULT 'F',
  UNIQUE (personal_number)
);

INSERT INTO student (personal_number,first_name,last_name,date_of_birth, nationality_id, education, scholarship_holder)
VALUES ('222BBB', 'Yakhya','Dabo','2000-12-31',1,'B','F');

INSERT INTO student (personal_number,first_name,last_name,date_of_birth, nationality_id, education, scholarship_holder)
VALUES ('333CCC', 'Max','Ndiaye','2001-11-21',2,'B','F');

INSERT INTO student (personal_number,first_name,last_name,date_of_birth, nationality_id, education, scholarship_holder)
VALUES ('444DDD', 'Sonko','Mohameth','2001-12-31',4,'P','F');

INSERT INTO student (personal_number,first_name,last_name,date_of_birth, nationality_id, education, scholarship_holder)
VALUES ('555EEE', 'SOW','Assane','2002-12-31',3,'B','F');

--changeset sample-docs:4 dbms:postgresql

-- Initialising sequences to avoid collusion with dummy sample DATA

SELECT setval('student_id_seq', 1000);
ALTER SEQUENCE student_id_seq OWNED BY student.id;