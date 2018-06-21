package br.edu.ifb.bd2.controller;

import javax.swing.JOptionPane;

import br.edu.ifb.bd2.model.Compra;
import br.edu.ifb.bd2.model.ItensCompra;
import br.edu.ifb.bd2.model.Livro;
import br.edu.ifb.bd2.persistence.ItensComprasDaoImp;

public class CadastroItensCompras {
	public static void main(String[] args) {
		Compra c = new Compra(); 
		c.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da compra:")));
		
		int op;
		do{
			op = JOptionPane.showConfirmDialog(null, adicionarItem(c) + "Deseja adicionar um novo produto na mesma compra?");
		}while(op==0);
	}
	
	static String adicionarItem(Compra c){
		ItensCompra i = new ItensCompra();
		
		i.setCompra(c);
		
		Livro l = new Livro();
		l.setIsbn(JOptionPane.showInputDialog("Digite o ISBN do Livro:"));
		i.setLivro(l);
		
		i.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade deste livro comprada:")));
		
		i.setPrecoVendido(Float.parseFloat(JOptionPane.showInputDialog("Digite o preco de venda deste livro:")));
		
		ItensComprasDaoImp idao = new ItensComprasDaoImp();
		
		return idao.save(i);
	}
}
