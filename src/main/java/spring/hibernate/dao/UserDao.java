package spring.hibernate.dao;


import spring.hibernate.entitymodel.User;

public interface UserDao extends GenericDao<User> {
	
	 
	public int changepassword(User user);

	public User findUser(User user);

}
