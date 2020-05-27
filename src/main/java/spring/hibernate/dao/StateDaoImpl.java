package spring.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.hibernate.entitymodel.State;


/**
* java program to perform crud operation for state information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-05-21 
*
*/
@Repository("stateDao")
@SuppressWarnings("rawtypes")
public class StateDaoImpl extends GenericDaoImpl<State> implements StateDao {
	
	private static final Logger logger = Logger.getLogger(StateDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {

        return sessionFactory.getCurrentSession();
    }
    
    /** 
	    * This is the getAllStateByCountry method which use to retrive state data from database 
	    * @param state is use it contain state information.
	 */ 
	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllStateByCountry(State state) {
		// TODO Auto-generated method stub
		
		
		Query q=null;
    	try {
    		q = getSession().createQuery("from State where country_id = :country_id ");
    		q.setParameter("country_id", state.getCountry().getCountry_id());	
    		   } catch (RuntimeException e) {    		      
    		      logger.error("error while fetch country by state  data from database.."+e);    		   
    		} 	
		
		return (List<State>) q.list();
	}

}
