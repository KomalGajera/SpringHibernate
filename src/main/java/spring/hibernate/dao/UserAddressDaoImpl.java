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
	
	
	private static final Logger logger = Logger.getLogger(UserAddressDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
	

	@SuppressWarnings("unchecked")
	@Override
	public String[] deleteAddress(Address a) {
		// TODO Auto-generated method stub
		String address[]=a.getUser().getAdd();
		List<String> oldaddress=new ArrayList<String>(); 
		List<String> newaddress=new ArrayList<String>();
		Query q = getSession().createQuery("SELECT a.Address FROM Address a WHERE user_id=:user_id");
		q.setParameter("user_id", a.getUser().getId());
		oldaddress= (List<String>) q.list();
		
		for (int i = 0; i < address.length;  i++) {
			newaddress.add(address[i]);				
		}
		  List<String> oldaddress1=new ArrayList<String>(oldaddress); 
    	  oldaddress.removeAll(newaddress); 
    	  newaddress.removeAll(oldaddress1);    
    	  
    	int oldsize=oldaddress.size();
    	int newsize=newaddress.size();
    	
    	logger.info("remaining old address size  ::::"+oldaddress.size());
		logger.info("remaining new address size  ::::"+newaddress.size());
    	
    	while(oldsize!=0 && newsize!=0) {
    		String old=oldaddress.get(0);
    		String New=newaddress.get(0);
    		logger.info("\n\n\n\n update time");
    		Query find = getSession().createQuery("SELECT a.id FROM Address a WHERE a.Address = :address and user_id=:user_id ");
			find.setParameter("address", old);
			find.setParameter("user_id",a.getUser().getId());
			int id=(int) find.list().get(0);
			Query update = getSession().createQuery("update Address a set a.Address=:address where a.id=:id");
			update.setParameter("address", New);
			update.setParameter("id", id);
			update.executeUpdate();
			oldaddress.remove(0);newaddress.remove(0);
			logger.info("remaining old address::::"+oldaddress.size());
			logger.info("remaining new address::::"+newaddress.size());
			newsize--;oldsize--;
    		
    		
    	}

		for (String s : oldaddress) {
			  logger.info("\n\n\n delete records is:"+s);
			  Query q1 = getSession().createQuery("from Address where Address = :address and user_id=:user_id ");
			  q1.setParameter("address", s);
			  q1.setParameter("user_id", a.getUser().getId());
			  Address oldadd=(Address) q1.list().get(0);			  
			  getSession().delete(oldadd);}
		
		String arr[]=new String[newaddress.size()];
		int i=0;
		for (String s1 : newaddress) {
			logger.info("\n\n\n\ninsert records:::"+s1);
			  arr[i]=s1;
			  i++;}
		return arr;
	}

}
