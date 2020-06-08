package spring.hibernate.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.hibernate.entitymodel.Address;

@Repository("useraddressDao")
@SuppressWarnings({"rawtypes","unchecked"})
public class UserAddressDaoImpl implements UserAddressDao {
	
	
	private static final Logger LOGGER = Logger.getLogger(UserAddressDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }	

	@Override
	public String[] deleteAddress(Address add) {
		// TODO Auto-generated method stub
		String address[]=add.getUser().getAdd();
		
		ArrayList<String> newaddress=new ArrayList<String>();
		Query query = getSession().createQuery("SELECT a.userAddress FROM Address a WHERE user_id=:user_id");
		query.setParameter("user_id", add.getUser().getUserId());
		ArrayList<String>  oldaddress= (ArrayList<String>) query.list();
		
		for (int count = 0; count < address.length;  count++) {
			newaddress.add(address[count]);				
		}
		  ArrayList<String> removeaddress=new ArrayList<String>(oldaddress); 
		  removeaddress.retainAll(newaddress);
		  
    	  oldaddress.removeAll(removeaddress); 
    	  newaddress.removeAll(removeaddress);    
    	  
    	int oldsize=oldaddress.size();
    	int newsize=newaddress.size();
    	
    	LOGGER.info("remaining old address size  ::::"+oldaddress.size());
		LOGGER.info("remaining new address size  ::::"+newaddress.size());
    	
    	while(oldsize!=0 && newsize!=0) {
    		String old=oldaddress.get(0);
    		String newadd=newaddress.get(0);
    		LOGGER.info("\n\n\n\n update time");
    		Query find = getSession().createQuery("SELECT a.addressId FROM Address a WHERE a.userAddress = :address and user_id=:user_id ");
			find.setParameter("address", old);
			find.setParameter("user_id",add.getUser().getUserId());
			int addressid=(int) find.list().get(0);
			Query update = getSession().createQuery("update userAddress a set a.userAddress=:address where a.addressId=:id");
			update.setParameter("address", newadd);
			update.setParameter("id", addressid);
			update.executeUpdate();
			oldaddress.remove(0);newaddress.remove(0);
			LOGGER.info("remaining old address::::"+oldaddress.size());
			LOGGER.info("remaining new address::::"+newaddress.size());
			newsize--;oldsize--;
    		
    		
    	}

		for (String s : oldaddress) {
			  LOGGER.info("\n\n\n delete records is:"+s);
			  Query query1 = getSession().createQuery("from Address where Address = :address and user_id=:user_id ");
			  query1.setParameter("address", s);
			  query1.setParameter("user_id", add.getUser().getUserId());
			  Address oldadd=(Address) query1.list().get(0);			  
			  getSession().delete(oldadd);}
		
		String arr[]=new String[newaddress.size()];
		int count=0;
		for (String s1 : newaddress) {
			LOGGER.info("\n\n\n\ninsert records:::"+s1);
			  arr[count]=s1;
			  count++;}
		return arr;
	}

}
