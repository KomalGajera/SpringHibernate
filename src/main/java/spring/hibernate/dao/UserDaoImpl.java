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
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
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
    		Query q = getSession().createQuery("update User u set u.password=:password where u.email=:email");
    		q.setParameter("password", user.getPassword());
    		q.setParameter("email", user.getEmail());
    		status = q.executeUpdate();
    		   } catch (RuntimeException e) {    		      
    		      logger.error("error while change password data to database.."+e);    		   
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
		Query q =null;
    	try {
    		q= getSession().createQuery("from User where email=:email and password=:password");
    		q.setParameter("email", user.getEmail());
    		q.setParameter("password", user.getPassword());
    		   } catch (RuntimeException e) {    		      
    		      logger.error("error while update  data to database.."+e);    		   
    		}       
		return (User) q.uniqueResult();
		 
	}

}
