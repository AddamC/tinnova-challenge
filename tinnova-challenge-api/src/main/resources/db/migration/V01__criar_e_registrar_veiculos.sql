CREATE TABLE veiculo (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	marca VARCHAR(50),
	ano INTEGER,
	descricao VARCHAR(50),
	vendido BOOLEAN DEFAULT(FALSE),
	created DATE,
	updated DATE
);

INSERT INTO veiculo (nome, marca, ano, created, updated) values ('Etios Cross', 'Toyota', 2016, now(), now());
INSERT INTO veiculo (nome, marca, ano, created, updated) values ('Kadett GSi', 'Chevrolet', 1989, now(), now());
INSERT INTO veiculo (nome, marca, ano, created, updated) values ('Kicks', 'Nissan', 2020, now(), now());
INSERT INTO veiculo (nome, marca, ano, created, updated) values ('Up', 'Volkswagen', 2019, now(), now());
INSERT INTO veiculo (nome, marca, ano, created, updated) values ('Fiesta', 'Ford', 2001, now(), now());