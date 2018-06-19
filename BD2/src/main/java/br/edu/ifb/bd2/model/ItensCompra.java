package br.edu.ifb.bd2.model;

public class ItensCompra {
	private Livro livro;
	private Float preco_vendido;
	private Integer quantidade;
	private Compra compra;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Float getPreco_vendido() {
		return preco_vendido;
	}
	public void setPreco_vendido(Float preco_vendido) {
		this.preco_vendido = preco_vendido;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
