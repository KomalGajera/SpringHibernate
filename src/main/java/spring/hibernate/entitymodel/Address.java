package spring.hibernate.entitymodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity  
@Table(name="address") 
public class Address {
	
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private int id;   
	@Length(max=17,message = "enter maximum 17 character..")
    private String Address;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false)
	private User user;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}   

}
