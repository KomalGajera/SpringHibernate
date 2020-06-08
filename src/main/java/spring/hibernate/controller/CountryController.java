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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.hibernate.entitymodel.Country;
import spring.hibernate.service.CountryService;

@Controller
@Validated
public class CountryController {
	
	  private static final Logger LOGGER = Logger.getLogger(CountryController.class);
	  
	  static{        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        System.setProperty("current.date", dateFormat.format(new Date()));
	    }
	
	   @Autowired  
	   CountryService countryservice;
	 
	 /*this mapping is use to show country page*/
	   @GetMapping("/addcountry")
	   public String addcountry(Model model) {
		   LOGGER.info("you are map in country page...");
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
		public String deleteCountry(@PathVariable("country_id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int countryid,Model model) {
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
	 		public @ResponseBody Country countryupdate(	@RequestParam("id")  @Positive(message="negative number is not allow")  @Min(value = 1, message = "id must be greater than or equal to 1") int countryid) {		   
		   		Country country=countryservice.getCountry(countryid);
				return country;
	 		   
	   }
	   

}
