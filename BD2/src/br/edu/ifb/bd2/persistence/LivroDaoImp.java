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

public class LivroDaoImp implements DAO{

	@Override
	public String save(Object obj) {
		Livro l = (Livro) obj;
		String sql = "insert into livros(isbn, título, autor, preco_custo, preco_venda, estoque_minimo, qtd_estoque,"
				+ "codigo_prateleira, cnpj_fornecedor) values(?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getIsbn());
			pst.setString(2, l.getTítulo());
			pst.setString(3, l.getAutor());
			pst.setFloat(4, l.getPreco_custo());
			pst.setFloat(5, l.getPreco_venda());
			pst.setInt(6, l.getEstoque_minimo());
			pst.setInt(7, l.getQtd_estoque());
			pst.setInt(8, l.getPrateleira().getCodigo_prateleira());
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

	@Override
	public String delete(Object obj) {
		Livro l = (Livro) obj;
		String sql = "delete from livros where isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getIsbn());
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

	@Override
	public String update(Object obj) {
		Livro l = (Livro) obj;
		String sql = "update livros set título=?, autor=?, preco_custo=?, preco_venda=?, estoque_minimo=?, qtd_estoque=?,"
				+ "codigo_prateleira=?, cnpj_fornecedor=? where isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, l.getTítulo());
			pst.setString(2, l.getAutor());
			pst.setFloat(3, l.getPreco_custo());
			pst.setFloat(4, l.getPreco_venda());
			pst.setInt(5, l.getEstoque_minimo());
			pst.setInt(6, l.getQtd_estoque());
			pst.setInt(7, l.getPrateleira().getCodigo_prateleira());
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

	@Override
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
					l.setTítulo(rs.getString(2));
					l.setAutor(rs.getString(3));
					l.setPreco_custo(rs.getFloat(4));
					l.setPreco_venda(rs.getFloat(5));
					l.setEstoque_minimo(rs.getInt(6));
					l.setQtd_estoque(rs.getInt(7));
					Prateleira p = new Prateleira();
					p.setCodigo_prateleira(rs.getInt(8));
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
