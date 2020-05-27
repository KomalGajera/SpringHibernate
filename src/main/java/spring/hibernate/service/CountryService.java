package spring.hibernate.service;

import java.util.List;

import spring.hibernate.entitymodel.Country;

public interface CountryService {

	public int save(Country country);
	 
    public List<Country> getAllCountry();
 
    public void deleteCountry(int country_id);    
 
    public Country getCountry(int id);
    
    public Country getCountryByName(String countryname);

	


}
