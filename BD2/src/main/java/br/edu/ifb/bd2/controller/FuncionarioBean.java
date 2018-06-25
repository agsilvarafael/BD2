package br.edu.ifb.bd2.controller;

import java.sql.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.edu.ifb.bd2.model.Endereco;
import br.edu.ifb.bd2.model.Funcionario;
import br.edu.ifb.bd2.persistence.ClienteDaoImp;
import br.edu.ifb.bd2.persistence.FuncionarioDaoImp;
import br.edu.ifb.bd2.persistence.IDAO;

@ManagedBean(name="funcionarioControle", eager=true)
public class FuncionarioBean {
	
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private Date dataNascimento;
	private Integer idEndereco;
	private Integer codigo;
	private Date dataContratacao;
	private String senha;
	private String cargo;
	private String situacao;
	private String cep;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private List<Funcionario> funcionarios;
	
	public void save() {
		Endereco e = new Endereco();
		e.setIdEndereco(idEndereco);
		
		Funcionario f = new Funcionario();
		f.setCpf(cpf);
		f.setNome(nome);
		f.setEmail(email);
		f.setTelefone(telefone);
		f.setEndereco(e);
		f.setDataNascimento(dataNascimento);
		f.setCodigo(codigo);
		f.setDataContratacao(dataContratacao);
		f.setSenha(senha);
		f.setCargo(cargo);
		f.setSituacao(situacao);//TODO colocar valor padr�o para esse
		
		IDAO dao = new ClienteDaoImp();
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Resultado",  dao.save(f)) );
	}
	public void update(RowEditEvent event) {
		IDAO dao = new FuncionarioDaoImp();
		FacesMessage msg = new FacesMessage("Resultado de editar livro", dao.update(event.getObject()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void dontUpdate(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicao cancelada para",
				"CPF "+((Funcionario)event.getObject()).getCpf()+"\n"+
				((Funcionario)event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
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

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
