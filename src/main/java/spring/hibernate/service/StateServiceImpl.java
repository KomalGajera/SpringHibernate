package spring.hibernate.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.dao.StateDao;
import spring.hibernate.entitymodel.State;

@Service("stateService")
@Transactional
public class StateServiceImpl implements StateService {

	
	private static final Logger LOGGER = Logger.getLogger(StateServiceImpl.class);
	
	@Autowired
	StateDao dao;
	
	@Override
	public int save(State state) {
		// TODO Auto-generated method stub
		if(state.getState_id()==0)
		{
			int status=dao.save(state);
			return status;
		}
		else {
			int status=dao.update(state);
			return status;
		}	
	}

	@Override
	public List<State> getAllState() {
		// TODO Auto-generated method stub
		return dao.findAll(State.class);
	}

	@Override
	public int deleteState(int stateId) {
		// TODO Auto-generated method stub
		return dao.delete(State.class, stateId);
	}


	@Override
	public State getState(int stateid) {
		// TODO Auto-generated method stub
		return dao.getbyid(State.class,stateid);
	}

	@Override
	public List<State> getAllStateByCountry(State state) {
		// TODO Auto-generated method stub
		return dao.getAllStateByCountry(state);
	}

}
