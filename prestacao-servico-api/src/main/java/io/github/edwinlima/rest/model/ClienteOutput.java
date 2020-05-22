package io.github.edwinlima.rest.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteOutput {
    private Integer id;
	
	private String nome;
	
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
}
