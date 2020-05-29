package spring.hibernate.dao;


import spring.hibernate.entitymodel.Country;

public interface CountryDao extends GenericDao<Country> {


	 Country getCountryByName(String countryname);


}
