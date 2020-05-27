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
import spring.hibernate.entitymodel.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserDao dao;
	
	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 try {
	            Date date = formatter.parse(user.getDob());
	            formatter.applyPattern("yyyy-MM-dd");
	            user.setDob(formatter.format(date));

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		 
			if(user.getId()==0) {				 
				return dao.save(user);
			}else {
				 
				return dao.update(user);
				
			}
	
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return dao.findAll(User.class);
	}

	@Override
	public void deleteUser(int userid) {
		// TODO Auto-generated method stub
		dao.delete(User.class,userid);
	}



	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User u=dao.getbyid(User.class, id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 try {
	            Date date = formatter.parse(u.getDob());
	            formatter.applyPattern("dd/MM/yyyy");
	            u.setDob(formatter.format(date));

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }		
		return u;
	}

	@Override
	public int changepassword(User user) {
		// TODO Auto-generated method stub
		return dao.changepassword(user);
	}

	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		return dao.findUser(user);
	}

}
