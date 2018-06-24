package br.edu.ifb.bd2.model;

import java.io.Serializable;
import java.util.List;

public class Prateleira implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo_prateleira;
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
	public Integer getCodigo_prateleira() {
		return codigo_prateleira;
	}
	public void setCodigo_prateleira(Integer codigo_prateleira) {
		this.codigo_prateleira = codigo_prateleira;
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
