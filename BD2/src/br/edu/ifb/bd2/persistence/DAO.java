package br.edu.ifb.bd2.persistence;

import java.util.List;

public interface DAO {
	public String save(Object obj);
	public String delete(Object obj);
	public String update(Object obj);
	public List<Object> list();
}
