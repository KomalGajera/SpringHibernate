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
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import spring.hibernate.entitymodel.Student;
import spring.hibernate.entitymodel.User;
import spring.hibernate.service.CountryService;
import spring.hibernate.service.StateService;
import spring.hibernate.service.UserAddressService;
import spring.hibernate.service.UserService;

@Controller
public class UserController {
	
	
		private static final Logger logger = Logger.getLogger(UserController.class);
		
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
	   UserAddressService useraddressservice;	  
	   
	   @Autowired
	   private HttpSession session;
	   
	   @Autowired
	    private CacheManager cacheManager; 
	   
	 
	   
	   /*this is use to maping the login page*/
	   @GetMapping("/")
	   public String login(Model model) {
		  logger.debug("this is login method..");
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
	    @RequestMapping(value = "/displaycountry", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody List<Country> listEmployee() {
		   List<Country> listCountry = countryservice.getAllCountry();
		   return listCountry;
		}    
	
	    /*this mapping is use to add new country to database*/
	   @RequestMapping(value="/countryadd", method = RequestMethod.POST)
	   public String countryadd(@ModelAttribute("country") Country country) {		   	 
			  int status=countryservice.save(country);
			  System.out.print(status);		  
			  return "redirect:/addcountry";
	   }
	   
	   /*this mapping is use to delete country from database*/
	   @RequestMapping(value = "/countrydelete/{country_id}", method = RequestMethod.DELETE, produces = "application/json")
		public String deleteCountry(	@PathVariable("country_id") int country_id) {
	    	countryservice.deleteCountry(country_id);	    	
	    	return "addcountry";
		}
	   
	   /*this mapping is use to update old country to database*/
	   @RequestMapping(value = "/countryupdate", method = RequestMethod.POST)
	 		public @ResponseBody Country countryupdate(	@RequestParam("id") int id) {		   
		   		Country country=countryservice.getCountry(id);
				return country;
	 		   
	   }
	   
	   
	   //<-----------------this data is for state-------------------> //
	   
	   /*this mapping is use to display state page details.*/
	   @GetMapping("/addstate")
	   public String addState(Model model) {
		   return "addstate";
	   }   
	   
	   /*this mapping is use to update state old records to database*/
	   @RequestMapping(value = "/stateupdate", method = RequestMethod.POST)
		public @ResponseBody State stateupdate(	@RequestParam("id") int id) {		   
	   		State state=stateservice.getState(id);
			return state;		   
	   }
  
	   	/*this mapping is use to display all state data from database*/
	    @RequestMapping(value = "/displaystate", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody List<State> listState() {
	    	 List<State> listState = stateservice.getAllState();
		   return listState;
		}   
	    
	    /*this mapping is use to fetch state name related particular country name */
	    @RequestMapping(value = "/displaystatebycountry/{country_name}", method = RequestMethod.POST, produces = "application/json")
	  		public @ResponseBody List<State> displaystatebycountry(	@PathVariable("country_name") String country_name) {
	    		 State state=new State();
			   	 Country c=countryservice.getCountryByName(country_name);
			   	 state.setCountry(c);
	  	    	 List<State> listState = stateservice.getAllStateByCountry(state);
	  	    	 return listState;
	  		}   
	   
	   /*this mapping is use to add new state to database*/ 
	   @RequestMapping(value="/stateadd", method = RequestMethod.POST)
	   public String stateadd(@ModelAttribute("state") State state,@ModelAttribute("country") Country country) {			   	
		   	 Country c=countryservice.getCountryByName(country.getCountry_name());
		   	 state.setCountry(c);
		   	 int status=stateservice.save(state);
			 System.out.print(status);		  
			 return "redirect:/addstate";
	   }
	   
	   /*this maping is use to delete state by state id*/
	   @RequestMapping(value = "/statedelete/{state_id}", method = RequestMethod.DELETE, produces = "application/json")
		public String deleteState(	@PathVariable("state_id") int state_id) {
	       stateservice.deleteState(state_id);
	    	
		   return "addstate";
		}
	   
	   //<-----------------this data is for User-------------------> //
	   
	    
	   /*this mapping is use to login user*/
	   @RequestMapping(value="/login", method = RequestMethod.POST)
	   public String login(@ModelAttribute("user") User user,Model model) {
		   
		   User u=userservice.findUser(user);
		   if(u!= null) {
			   session.setAttribute("username",u.getFname());
			   session.setAttribute("user",u.getRole());
			   session.setAttribute("userid", u.getId());

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
	      model.addAttribute("message", "Hello i am show user form....");
	      return "showuser";
	   }
	   
	   /*this mapping is use to logout user and remove session*/
	   @RequestMapping(value = "logout", method = RequestMethod.GET)
		public String logout(HttpSession session) {
		   
			session.removeAttribute("username");
			session.removeAttribute("user");
			session.removeAttribute("userid");
			
			 for(String name:cacheManager.getCacheNames()){
		            cacheManager.getCache(name).clear();            // clear cache by name
		       }
			
			return "redirect:/";
		}
	   
	   /*this mapping is use to display register page*/
	   @GetMapping("/register")
	   public String register(User user) {
		   user.setGender("male");
	        return "register";
	   }
	   
	   /*this mapping is use to store user detail to database*/
	   @PostMapping(value="/saveuser")
	   public String register(@Valid User user,BindingResult result,@RequestParam CommonsMultipartFile[] fileUpload,Model model) {	
		   System.out.println("error:-"+result);
		   
		   if(result.hasErrors()) {
			   return "register";
		   }
		   String hobby="  ";
		   ArrayList<Address> address=new ArrayList<Address>();    
		   if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){            	
	                user.setFileName(aFile.getOriginalFilename());
	                user.setData(aFile.getBytes());             
	            }
		   }
		   for (String s : user.getUser_hobby()) {
				 hobby=hobby+s+"  ";}	
		   if(user.getId()==0) {
			   for (String add : user.getAdd()) {
			    	Address user_add=new Address();
			    	user_add.setAddress(add);
			    	user_add.setUser(user);
			    	address.add(user_add);}
			    user.setAddress(address);
		   }
		   else {
			   Address a=new Address();
			   a.setUser(user);
			   String arr[]=useraddressservice.deleteAddress(a);
			   for (String add : arr) {
				   System.out.println("addresss:"+add);
			    	Address user_add=new Address();
			    	user_add.setAddress(add);
			    	user_add.setUser(user);
			    	address.add(user_add);}
			    user.setAddress(address);
		   }		  
		    user.setHobby(hobby);
		    user.setRole("user");
		    int status=1;//userservice.save(user);
		    if(status!=0) {
		    	
		    	 return "redirect:/";
		    }
		    else {
		    	return "register";
		    }
	   }
	  
	   /*this maping is use to get user information by user id*/
	   @PostMapping(value = "/userbyid")
		public @ResponseBody User getUser(	@RequestParam("id") int id) {
		   User user= userservice.getUserById(id); 
		   List<Address> list=user.getAddress();
		   int size=list.size();
		   String arr[]=new String[size];
		   int i=0;
		   Iterator<Address> itr2=list.iterator();    
	       while(itr2.hasNext())  
	       {  
	    	  
	    	   Address a=itr2.next();
	    	   arr[i]=a.getAddress();
	    	   i++;
			   
		   }
	       user.setAdd(arr);
		   return user;
		}

	   /*this mapping is use to check user is exits or not at register time*/
	   @PostMapping(value = "/checkemail")
		public @ResponseBody int checkUser(	@RequestParam("email") String email) {  
		   
		   int result=0;
		   List<User> listUser = userservice.getAllUser();    
		   for (User u : listUser) {
				 if(u.getEmail().equals(email)) {
					 	result=1;
					 }
				 }		 
		   return result;
	    	
		}  
	   
	   /*this method is use to count total number of address for particular user*/
	   @PostMapping(value = "/checkaddress")
	 		public @ResponseBody int checkAddress(	@RequestParam("id") int id) {
	 		   
			   User user= userservice.getUserById(id); 
			   List<Address> list=user.getAddress();
			   int size=list.size();
			   return size;
	 	    	
	 	}  
	   
	   /*this mapping is use to get all user from database*/
	   @RequestMapping(value = "/displayuser", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody List<User> listUser() {
	    	 List<User> listUser = userservice.getAllUser();    	
		     return listUser;
		} 
	   
	   
	   /*this method is use to display image*/
	   @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
		public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
			response.setContentType("image/jpeg");
			User u = userservice.getUserById(id);
			byte[] bytes =u.getData();
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
	   
	   /*this method is use to delete user by userid*/
	   @RequestMapping(value = "/userdelete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	 		public String deleteUser(	@PathVariable("id") int id) {
	 	      userservice.deleteUser(id); 	    	
	 		   return "showuser";
	 	}   
	   
	   /*this mapping is use to change user current password to new password*/
	   @RequestMapping(value="/changepassword", method = RequestMethod.POST)
	   public String password(@ModelAttribute("user") User user) {
		   int status=userservice.changepassword(user);
		    if(status!=0) {
		    	 return "redirect:/";
		    }
		    else {
		    	return "foregetpassword";
		    }
		
	   
	   }
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	    
	   
	   
}
