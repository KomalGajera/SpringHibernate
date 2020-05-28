package spring.hibernate.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.dao.UserAddressDao;
import spring.hibernate.entitymodel.Address;

@Service("useraddressService")
@Transactional
public class UserAddressServiceImpl implements UserAddressService {
	
	
	private static final Logger LOGGER = Logger.getLogger(UserAddressServiceImpl.class);
	
	@Autowired
    private UserAddressDao dao;

	@Override
	public String[] deleteAddress(Address address) {
		
		// TODO Auto-generated method stub
		return dao.deleteAddress(address);
	}

}
