package spring.hibernate.dao;

import java.util.List;

import spring.hibernate.entitymodel.State;

public interface StateDao extends GenericDao<State> {
	

	public List<State> getAllStateByCountry(State state);

}
