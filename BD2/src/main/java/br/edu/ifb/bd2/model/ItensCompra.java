package br.edu.ifb.bd2.model;

public class ItensCompra {
	private Livro livro;
	private Float precoVendido;
	private Integer quantidade;
	private Compra compra;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Float getPrecoVendido() {
		return precoVendido;
	}
	public void setPrecoVendido(Float precoVendido) {
		this.precoVendido = precoVendido;
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
