package br.edu.ifb.bd2.persistence;

public interface ICompraDAO extends IDAO{
	public String alteraPrecoVenda(String isbn, Float novoPreco);
	public String pagarCompra(Integer codigoCompra);
	public String cancelarCompra(Integer codigoCompra);
}
