package spring.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;


/**
* java program to perform crud operation for T information. 
*  T=country/state/user 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-05-21 
*
*/
@SuppressWarnings("unchecked")
public class GenericDaoImpl<T> implements GenericDao<T> {
	
	private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
	 
	
    /** 
	    * This is the save method which use to store T data to database 
	    * @param T is use it contain T information.
	 */ 
    @Override
    public int save(final T t) {
    	int status=0;
    	try {
    	 	status=(int) getSession().save(t);
    		   } catch (RuntimeException e) {
    		      
    		      logger.error("error while save  data to database.."+e);
    		   
    		}       
		return status;
    }   

    /** 
	    * This is the findAll method which use to retrive T data to database 
	    * @param T is use it contain T information.
	 */
	public List< T > findAll(Class<T> cls){
		@SuppressWarnings("rawtypes")
		Query q = null;
    	try {
    		 q = getSession().createQuery("from "+cls.getName() );
    		   } catch (RuntimeException e) {
    		      
    		      logger.error("error while fetch data from database.."+e);
    		   
    		}
		return (List<T>) q.list();      
		
      
     }

	   /** 
	    * This is the update method which use to update T data to database 
	    * @param T is use it contain T information.
	 */
    @Override
    public int update(final T t) {
    	
    	int status=0;
    	try {
    	 	 getSession().update(t);
    	 	 status=1;
    		   } catch (RuntimeException e) {    		      
    		      logger.error("error while update  data to database.."+e);    		   
    		}     
		return status;
    	    
    }

    
    /** 
	    * This is the getbyid method which use to retrive T data to database depend on id
	    * @param T is use it contain T information.
	    * @param id is use it contain T'id information.
	 */
	@Override
	public T getbyid(Class<T> cls, int id) {
		T t = null;
		try {
			t=getSession().get(cls, id);
   		   } catch (RuntimeException e) {  
   			   
   		      logger.error("error while fetch  data by id to database.."+e);  
   		      
   		      
   		}     		
	    return t;
	}


	   /** 
	    * This is the delete method which use to detete T data to database 
	    * @param T is use it contain T information.
	    * @param id is use it contain T'id information.
	 */
	@Override
	public void delete(Class<T> cls, int id) {
		// TODO Auto-generated method stub
		
		try {
			T t=getSession().load(cls, id);
			getSession().delete(t);
   		   } catch (RuntimeException e) {  
   			   
   		      logger.error("error while fetch  data by id to database.."+e);     		      
   		      
   		}     		
		
		
	}

}
