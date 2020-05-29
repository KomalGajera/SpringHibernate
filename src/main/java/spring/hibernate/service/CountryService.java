package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.Country;

public interface CountryService {

	int save(Country country);
	 
    List<Country> getAllCountry();
 
    int deleteCountry(int countryid);    
 
    Country getCountry(int countryid);
    
    Country getCountryByName(String countryname);

	


}
