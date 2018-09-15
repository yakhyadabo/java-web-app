--liquibase formatted sql

--changeset sample-docs:1

CREATE TABLE sample_user(
  id SERIAL PRIMARY KEY,
  login VARCHAR(25) NOT NULL,
  password VARCHAR(35) NOT NULL,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL
);

INSERT INTO sample_user VALUES (2, 'ydabo', 'password_DABO', 'Yakhya','Dabo');
INSERT INTO sample_user VALUES (4, 'ldiop', 'password_DIOP','Lamine','Diop');
INSERT INTO sample_user VALUES (6, 'mfall', 'rKrpbSV8X34QHbBv','Makhta','Fall');

--changeset sample-docs:2 dbms:postgresql

-- Initialising sequences to avoid collusion with dummy sample DATA

SELECT setval('sample_user_id_seq', 10000);
ALTER SEQUENCE sample_user_id_seq OWNED BY sample_user.id;