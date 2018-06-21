package br.edu.ifb.bd2.controller;

import java.sql.Date;

import javax.swing.JOptionPane;

import br.edu.ifb.bd2.model.Cliente;
import br.edu.ifb.bd2.model.Compra;
import br.edu.ifb.bd2.persistence.CompraDaoImp;

public class CadastroCompras {
	public static void main(String[] args) {
		Compra c = new Compra(); 
		Cliente cli = new Cliente();
		cli.setCpf(JOptionPane.showInputDialog("Digite o CPF do Cliente:"));
		c.setCliente(cli);
		
		c.setData_compra(new Date(System.currentTimeMillis()));
		
		c.setSituacao("aberta");
		
		CompraDaoImp cp = new CompraDaoImp();
		String result = cp.save(c);
		
		if(result.equals("Inserido com sucesso.")){
		
			int op = JOptionPane.showConfirmDialog(null, result+"\nDeseja adicionar um produto?");
			while(op==0){
				op = JOptionPane.showConfirmDialog(null, CadastroItensCompras.adicionarItem(c) + "\nDeseja adicionar um novo produto na mesma compra?");
			}
		}
		
		else{
			JOptionPane.showMessageDialog(null, result);
		}
	}
}
