package spring.hibernate.entitymodel;

import javax.persistence.Column;
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
	
    public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id", nullable = false)
    private int addressId;   
	
	@Length(max=17,message = "enter maximum 17 character..")
	@Column(name = "address", nullable = false)
    private String userAddress;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false)
	private Users user;
    
    public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
