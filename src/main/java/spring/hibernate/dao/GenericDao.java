package spring.hibernate.dao;

import java.util.List;

public interface GenericDao<T> {
	

	    int save(T t);

	    void delete(Class<T> cls, int id);
	    
	    public T getbyid(Class<T> cls, int id);

	    public List< T > findAll(Class<T> cls);

	    int update(T t);  

}
