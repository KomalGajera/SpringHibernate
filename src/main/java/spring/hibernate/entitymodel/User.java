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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="user")
public class User {
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	@Column(name = "fname", nullable = false)
	@Size(max = 20, min = 3, message = "enter maximum 3 character..")
	@NotNull
	@Pattern(regexp="^[a-zA-Z]{3,15}$",message = "enter only character")
	private String fname;
	
	@Column(name = "lname", nullable = false)
	@Size(max = 20, min = 3, message = "enter maximum 3 character..")
	@NotNull
	private String  lname;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "data")
	private byte[] data;
	
    @Column(name = "email", nullable = false)
    @Email(message="please enter valid email address..")
	private String  email;
    
	@Column(name = "number", nullable = false)
	//@Size(min=10,max=10,message = "please enter valid number")	
	private long number;
    
	@Column(name = "password", nullable = false)
	@Pattern(regexp="^([a-zA-Z0-9@*#]{8,15})$",message = "enter only character")
	private String  password;
	
	@Column(name = "dob", nullable = false)
	//@PastOrPresent(message = "not future date allow..")
	private String dob;
	
	@Column(name = "gender", nullable = false)
	private String  gender;
	
	@Column(name = "hobby", nullable = false)
	private String hobby;
	
	@Transient
	private String[] user_hobby;
	
	@Transient
	private String[] add;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER) 	
	private List<Address> address;  
		
	
	@Column(name = "country", nullable = false)
	@NotNull(message = "please select country..")
	private String country;
	 
	
	@Column(name = "state", nullable = false)
	@NotNull(message = "please select state..")
	private String state;
	 
	
	 
	@Column(name = "role", nullable = false)
	private String role;
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



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
		return data;
	}



	public void setData(byte[] data) {
		this.data = data;
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



	public String[] getUser_hobby() {
		return user_hobby;
	}



	public void setUser_hobby(String[] user_hobby) {
		this.user_hobby = user_hobby;
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
		return add;
	}



	public void setAdd(String[] add) {
		this.add = add;
	}
}
