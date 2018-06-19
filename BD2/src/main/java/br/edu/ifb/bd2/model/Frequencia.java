package br.edu.ifb.bd2.model;

import java.sql.Date;
import java.sql.Time;

public class Frequencia {
	private Date data_freq;
	private Time hora_entrada;
	private Time hora_saida;
	private Funcionario funcionario;
	
	public Date getData_freq() {
		return data_freq;
	}
	public void setData_freq(Date data_freq) {
		this.data_freq = data_freq;
	}
	public Time getHora_entrada() {
		return hora_entrada;
	}
	public void setHora_entrada(Time hora_entrada) {
		this.hora_entrada = hora_entrada;
	}
	public Time getHora_saida() {
		return hora_saida;
	}
	public void setHora_saida(Time hora_saida) {
		this.hora_saida = hora_saida;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	
	
}
