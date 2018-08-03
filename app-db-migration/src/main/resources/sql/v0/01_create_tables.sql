--liquibase formatted sql

--changeset sample-docs:1

CREATE TABLE sample_user(
  id SERIAL PRIMARY KEY,
  login VARCHAR(25) NOT NULL,
  password VARCHAR(35) NOT NULL
);