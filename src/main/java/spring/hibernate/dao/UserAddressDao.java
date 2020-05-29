package spring.hibernate.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.entitymodel.Address;

@Service("useraddressService")
@Transactional
public interface UserAddressDao {

	String[] deleteAddress(Address address);

}
