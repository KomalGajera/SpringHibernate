package spring.hibernate.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.dao.CountryDao;
import spring.hibernate.entitymodel.Country;

@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {

	private static final Logger logger = Logger.getLogger(CountryServiceImpl.class);
	
	@Autowired
    private CountryDao dao;
	

	@Override
	public int save(Country country) {
		// TODO Auto-generated method stub
		if(country.getCountry_id()==0)
		{
			int status=dao.save(country);
			return status;
		}
		else {
			int status=dao.update(country);
			return status;
		}		
	
	}

	@Override
	public List<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return dao.findAll(Country.class);
	}

	@Override
	public void deleteCountry(int country_id) {
		// TODO Auto-generated method stub	
		dao.delete(Country.class,country_id);
	}

	@Override
	public Country getCountry(int countryid) {
		// TODO Auto-generated method stub]		
		return dao.getbyid(Country.class,countryid);
		//return dao.getCountry(countryid);
	}

	@Override
	public Country getCountryByName(String countryname) {
		// TODO Auto-generated method stub
		return dao.getCountryByName(countryname);
	}

	
}
