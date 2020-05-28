package spring.hibernate.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.hibernate.entitymodel.User;

/**
* java program to perform crud operation for user information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-05-21
*
*/
@Repository("UserDao")
@SuppressWarnings("rawtypes")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
	
	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
   

    /** 
	    * This is the changepassword method which use to change user password data to database 
	    * @param user is use it contain user information.
	 */ 
	@Override
	public int changepassword(User user) {
		// TODO Auto-generated method stub
		
		int status=0;
    	try {
    		Query query = getSession().createQuery("update User u set u.password=:password where u.email=:email");
    		query.setParameter("password", user.getPassword());
    		query.setParameter("email", user.getEmail());
    		status = query.executeUpdate();
    		   } catch (RuntimeException e) {    		      
    		      LOGGER.error("error while change password data to database.."+e);    		   
    		}       
		return status;
		
	}


	 /** 
	    * This is the finduser method which use to get user data to database 
	    * @param user is use it contain user information.
	 */ 
	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		Query query =null;
    	try {
    		query= getSession().createQuery("from User where email=:email and password=:password");
    		query.setParameter("email", user.getEmail());
    		query.setParameter("password", user.getPassword());
    		   } catch (RuntimeException e) {    		      
    		      LOGGER.error("error while update  data to database.."+e);    		   
    		}       
		return (User) query.uniqueResult();
		 
	}

}
