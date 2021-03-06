package spring.hibernate.entitymodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="user")
public class Users {
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name = "id", nullable = false)
	private int userId;
	
	@Column(name = "fname", nullable = false)
	@Size(max = 20, min = 3, message = "{fname.size.invalid}")
	@NotNull
	@Pattern(regexp="^[a-zA-Z]{3,15}$",message = "{fname.pattern.invalid}")
	private String fname;
	
	@Column(name = "lname", nullable = false)
	@Size(max = 20, min = 3, message = "{fname.size.invalid}")
	@NotNull
	@Pattern(regexp="^[a-zA-Z]{3,15}$",message = "{fname.pattern.invalid}")
	private String  lname;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "data")
	private byte[] data;
	
    @Column(name = "email", nullable = false)
    @Email(message="email.pattern.invalid")
	private String  email;
    
	@Column(name = "number", nullable = false)	
	@Max(value = 10,message="{number.invalid}")
	@Min(value=10,message="{number.invalid}")
	private long number;
    
	@Column(name = "password", nullable = false)
	@Pattern(regexp="^([a-zA-Z0-9@*#]{8,15})$",message = "{password.pattern.invalid}")
	private String  password;
	
	@Column(name = "dob", nullable = false)
	@NotNull(message = "{dob.invalid}")
	private String dob;
	
	@Column(name = "gender", nullable = false)
	private String  gender;
	
	@Column(name = "hobby", nullable = false)
	private String hobby;
	
	@Transient
	private String[] userHobby;
	
	@Transient
	private String[] add;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER) 	
	@Valid
	private List<Address> address;  
		
	
	@Column(name = "country", nullable = false)
	@NotNull(message = "{country.invalid}")
	private String country;
	 
	
	@Column(name = "state", nullable = false)
	@NotNull(message = "{state.invalid}")
	private String state;
	 
	
	 
	@Column(name = "role", nullable = false)
	private String role="user";
	


	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public byte[] getData() {
		return data!=null?(byte[])data:new byte[] {};
	}



	public void setData(byte[] data) {
		this.data = data!=null?(byte[])data:new byte[] {};
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getNumber() {
		return number;
	}



	public void setNumber(long number) {
		this.number = number;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public String getHobby() {
		return hobby;
	}



	public void setHobby(String hobby) {
		this.hobby = hobby;
	}





	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String[] getUserHobby() {
		return userHobby!=null?(String[])userHobby:new String[] {};
	}



	public void setUserHobby(String[] userHobby) {
		this.userHobby = userHobby!=null?(String[])userHobby:new String[] {};
	}



	public List<Address> getAddress() {
		return address;
	}



	public void setAddress(List<Address> address) {
		this.address = address;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String[] getAdd() {
		return add!=null?(String[])add:new String[] {};
	}



	public void setAdd(String[] add) {
		this.add = add!=null?(String[])add:new String[] {};
	}
}
