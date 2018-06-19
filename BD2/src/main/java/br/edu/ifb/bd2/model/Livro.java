package br.edu.ifb.bd2.model;

public class Livro {
	private String isbn;
	private String título;
	private String autor;
	private Float preco_custo;
	private Float preco_venda;
	private Integer estoque_minimo;
	private Integer qtd_estoque;
	private Fornecedor fornecedor;
	private Prateleira prateleira;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTítulo() {
		return título;
	}
	public void setTítulo(String título) {
		this.título = título;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Float getPreco_custo() {
		return preco_custo;
	}
	public void setPreco_custo(Float preco_custo) {
		this.preco_custo = preco_custo;
	}
	public Float getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(Float preco_venda) {
		this.preco_venda = preco_venda;
	}
	public Integer getEstoque_minimo() {
		return estoque_minimo;
	}
	public void setEstoque_minimo(Integer estoque_minimo) {
		this.estoque_minimo = estoque_minimo;
	}
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
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
