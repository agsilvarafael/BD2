package br.edu.ifb.bd2.model;

import java.io.Serializable;

public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String titulo;
	private String autor;
	private Float precoCusto;
	private Float precoVenda;
	private Integer estoqueMinimo;
	private Integer qtdEstoque;
	private Fornecedor fornecedor;
	private Prateleira prateleira;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Float getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(Float precoCusto) {
		this.precoCusto = precoCusto;
	}
	public Float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Prateleira getPrateleira() {
		return prateleira;
	}
	public void setPrateleira(Prateleira prateleira) {
		this.prateleira = prateleira;
	}
}
