package spring.hibernate.dao;


import spring.hibernate.entitymodel.Country;

public interface CountryDao extends GenericDao<Country> {


	public Country getCountryByName(String countryname);


}
