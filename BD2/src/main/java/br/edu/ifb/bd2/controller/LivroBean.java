package br.edu.ifb.bd2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.edu.ifb.bd2.model.Fornecedor;
import br.edu.ifb.bd2.model.Livro;
import br.edu.ifb.bd2.model.Prateleira;
import br.edu.ifb.bd2.persistence.IDAO;
import br.edu.ifb.bd2.persistence.LivroDaoImp;

@ManagedBean(name="livroControle", eager=true)
public class LivroBean {

	private List<Livro> livros;
	private String isbn;
	private String titulo;
	private String autor;
	private Float precoCusto;
	private Float precoVenda;
	private Integer estoqueMinimo;
	private Integer qtdEstoque;
	private String fornecedorCNPJ;
	private Integer prateleiraCodigo;
	private Map<String, String> fornecedores = new HashMap<String, String>();//Nome, CNPJ
	private Map<String, Integer> prateleiras = new HashMap<String, Integer>();//Local, Codigo
	
	@PostConstruct
	public void init() {
		//TODO adicionar os valores nos maps puxando os registros do banco
	}
	
	public void add() {
		Fornecedor f = new Fornecedor();
		f.setCnpj(fornecedorCNPJ);
		Prateleira p = new Prateleira();
		p.setCodigo_prateleira(prateleiraCodigo);
		Livro l = new Livro();
		l.setIsbn(isbn);
		l.setTitulo(titulo);
		l.setAutor(autor);
		l.setPrecoCusto(precoCusto);
		l.setPrecoVenda(precoVenda);
		l.setEstoqueMinimo(estoqueMinimo);
		l.setQtdEstoque(qtdEstoque);
		l.setFornecedor(f);
		l.setPrateleira(p);
		limpar();
	}

	public void update(RowEditEvent event) {
		IDAO dao = new LivroDaoImp();
		FacesMessage msg = new FacesMessage("Resultado de editar livro", dao.update(event.getObject()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void dontUpdate(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edi��o cancelada para",
				"ISBN "+((Livro)event.getObject()).getIsbn()+"\n"+
				((Livro)event.getObject()).getTitulo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private void limpar() {
		isbn = "";
		titulo = "";
		autor = "";
		precoCusto = 0f;
		precoVenda = 0f;
		estoqueMinimo = 0;
		qtdEstoque = 0;
		fornecedorCNPJ = "";
		prateleiraCodigo = 0;
	}

	public String getAutor() {
		return autor;
	}
	
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	
	public String getFornecedorCNPJ() {
		return fornecedorCNPJ;
	}
	
	public Map<String, String> getFornecedores() {
		return fornecedores;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public Integer getPrateleiraCodigo() {
		return prateleiraCodigo;
	}

	public Map<String, Integer> getPrateleiras() {
		return prateleiras;
	}

	public Float getPrecoCusto() {
		return precoCusto;
	}

	public Float getPrecoVenda() {
		return precoVenda;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public void setFornecedorCNPJ(String fornecedorCNPJ) {
		this.fornecedorCNPJ = fornecedorCNPJ;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void setPrateleiraCodigo(Integer prateleiraCodigo) {
		this.prateleiraCodigo = prateleiraCodigo;
	}

	public void setPrecoCusto(Float precoCusto) {
		this.precoCusto = precoCusto;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
