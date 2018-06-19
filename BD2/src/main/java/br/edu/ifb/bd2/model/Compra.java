package br.edu.ifb.bd2.model;

import java.sql.Date;
import java.util.List;

public class Compra {
	private Integer codigo;
	private Date data_compra;
	private String situacao;
	private Date data_cancelamento;
	private Cliente cliente;
	private List<ItensCompra> itens;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getData_compra() {
		return data_compra;
	}
	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Date getData_cancelamento() {
		return data_cancelamento;
	}
	public void setData_cancelamento(Date data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}
	public List<ItensCompra> getItens() {
		return itens;
	}
	public void setItens(List<ItensCompra> itens) {
		this.itens = itens;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
