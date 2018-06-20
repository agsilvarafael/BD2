package br.edu.ifb.bd2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifb.bd2.model.Livro;
import br.edu.ifb.bd2.persistence.IDAO;
import br.edu.ifb.bd2.persistence.LivroDaoImp;

@ManagedBean
@ViewScoped
public class LivrosDataGridView implements Serializable{

	private List<Livro> livros;
	private Livro livroSelecionado;
	
	@PostConstruct
	public void init() {
		IDAO dao = new LivroDaoImp();
		livros = (List<Livro>) (List) dao.list();
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public List<Livro> getLivros() {
		return livros;
	}
	
}
