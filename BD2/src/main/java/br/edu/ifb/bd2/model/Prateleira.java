package br.edu.ifb.bd2.model;

import java.io.Serializable;
import java.util.List;

public class Prateleira implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigoPrateleira;
	private String localizacao;
	private String descricao;
	private List<Livro> Livros;
	private Funcionario funcionario;
	
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCodigoPrateleira() {
		return codigoPrateleira;
	}
	public void setCodigoPrateleira(Integer codigoPrateleira) {
		this.codigoPrateleira = codigoPrateleira;
	}
	public List<Livro> getLivros() {
		return Livros;
	}
	public void setLivros(List<Livro> livros) {
		Livros = livros;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
