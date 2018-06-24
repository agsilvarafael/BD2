package br.edu.ifb.bd2.model;

import java.sql.Date;
import java.util.List;

public class Cliente extends Pessoa{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataCadastro;
	private List<Compra> compras;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
}
