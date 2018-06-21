package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifb.bd2.model.Cliente;
import br.edu.ifb.bd2.model.Compra;


public class CompraDaoImp implements ICompraDAO{

	public String save(Object obj) {
		Compra c = (Compra) obj;
		String sql = "insert into compras(codigo, data_compra, situacao, data_cancelamento, cpf) values(?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, c.getCodigo());
			pst.setDate(2, c.getData_compra());
			pst.setString(3, c.getSituacao());
			pst.setDate(4, c.getData_cancelamento());
			pst.setString(5, c.getCliente().getCpf());
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
		Compra c = (Compra) obj;
		String sql = "delete from compras where codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, c.getCodigo());
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
		Compra c = (Compra) obj;
		String sql = "update compras set data_compra=?, situacao=?, data_cancelamento=?, cpf=? where codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, c.getData_compra());
			pst.setString(2, c.getSituacao());
			pst.setDate(3, c.getData_cancelamento());
			pst.setString(4, c.getCliente().getCpf());
			pst.setInt(5, c.getCodigo());
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
		String sql = "select * from compras";
		List<Object> compras = new ArrayList<Object>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Compra c = new Compra();
					c.setCodigo(rs.getInt(1));
					c.setData_compra(rs.getDate(2));
					c.setSituacao(rs.getString(3));
					c.setData_cancelamento(rs.getDate(4));
					Cliente cl = new Cliente();
					cl.setCpf(rs.getString(5));
					c.setCliente(cl);
					compras.add(c);
				}
			} else {
				compras = null;
			}
		} catch (SQLException e) {
			compras = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return compras;
	}

	public String alteraPrecoVenda(String isbn, Float novoPreco) {
		String sql = "call fc_altera_preco_venda(?,?);";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, isbn);
			pst.setFloat(1, novoPreco);
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

	public String pagarCompra(Integer codigoCompra) {
		String sql = "call proc_pagar_compra(?);";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigoCompra);
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Pago com sucesso.";
			} else {
				return "Erro ao pagar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String cancelarCompra(Integer codigoCompra) {
		String sql = "call proc_cancelar_compra(?);";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codigoCompra);
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Cancelado com sucesso.";
			} else {
				return "Erro ao cancelar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

}
