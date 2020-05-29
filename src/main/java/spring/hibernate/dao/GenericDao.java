package spring.hibernate.dao;

import java.util.List;

public interface GenericDao<T> {
	

	    int save(T classname);

	    int delete(Class<T> cls, int classid);
	    
	     T getbyid(Class<T> cls, int classid);

	     List< T > findAll(Class<T> cls);

	    int update(T classname);  

}
