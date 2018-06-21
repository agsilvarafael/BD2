package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Compra;
import br.edu.ifb.bd2.model.ItensCompra;
import br.edu.ifb.bd2.model.Livro;

public class ItensComprasDaoImp implements IDAO{

	public String save(Object obj) {
		ItensCompra i = (ItensCompra) obj;
		String sql = "insert into itens_compras values(?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, i.getCompra().getCodigo());
			pst.setString(2, i.getLivro().getIsbn());
			pst.setFloat(3, i.getPrecoVendido());
			pst.setInt(4, i.getQuantidade());
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
		ItensCompra i = (ItensCompra) obj;
		String sql = "delete from itens_compras where codigo_compra=? and isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, i.getCompra().getCodigo());
			pst.setString(2, i.getLivro().getIsbn());
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
		ItensCompra i = (ItensCompra) obj;
		String sql = "update itens_compras set preco_vendido=?, quantidade=? where codigo_compra=? and isbn=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setFloat(1, i.getPrecoVendido());
			pst.setInt(2, i.getQuantidade());
			pst.setInt(3, i.getCompra().getCodigo());
			pst.setString(4, i.getLivro().getIsbn());
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
		String sql = "select * from itens_compras";
		List<Object> itensCompras = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ItensCompra i = new ItensCompra();
					Compra c = new Compra();
					c.setCodigo(rs.getInt(1));
					i.setCompra(c);
					Livro l = new Livro();
					l.setIsbn(rs.getString(2));
					i.setLivro(l);
					i.setPrecoVendido(rs.getFloat(3));
					i.setQuantidade(rs.getInt(4));
					itensCompras.add(i);
				}
			} else {
				itensCompras = null;
			}
		} catch (SQLException e) {
			itensCompras = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return itensCompras;
	}
}
