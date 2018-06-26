package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Funcionario;
import br.edu.ifb.bd2.model.Prateleira;

public class PrateleiraDaoImp implements IDAO{

	public String save(Object obj) {
		Prateleira p = (Prateleira) obj;
		String sql = "insert into prateleiras values(?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p.getLocalizacao());
			pst.setString(2, p.getFuncionario().getCpf());
			pst.setString(3, p.getDescricao());
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
		Prateleira p = (Prateleira) obj;
		String sql = "delete from prateleiras where codigoPrateleira=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, p.getCodigoPrateleira());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Exclu�do com sucesso.";
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
		Prateleira p = (Prateleira) obj;
		String sql = "update prateleiras set localizacao=?, cpf_funcionario_responsavel =?, descricao =? where codigoPrateleira=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p.getLocalizacao());
			pst.setString(2, p.getFuncionario().getCpf());
			pst.setString(3, p.getDescricao());
			pst.setInt(4, p.getCodigoPrateleira());
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
		String sql = "select * from prateleiras";
		List<Object> prateleiras = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Prateleira p = new Prateleira();
					p.setCodigoPrateleira(rs.getInt(1));
					p.setLocalizacao(rs.getString(2));
					Funcionario f = new Funcionario();
					f.setCpf(rs.getString(3));
					p.setFuncionario(f);
					p.setLocalizacao(rs.getString(4));
					prateleiras.add(p);
				}
			} else {
				prateleiras = null;
			}
		} catch (SQLException e) {
			prateleiras = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return prateleiras;
	}
}
