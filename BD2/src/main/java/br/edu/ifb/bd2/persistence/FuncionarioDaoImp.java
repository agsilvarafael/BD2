package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Endereco;
import br.edu.ifb.bd2.model.Funcionario;


public class FuncionarioDaoImp implements IDAOView{

	public String save(Object obj) {
		Funcionario f = (Funcionario) obj;
		String sql0 = "select cpf from pessoas where cpf=?";
		String sql1 = "insert into funcionarios values(?,?,?,?,?)";
		String sql2 = "insert into funcionarios values(?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst0 = con.prepareStatement(sql0);
			ResultSet rs = pst0.executeQuery();
			if(!rs.next()) {//Salva os dados da pessoa caso ela ainda n�o esteja salva no BD
				PreparedStatement pst1 = con.prepareStatement(sql2);
					pst1.setString(1, f.getCpf());
					pst1.setString(2, f.getNome());
					pst1.setString(3, f.getTelefone());
					pst1.setString(4, f.getEmail());
					pst1.setDate(5, f.getDataNascimento());
					pst1.setInt(6, f.getEndereco().getIdEndereco());
			}
			PreparedStatement pst2 = con.prepareStatement(sql2);
				pst2.setString(1, f.getCpf());
				pst2.setDate(2, f.getDataContratacao());
				pst2.setString(3, f.getSenha());
				pst2.setString(4, f.getCargo());
				pst2.setString(5, f.getSituacao());
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
		Funcionario f = (Funcionario) obj;
		String sql = "delete from funcionarios where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, f.getCpf());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluido com sucesso.";
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
		Funcionario f = (Funcionario) obj;
		String sql = "update funcionarios set dataContratacao=?, senha=?, cargo=?, situacao=? where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, f.getDataContratacao());
			pst.setString(2, f.getSenha());
			pst.setString(3, f.getCargo());
			pst.setString(4, f.getSituacao());
			pst.setString(5, f.getCpf());
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
		String sql = "select * from funcionarios";
		List<Object> funcionarios = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Funcionario f = new Funcionario();
					f.setCpf(rs.getString(1));
					f.setCodigo(rs.getInt(2));
					f.setDataContratacao(rs.getDate(3));
					f.setSenha(rs.getString(4));
					f.setCargo(rs.getString(5));
					f.setSituacao(rs.getString(6));
					funcionarios.add(f);
				}
			} else {
				funcionarios = null;
			}
		} catch (SQLException e) {
			funcionarios = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return funcionarios;
	}

	public List<Object> getView() {
		String sql = "select * from view_dados_funcionario";
		List<Object> funcionarios = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Funcionario f = new Funcionario();
					f.setCodigo(rs.getInt(1));
					f.setCpf(rs.getString(2));
					f.setNome(rs.getString(3));
					f.setTelefone(rs.getString(4));
					f.setEmail(rs.getString(5));
					f.setSituacao(rs.getString(6));
					f.setDataContratacao(rs.getDate(7));
					Endereco e = new Endereco();
					e.setCep(rs.getString(8));
					e.setLogradouro(rs.getString(9));
					e.setNumero(rs.getString(10));
					e.setBairro(rs.getString(11));
					e.setComplemento(rs.getString(12));
					f.setEndereco(e);
					funcionarios.add(f);
				}
			} else {
				funcionarios = null;
			}
		} catch (SQLException e) {
			funcionarios = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return funcionarios;
	}
}
