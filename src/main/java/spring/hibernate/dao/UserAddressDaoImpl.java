package spring.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.hibernate.entitymodel.Address;

@Repository("useraddressDao")
@SuppressWarnings("rawtypes")
public class UserAddressDaoImpl implements UserAddressDao {
	
	
	private static final Logger LOGGER = Logger.getLogger(UserAddressDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
	

	@SuppressWarnings("unchecked")
	@Override
	public String[] deleteAddress(Address add) {
		// TODO Auto-generated method stub
		String address[]=add.getUser().getAdd();
		List<String> oldaddress=new ArrayList<String>(); 
		List<String> newaddress=new ArrayList<String>();
		Query query = getSession().createQuery("SELECT a.Address FROM Address a WHERE user_id=:user_id");
		query.setParameter("user_id", add.getUser().getId());
		oldaddress= (List<String>) query.list();
		
		for (int count = 0; count < address.length;  count++) {
			newaddress.add(address[count]);				
		}
		  List<String> oldaddress1=new ArrayList<String>(oldaddress); 
    	  oldaddress.removeAll(newaddress); 
    	  newaddress.removeAll(oldaddress1);    
    	  
    	int oldsize=oldaddress.size();
    	int newsize=newaddress.size();
    	
    	LOGGER.info("remaining old address size  ::::"+oldaddress.size());
		LOGGER.info("remaining new address size  ::::"+newaddress.size());
    	
    	while(oldsize!=0 && newsize!=0) {
    		String old=oldaddress.get(0);
    		String newadd=newaddress.get(0);
    		LOGGER.info("\n\n\n\n update time");
    		Query find = getSession().createQuery("SELECT a.id FROM Address a WHERE a.Address = :address and user_id=:user_id ");
			find.setParameter("address", old);
			find.setParameter("user_id",add.getUser().getId());
			int addressid=(int) find.list().get(0);
			Query update = getSession().createQuery("update Address a set a.Address=:address where a.id=:id");
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
			  query1.setParameter("user_id", add.getUser().getId());
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
