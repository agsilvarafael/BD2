package br.edu.ifb.bd2.model;

import java.sql.Date;

public class FaltasJustificadas {
	private Date data_falta;
	private String descricao;
	private Funcionario funcionario;
	
	public Date getData_falta() {
		return data_falta;
	}
	public void setData_falta(Date data_falta) {
		this.data_falta = data_falta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
