CREATE TABLE servico (
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(150) NOT NULL,
	valor VARCHAR(11) NOT NULL,
	id_cliente BIGINT NOT NULL,
	PRIMARY KEY(id)
);

ALTER TABLE servico ADD CONSTRAINT fk_servico_cliente
FOREIGN KEY (id_cliente) REFERENCES cliente (id);