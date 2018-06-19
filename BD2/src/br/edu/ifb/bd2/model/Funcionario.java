package br.edu.ifb.bd2.model;

import java.util.Date;
import java.util.List;

public class Funcionario extends Pessoa {
	private Integer codigo;
	private Date data_contratacao;
	private String senha;
	private String cargo;
	private String situacao;
	private List<Frequencia> frequencias;
	private List<Faltas_justificadas> faltas_justificadas;
	private List<Prateleira> prateleiras;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getData_contratacao() {
		return data_contratacao;
	}
	public void setData_contratacao(Date data_contratacao) {
		this.data_contratacao = data_contratacao;
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
	public List<Faltas_justificadas> getFaltas_justificadas() {
		return faltas_justificadas;
	}
	public void setFaltas_justificadas(List<Faltas_justificadas> faltas_justificadas) {
		this.faltas_justificadas = faltas_justificadas;
	}
	public List<Prateleira> getPrateleiras() {
		return prateleiras;
	}
	public void setPrateleiras(List<Prateleira> prateleiras) {
		this.prateleiras = prateleiras;
	}
	
}
