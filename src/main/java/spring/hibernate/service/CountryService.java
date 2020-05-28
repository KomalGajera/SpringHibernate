package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.Country;

public interface CountryService {

	public int save(Country country);
	 
    public List<Country> getAllCountry();
 
    public int deleteCountry(int countryid);    
 
    public Country getCountry(int countryid);
    
    public Country getCountryByName(String countryname);

	


}
