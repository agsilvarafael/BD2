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
		String sql = "insert into clientes values(?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, c.getCpf());
			pst.setDate(2, c.getData_cadastro());
			int res = pst.executeUpdate();
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
		String sql = "update clientes set data_cadastro=? where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, c.getData_cadastro());
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
					c.setData_cadastro(rs.getDate(2));
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
		String sql = "select * from view_dados_cliente";
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
					c.setData_cadastro(rs.getDate(10));
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
