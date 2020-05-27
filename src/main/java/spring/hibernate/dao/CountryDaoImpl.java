package spring.hibernate.dao;



import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.hibernate.entitymodel.Country;


/**
* java program to perform crud operation for country information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-05-21 
*
*/
@Repository("countryDao")
@SuppressWarnings("rawtypes")
public class CountryDaoImpl extends GenericDaoImpl<Country> implements CountryDao {

	private static final Logger logger = Logger.getLogger(CountryDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
    
    /** 
	    * This is the getcountryByName method which use to retrive country data from database 
	    * @param countryname is use it contain countryname.
	 */ 
	@Override
	public Country getCountryByName(String countryname) {
		// TODO Auto-generated method stub
		
		Query q=null;
    	try {
    		q = getSession().createQuery("from Country where country_name = :country_name ");
    		q.setParameter("country_name", countryname);
    	
    		   } catch (RuntimeException e) {    		      
    		      logger.error("error while get country by countryname from database.."+e);    		   
    		}
		
		return (Country)q.list().get(0);
	}




	

}
