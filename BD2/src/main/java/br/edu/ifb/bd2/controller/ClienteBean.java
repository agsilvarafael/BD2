package br.edu.ifb.bd2.controller;

import java.sql.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.edu.ifb.bd2.model.Cliente;
import br.edu.ifb.bd2.model.Endereco;
import br.edu.ifb.bd2.persistence.ClienteDaoImp;
import br.edu.ifb.bd2.persistence.IDAO;

@ManagedBean(name="clienteControle", eager=true)
public class ClienteBean {

	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private Date dataNascimento;
	private Integer idEndereco;
	private Date dataCadastro;
	private List<Cliente> clientes;
	
	public void save() {
		Endereco e = new Endereco();
		e.setIdEndereco(idEndereco);
		
		Cliente c = new Cliente();
		c.setCpf(cpf);
		c.setNome(nome);
		c.setEmail(email);
		c.setTelefone(telefone);
		c.setEndereco(e);
		c.setDataNascimento(dataNascimento);
		
		IDAO dao = new ClienteDaoImp();
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Resultado",  dao.save(c)) );
	}
	public void update(RowEditEvent event) {
		IDAO dao = new ClienteDaoImp();
		FacesMessage msg = new FacesMessage("Resultado de editar livro", dao.update(event.getObject()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void dontUpdate(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicao cancelada para",
				"CPF "+((Cliente)event.getObject()).getCpf()+"\n"+
				((Cliente)event.getObject()).getNome());
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
}
