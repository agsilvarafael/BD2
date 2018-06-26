package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Fornecedor;
import br.edu.ifb.bd2.model.Livro;
import br.edu.ifb.bd2.model.Prateleira;

public class LivroDaoImp implements IDAO{

	public String save(Object obj) {
		Livro l = (Livro) obj;
		String sql = "insert into livros(isbn, titulo, autor, precoCusto, precoVenda, estoqueMinimo, qtdEstoque,"
				+ "codigoPrateleira, cnpj_fornecedor) values(?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getIsbn());
			pst.setString(2, l.getTitulo());
			pst.setString(3, l.getAutor());
			pst.setFloat(4, l.getPrecoCusto());
			pst.setFloat(5, l.getPrecoVenda());
			pst.setInt(6, l.getEstoqueMinimo());
			pst.setInt(7, l.getQtdEstoque());
			pst.setInt(8, l.getPrateleira().getCodigoPrateleira());
			pst.setString(9, l.getFornecedor().getCnpj());
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
		Livro l = (Livro) obj;
		String sql = "delete from livros where isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getIsbn());
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
		Livro l = (Livro) obj;
		String sql = "update livros set titulo=?, autor=?, precoCusto=?, precoVenda=?, estoqueMinimo=?, qtdEstoque=?,"
				+ "codigoPrateleira=?, cnpj_fornecedor=? where isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getTitulo());
			pst.setString(2, l.getAutor());
			pst.setFloat(3, l.getPrecoCusto());
			pst.setFloat(4, l.getPrecoVenda());
			pst.setInt(5, l.getEstoqueMinimo());
			pst.setInt(6, l.getQtdEstoque());
			pst.setInt(7, l.getPrateleira().getCodigoPrateleira());
			pst.setString(8, l.getFornecedor().getCnpj());
			pst.setString(9, l.getIsbn());
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
		String sql = "select * from livros";
		List<Object> livros = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Livro l = new Livro();
					l.setIsbn(rs.getString(1));
					l.setTitulo(rs.getString(2));
					l.setAutor(rs.getString(3));
					l.setPrecoCusto(rs.getFloat(4));
					l.setPrecoVenda(rs.getFloat(5));
					l.setEstoqueMinimo(rs.getInt(6));
					l.setQtdEstoque(rs.getInt(7));
					Prateleira p = new Prateleira();
					p.setCodigoPrateleira(rs.getInt(8));
					l.setPrateleira(p);
					Fornecedor f = new Fornecedor();
					f.setCnpj(rs.getString(9));
					l.setFornecedor(f);
					livros.add(l);
				}
			} else {
				livros = null;
			}
		} catch (SQLException e) {
			livros = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return livros;
	}

}
