package br.edu.ifb.bd2.model;

import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa{
	private Date data_cadastro;
	private List<Compra> compras;

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
}
