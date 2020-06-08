package spring.hibernate.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import spring.hibernate.entitymodel.Address;
import spring.hibernate.entitymodel.Users;
import spring.hibernate.service.UserAddressService;
import spring.hibernate.service.UserService;

@Controller
@Validated
public class UserController {
	
	
		private static final Logger LOGGER = Logger.getLogger(UserController.class);
		
		static{        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        System.setProperty("current.date", dateFormat.format(new Date()));
	    }
		
	   @Autowired  
	   UserService userservice;	  
	   
	   @Autowired  
	   UserAddressService useraddress;	  
	   
	
	   /*this is use to maping the login page*/
	   @GetMapping("/")
	   public String login(Model model) {
		  LOGGER.debug("this is login method..");
	      model.addAttribute("message", "Hello i am login page!!!!!");
	      return "login";
	   }	  
	   
	   /*this mapping is use show user profile information */
	   @GetMapping("/profile")
	   public String profile(Model model) {
	      model.addAttribute("message", "Hello i am login page!!!!!");
	      return "profile";
	   }	
	   
	   /* this method is use to display forget password page*/
	   @GetMapping("/forgetpassword")
	   public String forgetpassword() {	      
	      return "forgetpassword";
	   }	
	   
	
	    
	  
	   /*this maping is use to display user all records*/
	   @GetMapping("/showuser")
	   public String showuser() {
	      return "showuser";
	   }
	   
	  
	   
	   /*this mapping is use to display register page*/
	   @GetMapping("/register")
	   public String register(Users user) {
		   user.setGender("male");
	        return "register";
	   }
	   
	   /*this mapping is use to store user detail to database*/
	   @PostMapping(value="/saveuser")
	   public String register(@Valid Users user,BindingResult result,@RequestParam CommonsMultipartFile[] fileUpload,Model model) {	
		   System.out.println("error:-"+result);		   
		 /*  if(result.hasErrors()) {
			   return "register";
		   }*/
		   ArrayList<Address> address=new ArrayList<Address>();  
		   
		   System.out.println("\n\n\n\n\n\n\nfile upload length"+fileUpload.length);
		   if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){    
	                user.setFileName(aFile.getOriginalFilename());
	                user.setData(aFile.getBytes());             
	            }
		   }
		   
		  
		   StringBuffer buf = new StringBuffer();
		   for (String s : user.getUserHobby()) {
			   buf.append(s);}	
		   String hobby = buf.toString();
		   
		   if(user.getUserId()==0) {
			   for (String add : user.getAdd()) {
			    	Address useradd=new Address();
			    	useradd.setUserAddress(add);
			    	useradd.setUser(user);
			    	address.add(useradd);}
			    user.setAddress(address);
		   }
		   else {
			   Address newadd=new Address();
			   newadd.setUser(user);
			   String arr[]=useraddress.deleteAddress(newadd);
			   for (String add : arr) {
				   System.out.println("addresss:"+add);
			    	Address useradd=new Address();
			    	useradd.setUserAddress(add);
			    	useradd.setUser(user);
			    	address.add(useradd);}
			    user.setAddress(address);
		   }		  
		    user.setHobby(hobby);
		    int status=1;//userservice.save(user);
		    if(status!=0) {		    	
		    	 return "redirect:/";
		    }
		    else {
		    	model.addAttribute("Message","there is some error of user store/update.");
		    	return "register";
		    }
	   }
	  
	   /*this maping is use to get user information by user id*/
	   @PostMapping(value = "/userbyid")
		public ResponseEntity<Users>  getUser(	@RequestParam("id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int userid) {
		   
		   Users user= userservice.getUserById(userid); 
		   List<Address> list=user.getAddress();
		   int size=list.size();
		   String arr[]=new String[size];
		   int count=0;
		   Iterator<Address> itr2=list.iterator();    
	       while(itr2.hasNext())  
	       {  
	    	  
	    	   Address address=itr2.next();
	    	   arr[count]=address.getUserAddress();
	    	   count++;
			   
		   }
	       user.setAdd(arr);
	       return ResponseEntity.ok(user);
		  // return user;
		}

	   
	   /*this method is use to count total number of address for particular user*/
	   @PostMapping(value = "/checkaddress")
	 		public ResponseEntity<Integer>  checkAddress(	@RequestParam("id")   @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int userid) {	 		   
			   Users user= userservice.getUserById(userid); 
			   List<Address> list=user.getAddress();
			   int size=list.size();
			   return ResponseEntity.ok(size);
	 	    	
	 	}  
	   
	   /*this mapping is use to get all user from database*/
	   @GetMapping(value = "/displayuser", produces = "application/json")
		public @ResponseBody List<Users> listUser() {
	    	 List<Users> listUser = userservice.getAllUser();    	
		     return listUser;
		} 
	   
	   
	   /*this method is use to display image*/
	   @GetMapping(value = "/image/{id}")
		public void getStudentPhoto(HttpServletResponse response, @PathVariable("id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int userid) throws Exception {
			response.setContentType("image/jpeg");
			Users user = userservice.getUserById(userid);
			byte[] bytes =user.getData();
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
	   
	   /*this method is use to delete user by userid*/
	   @DeleteMapping(value = "/userdelete/{id}", produces = "application/json")
	 		public String deleteUser(	@PathVariable("id") @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int userid,Model model) {
	 	      int status=userservice.deleteUser(userid); 	
	 	      if(status==1) {
	 	    	 return "showuser";
	 	      }else {
	 	    	   model.addAttribute("Delete", "There is some error of delete user");
				   return "showuser";
	 	      }
	 		  
	 	} 
	
	  @ExceptionHandler(ConstraintViolationException.class) 
	  public void constraintViolationException(HttpServletResponse response) throws IOException
	  { response.sendError(HttpStatus.BAD_REQUEST.value()); }
	 
	   
    
	   
}
