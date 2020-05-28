package spring.hibernate.service;

import java.util.List;
import spring.hibernate.entitymodel.State;

public interface StateService {
	
	public int save(State state);
	 
    public List<State> getAllState();
 
    public int deleteState(int stateId);
 
    public State getState(int stateid);

	public List<State> getAllStateByCountry(State state);
	

}
