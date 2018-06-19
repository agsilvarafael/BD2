package br.edu.ifb.bd2.model;

public class Fornecedor {
	private String cnpj;
	private String nome;
	private String email;
	private Endereco id_endereco;
	private String telefone;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(Endereco id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	} 

}
