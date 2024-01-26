CREATE DATABASE livros;
USE livros;
-- Cria a tabela de livro.
CREATE TABLE livro (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  autor VARCHAR(100) NOT NULL,
  editora VARCHAR(255),
  data_publicacao DATE,
  PRIMARY KEY (id)
);