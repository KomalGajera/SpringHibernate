package spring.hibernate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.hibernate.entitymodel.Country;
import spring.hibernate.entitymodel.State;
import spring.hibernate.service.CountryService;
import spring.hibernate.service.StateService;

@Controller
@Validated
public class StateController {
	
	   private static final Logger LOGGER = Logger.getLogger(StateController.class);
	   
	   static{        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        System.setProperty("current.date", dateFormat.format(new Date()));
	    }
	
	   @Autowired  
	   CountryService countryservice;
	 
	   @Autowired  
	   StateService stateservice;	 
	   
	   /*this mapping is use to display state page details.*/
	   @GetMapping("/addstate")
	   public String addState() {
		   LOGGER.info("you are map in state page...");
		   return "addstate";
	   }   
	   
	   /*this mapping is use to update state old records to database*/
	   @PostMapping(value = "/stateupdate")
		public @ResponseBody State stateupdate(	@RequestParam("id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int stateid) {		   
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
		public String deleteState(	@PathVariable("state_id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int stateid,Model model) {
	       int status=stateservice.deleteState(stateid);
	    	if(status==1) {
	    		return "addstate";
	    	}else {
	    		 model.addAttribute("Delete", "there is some error of delete state");
				   return "addstate";
	    	}
		   
		}
	
	   

}
