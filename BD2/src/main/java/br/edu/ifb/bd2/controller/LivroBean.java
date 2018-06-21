package br.edu.ifb.bd2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.ifb.bd2.model.Fornecedor;
import br.edu.ifb.bd2.model.Livro;
import br.edu.ifb.bd2.model.Prateleira;

@ManagedBean(name="livroControle", eager=true)
public class LivroBean {

	private String isbn;
	private String titulo;
	private String autor;
	private Float precoCusto;
	private Float precoVenda;
	private Integer estoqueMinimo;
	private Integer qtdEstoque;
	private String fornecedorCNPJ;
	private Integer prateleiraCodigo;
	private Map<String, String> fornecedores = new HashMap<String, String>();
	private Map<String, Integer> prateleiras = new HashMap<String, Integer>();
	
	@PostConstruct
	public void init() {
		//TODO adicionar os valores nos maps puxando os registros do banco
	}
	
	public void add() {
		Fornecedor f = new Fornecedor();
		f.setCnpj(fornecedorCNPJ);
		Prateleira p = new Prateleira();
		p.setCodigo_prateleira(prateleiraCodigo);
		Livro l = new Livro();
		l.setIsbn(isbn);
		l.setTitulo(titulo);
		l.setAutor(autor);
		l.setPrecoCusto(precoCusto);
		l.setPrecoVenda(precoVenda);
		l.setEstoqueMinimo(estoqueMinimo);
		l.setQtdEstoque(qtdEstoque);
		l.setFornecedor(f);
		l.setPrateleira(p);
		limpar();
	}
	
	private void limpar() {
		isbn = "";
		titulo = "";
		autor = "";
		precoCusto = 0f;
		precoVenda = 0f;
		estoqueMinimo = 0;
		qtdEstoque = 0;
		fornecedorCNPJ = "";
		prateleiraCodigo = 0;
	}
	
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

	public String getFornecedorCNPJ() {
		return fornecedorCNPJ;
	}

	public void setFornecedorCNPJ(String fornecedorCNPJ) {
		this.fornecedorCNPJ = fornecedorCNPJ;
	}

	public Integer getPrateleiraCodigo() {
		return prateleiraCodigo;
	}

	public void setPrateleiraCodigo(Integer prateleiraCodigo) {
		this.prateleiraCodigo = prateleiraCodigo;
	}

	public Map<String, String> getFornecedores() {
		return fornecedores;
	}

	public Map<String, Integer> getPrateleiras() {
		return prateleiras;
	}
	
}
