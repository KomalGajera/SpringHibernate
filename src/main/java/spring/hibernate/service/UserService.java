package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.User;


public interface UserService {
	
	public int save(User user);
	 
    public List<User> getAllUser();
 
    public int deleteUser(int userid); 

	public User getUserById(int userid);

	public int changepassword(User user);

	public User findUser(User user);

}
