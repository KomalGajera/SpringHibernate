package spring.hibernate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.hibernate.entitymodel.Users;
import spring.hibernate.service.UserService;

@Controller
public class Authenticator {
	

	  private static final Logger LOGGER = Logger.getLogger(CountryController.class);
	  
	  static{        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        System.setProperty("current.date", dateFormat.format(new Date()));
	    }
	  
	   @Autowired  
	   UserService userservice;	    
	   
	   @Autowired
	   private HttpSession session;
	   
	   
	   /*this mapping is use to login user*/
	   @PostMapping(value="/login")
	   public String login(@ModelAttribute("user") Users user,Model model) {
		   
		   Users newuser=userservice.findUser(user);
		   if(newuser!= null) {
			   LOGGER.info("login sucessfully....");
			   session.setAttribute("username",newuser.getFname());
			   session.setAttribute("user",newuser.getRole());
			   session.setAttribute("userid", newuser.getUserId());
			   return "redirect:/showuser";
			   
		   }
		   else {
			   model.addAttribute("error", "account is not valid");
			   return "error";
		}		
	   
	   }
	   
	   
	   /*this mapping is use to logout user and remove session*/
	   @GetMapping(value = "logout")
		public String logout(HttpSession session) {
		   
			session.removeAttribute("username");
			session.removeAttribute("user");
			session.removeAttribute("userid");
			return "redirect:/";
		}
	   
	   
	   /*this mapping is use to change user current password to new password*/
	   @PostMapping(value="/changepassword")
	   public String password(@ModelAttribute("user") Users user) {
		   int status=userservice.changepassword(user);
		    if(status!=0) {
		    	 return "redirect:/";
		    }
		    else {
		    	return "foregetpassword";
		    }
	   }
	   

	   /*this mapping is use to check user is exits or not at register time*/
	   @PostMapping(value = "/checkemail")
		public @ResponseBody int checkUser(	@RequestParam("email") String email) {  
		   
		   int result=0;
		   List<Users> listUser = userservice.getAllUser();    
		   for (Users u : listUser) {
				 if(u.getEmail().equals(email)) {
					 	result=1;
					 }
				 }		 
		   return result;
	    	
		}  
	   

}
