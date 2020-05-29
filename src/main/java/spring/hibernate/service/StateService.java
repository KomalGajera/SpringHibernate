package spring.hibernate.service;

import java.util.List;
import spring.hibernate.entitymodel.State;

public interface StateService {
	
	int save(State state);
	 
    List<State> getAllState();
 
    int deleteState(int stateId);
 
    State getState(int stateid);

	List<State> getAllStateByCountry(State state);
	

}
