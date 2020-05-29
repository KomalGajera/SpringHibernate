package spring.hibernate.dao;

import java.util.List;

import spring.hibernate.entitymodel.State;

public interface StateDao extends GenericDao<State> {
	

	List<State> getAllStateByCountry(State state);

}
