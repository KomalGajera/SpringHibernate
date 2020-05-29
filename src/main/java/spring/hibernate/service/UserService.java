package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.Users;


public interface UserService {
	
	int save(Users user);
	 
    List<Users> getAllUser();
 
    int deleteUser(int userid); 

	Users getUserById(int userid);

	int changepassword(Users user);

	Users findUser(Users user);

}
