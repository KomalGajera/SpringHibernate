package spring.hibernate.dao;


import spring.hibernate.entitymodel.Users;

public interface UserDao extends GenericDao<Users> {
	
	 
    int changepassword(Users user);

	Users findUser(Users user);

}
