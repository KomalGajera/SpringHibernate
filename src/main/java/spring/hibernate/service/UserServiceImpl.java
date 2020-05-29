package spring.hibernate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.dao.UserDao;
import spring.hibernate.entitymodel.Users;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserDao dao;
	
	@Override
	public int save(Users user) {
		// TODO Auto-generated method stub
		LOGGER.info("user save data...");
		
		  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		  try { 
			  Date date = formatter.parse(user.getDob()); formatter.applyPattern("yyyy-MM-dd");
			  user.setDob(formatter.format(date));
		  
		  } catch (ParseException e) { e.printStackTrace(); }
		 
			if(user.getUserId()==0) {				 
				return dao.save(user);
			}else {
				 
				return dao.update(user);
				
			}
	
	}

	@Override
	public List<Users> getAllUser() {
		// TODO Auto-generated method stub
		LOGGER.info("user get data...");
		return dao.findAll(Users.class);
	}

	@Override
	public int deleteUser(int userid) {
		// TODO Auto-generated method stub
		LOGGER.info("user delete data...");
		 return dao.delete(Users.class,userid);
	}



	@Override
	public Users getUserById(int userid) {
		// TODO Auto-generated method stub
		LOGGER.info("user get data by user id..");
		Users user=dao.getbyid(Users.class, userid);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 try {
	            Date date = formatter.parse(user.getDob());
	            formatter.applyPattern("dd/MM/yyyy");
	            user.setDob(formatter.format(date));

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }		
		return user;
	}

	@Override
	public int changepassword(Users user) {
		// TODO Auto-generated method stub
		LOGGER.info("user change data...");
		return dao.changepassword(user);
	}

	@Override
	public Users findUser(Users user) {
		// TODO Auto-generated method stub
		LOGGER.info("user find data for login..");
		return dao.findUser(user);
	}

}
