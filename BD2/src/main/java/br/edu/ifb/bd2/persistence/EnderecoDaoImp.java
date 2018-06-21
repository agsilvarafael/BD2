package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Endereco;


public class EnderecoDaoImp implements IDAO{

	public String save(Object obj) {
		Endereco e = (Endereco) obj;
		String sql = "insert into enderecos values(?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e.getCep());
			pst.setString(2, e.getBairro());
			pst.setString(3, e.getLogradouro ());
			pst.setString(4, e.getNumero());
			pst.setString(5, e.getComplemento());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Inserido com sucesso.";
			} else {
				return "Erro ao inserir.";
			}
		} catch (SQLException ex) {
			return ex.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String delete(Object obj) {
		Endereco e = (Endereco) obj;
		String sql = "delete from enderecos where id_endereco=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, e.getIdEndereco());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			} else {
				return "Erro ao excluir.";
			}
		} catch (SQLException ex) {
			return ex.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String update(Object obj) {
		Endereco e = (Endereco) obj;
		String sql = "update enderecos set cep=?, bairro=?, logradouro=?, numero=?, complemento=? where id_endereco=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e.getCep());
			pst.setString(2, e.getBairro());
			pst.setString(3, e.getLogradouro ());
			pst.setString(4, e.getNumero());
			pst.setString(5, e.getComplemento());
			pst.setInt(6, e.getIdEndereco());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Alterado com sucesso.";
			} else {
				return "Erro ao alterar.";
			}
		} catch (SQLException ex) {
			return ex.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public List<Object> list() {
		String sql = "select * from enderecos";
		List<Object> enderecos = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Endereco e = new Endereco();
					e.setIdEndereco(rs.getInt(1));
					e.setCep(rs.getString(2));
					e.setBairro(rs.getString(3));
					e.setLogradouro(rs.getString(4));
					e.setNumero(rs.getString(5));
					e.setComplemento(rs.getString(6));
					enderecos.add(e);
				}
			} else {
				enderecos = null;
			}
		} catch (SQLException e) {
			enderecos = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return enderecos;
	}

}
