package br.edu.ifb.bd2.persistence;

import java.util.List;

public interface IDAOView extends IDAO{

	public List<Object> getView();
	
}
