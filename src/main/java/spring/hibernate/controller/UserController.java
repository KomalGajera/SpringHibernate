package spring.hibernate.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import spring.hibernate.entitymodel.Address;
import spring.hibernate.entitymodel.Country;
import spring.hibernate.entitymodel.State;
import spring.hibernate.entitymodel.Users;
import spring.hibernate.service.CountryService;
import spring.hibernate.service.StateService;
import spring.hibernate.service.UserAddressService;
import spring.hibernate.service.UserService;

@Controller
public class UserController {
	
	
		private static final Logger LOGGER = Logger.getLogger(UserController.class);
		
		static{        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        System.setProperty("current.date", dateFormat.format(new Date()));
	    }
		
	   @Autowired  
	   CountryService countryservice;
	   
	   @Autowired  
	   StateService stateservice;	   
	   
	   @Autowired  
	   UserService userservice;	  
	   
	   @Autowired  
	   UserAddressService useraddress;	  
	   
	   @Autowired
	   private HttpSession session;
	
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
	   public String forgetpassword(Model model) {	      
	      return "forgetpassword";
	   }	
	   
	
	   
	   //<-----------------this data is for country-------------------> //
	   /*this mapping is use to show country page*/
	   @GetMapping("/addcountry")
	   public String addcountry(Model model) {
		   return "addcountry";
	   }
	   
	   /*this request mapping is use to display list of country which are available in database*/
	    @GetMapping(value = "/displaycountry", produces = "application/json")
		public @ResponseBody List<Country> listEmployee() {
		   List<Country> listCountry = countryservice.getAllCountry();
		   return listCountry;
		}    
	
	    /*this mapping is use to add new country to database*/
	   @PostMapping(value="/countryadd")
	   public String countryadd(@ModelAttribute("country") Country country,Model model) {		   	 
			  int status=countryservice.save(country);
			  if(status!=0) {
				  return "redirect:/addcountry";
			  }else {
				  model.addAttribute("Message", "country does not add sucessfully..");
				  return "addcountry";
			  }
			   
			 
	   }
	   
	   /*this mapping is use to delete country from database*/
	   @RequestMapping(value = "/countrydelete/{country_id}", method = RequestMethod.DELETE, produces = "application/json")
		public String deleteCountry(@PathVariable("country_id") int countryid,Model model) {
	    	int status=countryservice.deleteCountry(countryid);
	    	if(status!=0) {
	    		return "addcountry";
	    	}else {
	    		  model.addAttribute("Delete", "there is an error of delete country..");
				  return "addcountry";
	    	}
	    	
		}
	   
	   /*this mapping is use to update old country to database*/
	   @PostMapping(value = "/countryupdate")
	 		public @ResponseBody Country countryupdate(	@RequestParam("id") int countryid) {		   
		   		Country country=countryservice.getCountry(countryid);
				return country;
	 		   
	   }
	   
	   
	   //<-----------------this data is for state-------------------> //
	   
	   /*this mapping is use to display state page details.*/
	   @GetMapping("/addstate")
	   public String addState(Model model) {
		   return "addstate";
	   }   
	   
	   /*this mapping is use to update state old records to database*/
	   @PostMapping(value = "/stateupdate")
		public @ResponseBody State stateupdate(	@RequestParam("id") int stateid) {		   
	   		State state=stateservice.getState(stateid);
			return state;		   
	   }
  
	   	/*this mapping is use to display all state data from database*/
	    @GetMapping(value = "/displaystate", produces = "application/json")
		public @ResponseBody List<State> listState() {
	    	 List<State> listState = stateservice.getAllState();
		   return listState;
		}   
	    
	    /*this mapping is use to fetch state name related particular country name */
	    @PostMapping(value = "/displaystatebycountry/{country_name}", produces = "application/json")
	  		public @ResponseBody List<State> displaystatebycountry(	@PathVariable("country_name") String countryname) {
	    		 State state=new State();
			   	 Country country=countryservice.getCountryByName(countryname);
			   	 state.setCountry(country);
	  	    	 List<State> listState = stateservice.getAllStateByCountry(state);
	  	    	 return listState;
	  		}   
	   
	   /*this mapping is use to add new state to database*/ 
	   @PostMapping(value="/stateadd")
	   public String stateadd(@ModelAttribute("state") State state,@ModelAttribute("country") Country country,Model model) {			   	
		   	 Country newcountry=countryservice.getCountryByName(country.getCountryName());
		   	 state.setCountry(newcountry);
		   	 int status=stateservice.save(state);
		   	 if(status!=0) {
		   		return "redirect:/addstate";
		   	 }else {
		   		 model.addAttribute("Message", "there is some error in state store/update");
				 return "addstate";
		   	 }
			  
			 
	   }
	   
	   /*this maping is use to delete state by state id*/
	   @DeleteMapping(value = "/statedelete/{state_id}", produces = "application/json")
		public String deleteState(	@PathVariable("state_id") int stateid,Model model) {
	       int status=stateservice.deleteState(stateid);
	    	if(status==1) {
	    		return "addstate";
	    	}else {
	    		 model.addAttribute("Delete", "there is some error of delete state");
				   return "addstate";
	    	}
		   
		}
	   
	   //<-----------------this data is for User-------------------> //
	   
	    
	   /*this mapping is use to login user*/
	   @PostMapping(value="/login")
	   public String login(@ModelAttribute("user") Users user,Model model) {
		   
		   Users newuser=userservice.findUser(user);
		   if(newuser!= null) {
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
	   
	   /*this maping is use to display user all records*/
	   @GetMapping("/showuser")
	   public String showuser(Model model) {
	      return "showuser";
	   }
	   
	   /*this mapping is use to logout user and remove session*/
	   @GetMapping(value = "logout")
		public String logout(HttpSession session) {
		   
			session.removeAttribute("username");
			session.removeAttribute("user");
			session.removeAttribute("userid");
			return "redirect:/";
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
		   if(result.hasErrors()) {
			   return "register";
		   }
		   ArrayList<Address> address=new ArrayList<Address>();    
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
		    user.setRole("user");
		    int status=userservice.save(user);
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
		public @ResponseBody Users getUser(	@RequestParam("id") int userid) {
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
		   return user;
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
	   
	   /*this method is use to count total number of address for particular user*/
	   @PostMapping(value = "/checkaddress")
	 		public @ResponseBody int checkAddress(	@RequestParam("id") int userid) {	 		   
			   Users user= userservice.getUserById(userid); 
			   List<Address> list=user.getAddress();
			   int size=list.size();
			   return size;
	 	    	
	 	}  
	   
	   /*this mapping is use to get all user from database*/
	   @GetMapping(value = "/displayuser", produces = "application/json")
		public @ResponseBody List<Users> listUser() {
	    	 List<Users> listUser = userservice.getAllUser();    	
		     return listUser;
		} 
	   
	   
	   /*this method is use to display image*/
	   @GetMapping(value = "/image/{id}")
		public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") int userid) throws Exception {
			response.setContentType("image/jpeg");
			Users user = userservice.getUserById(userid);
			byte[] bytes =user.getData();
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
	   
	   /*this method is use to delete user by userid*/
	   @DeleteMapping(value = "/userdelete/{id}", produces = "application/json")
	 		public String deleteUser(	@PathVariable("id") int userid,Model model) {
	 	      int status=userservice.deleteUser(userid); 	
	 	      if(status==1) {
	 	    	 return "showuser";
	 	      }else {
	 	    	   model.addAttribute("Delete", "There is some error of delete user");
				   return "showuser";
	 	      }
	 		  
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
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	    
	   
	   
}
