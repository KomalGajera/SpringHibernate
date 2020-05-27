package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.User;


public interface UserService {
	
	public int save(User user);
	 
    public List<User> getAllUser();
 
    public void deleteUser(int userid); 

	public User getUserById(int id);

	public int changepassword(User user);

	public User findUser(User user);

}
