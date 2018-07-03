package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Cliente;
import br.edu.ifb.bd2.model.Endereco;

public class ClienteDaoImp implements IDAOView{

	public String save(Object obj) {
		Cliente c = (Cliente) obj;
		String sql0 = "select cpf from pessoas where cpf=?";
		String sql1 = "insert into funcionarios values(?,?,?,?,?)";
		String sql2 = "insert into clientes values(?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst0 = con.prepareStatement(sql0);
			ResultSet rs = pst0.executeQuery();
			if(!rs.next()) {//Salva os dados da pessoa caso ela ainda não esteja salva no BD
				PreparedStatement pst1 = con.prepareStatement(sql2);
					pst1.setString(1, c.getCpf());
					pst1.setString(2, c.getNome());
					pst1.setString(3, c.getTelefone());
					pst1.setString(4, c.getEmail());
					pst1.setDate(5, c.getDataNascimento());
					pst1.setInt(6, c.getEndereco().getIdEndereco());
			}
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, c.getCpf());
			pst2.setDate(2, c.getDataCadastro());
			int res = pst2.executeUpdate();
			if (res > 0) {
				return "Inserido com sucesso.";
			} else {
				return "Erro ao inserir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String delete(Object obj) {
		Cliente c = (Cliente) obj;
		String sql = "delete from clientes where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, c.getCpf());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			} else {
				return "Erro ao excluir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String update(Object obj) {
		Cliente c = (Cliente) obj;
		String sql = "update clientes set dataCadastro=? where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, c.getDataCadastro());
			pst.setString(2, c.getCpf());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Alterado com sucesso.";
			} else {
				return "Erro ao alterar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public List<Object> list() {
		String sql = "select * from clientes";
		List<Object> clientes = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente c = new Cliente();
					c.setCpf(rs.getString(1));
					c.setDataCadastro(rs.getDate(2));
					clientes.add(c);
				}
			} else {
				clientes = null;
			}
		} catch (SQLException e) {
			clientes = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return clientes;
	}

	public List<Object> getView() {
		String sql = "select * from view_dadosCliente";
		List<Object> clientes = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente c = new Cliente();
					c.setCpf(rs.getString(1));
					c.setNome(rs.getString(2));
					c.setTelefone(rs.getString(3));
					c.setEmail(rs.getString(4));
					Endereco e = new Endereco();
					e.setCep(rs.getString(5));
					e.setLogradouro(rs.getString(6));
					e.setNumero(rs.getString(7));
					e.setBairro(rs.getString(8));
					e.setComplemento(rs.getString(9));
					c.setEndereco(e);
					c.setDataCadastro(rs.getDate(10));
					clientes.add(c);
				}
			} else {
				clientes = null;
			}
		} catch (SQLException e) {
			clientes = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return clientes;
	}
}
