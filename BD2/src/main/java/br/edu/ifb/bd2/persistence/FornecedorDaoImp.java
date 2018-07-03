package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Endereco;
import br.edu.ifb.bd2.model.Fornecedor;

public class FornecedorDaoImp implements IDAO{

	public String save(Object obj) {
		Fornecedor f = (Fornecedor) obj;
		String sql2 = "insert into fornecedores values(?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql2);
			pst = con.prepareStatement(sql2);
				pst.setString(1, f.getCnpj());
				pst.setString(2, f.getNome());
				pst.setString(3, f.getEmail());
				pst.setInt(4, f.getEndereco().getIdEndereco());
				pst.setString(5, f.getTelefone());
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
		Fornecedor f = (Fornecedor) obj;
		String sql = "delete from fornecedores where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, f.getCnpj());
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
		Fornecedor f = (Fornecedor) obj;
		String sql = "update fornecedores set nome=?, email=?, id_endereco =?, telefone =?, where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, f.getNome());
			pst.setString(2, f.getEmail());
			pst.setInt(3, f.getEndereco().getIdEndereco());
			pst.setString(4, f.getTelefone());
			pst.setString(5, f.getCnpj());
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
		String sql = "select * from fornecedores";
		List<Object> fornecedores = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Fornecedor f = new Fornecedor();
					f.setCnpj(rs.getString(1));
					f.setNome(rs.getString(2));
					f.setEmail(rs.getString(3));
					Endereco e = new Endereco();
					e.setIdEndereco(rs.getInt(4));
					f.setEndereco(e);
					f.setTelefone(rs.getString(5));
					fornecedores.add(f);
				}
			} else {
				fornecedores = null;
			}
		} catch (SQLException e) {
			fornecedores = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return fornecedores;
	}
}
