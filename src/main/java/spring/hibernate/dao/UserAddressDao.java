package spring.hibernate.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hibernate.entitymodel.Address;
import spring.hibernate.entitymodel.User;

@Service("useraddressService")
@Transactional
public interface UserAddressDao {

	public String[] deleteAddress(Address a);

}
