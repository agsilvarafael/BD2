package br.edu.ifb.bd2.model;

import java.sql.Date;
import java.util.List;

public class Funcionario extends Pessoa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private Date dataContratacao;
	private String senha;
	private String cargo;
	private String situacao;
	private List<Frequencia> frequencias;
	private List<FaltasJustificadas> faltasJustificadas;
	private List<Prateleira> prateleiras;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getDataContratacao() {
		return dataContratacao;
	}
	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public List<Frequencia> getFrequencias() {
		return frequencias;
	}
	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}
	public List<FaltasJustificadas> getFaltasJustificadas() {
		return faltasJustificadas;
	}
	public void setFaltasJustificadas(List<FaltasJustificadas> faltasJustificadas) {
		this.faltasJustificadas = faltasJustificadas;
	}
	public List<Prateleira> getPrateleiras() {
		return prateleiras;
	}
	public void setPrateleiras(List<Prateleira> prateleiras) {
		this.prateleiras = prateleiras;
	}
	
}
