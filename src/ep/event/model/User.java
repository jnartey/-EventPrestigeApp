/**
 * Users model
 */
package ep.event.model;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Jacob Nartey
 *
 */
public class User {
	private String slug;
	private Integer user_id;
	private String full_name;
	private String email;
	private String password;
	private String new_password;
	private String phone;
	private String address;
	private Date dob;
	private String country;
	private String state;
	private String city;
	private String image;
	private Integer user_role;
	private Timestamp date_created;
	private Timestamp date_modified;
	
	

	/**
	 * @param slug
	 * @param user_id
	 * @param full_name
	 * @param email
	 * @param password
	 * @param new_password
	 * @param phone
	 * @param address
	 * @param dob
	 * @param country
	 * @param state
	 * @param city
	 * @param image
	 * @param user_role
	 * @param date_created
	 * @param date_modified
	 */
	public User(String slug, Integer user_id, String full_name, String email, String password, String new_password,
			String phone, String address, Date dob, String country, String state, String city, String image,
			Integer user_role, Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.user_id = user_id;
		this.full_name = full_name;
		this.email = email;
		this.password = password;
		this.new_password = new_password;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.country = country;
		this.state = state;
		this.city = city;
		this.image = image;
		this.user_role = user_role;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}


	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the new_password
	 */
	public String getNew_password() {
		return new_password;
	}

	/**
	 * @param new_password the new_password to set
	 */
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the user_role
	 */
	public Integer getUser_role() {
		return user_role;
	}

	/**
	 * @param user_role the user_role to set
	 */
	public void setUser_role(Integer user_role) {
		this.user_role = user_role;
	}

	/**
	 * @return the date_created
	 */
	public Timestamp getDate_created() {
		return date_created;
	}

	/**
	 * @param date_created the date_created to set
	 */
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

	/**
	 * @return the date_modified
	 */
	public Timestamp getDate_modified() {
		return date_modified;
	}

	/**
	 * @param date_modified the date_modified to set
	 */
	public void setDate_modified(Timestamp date_modified) {
		this.date_modified = date_modified;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (date_created == null) {
			if (other.date_created != null)
				return false;
		} else if (!date_created.equals(other.date_created))
			return false;
		if (date_modified == null) {
			if (other.date_modified != null)
				return false;
		} else if (!date_modified.equals(other.date_modified))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (new_password == null) {
			if (other.new_password != null)
				return false;
		} else if (!new_password.equals(other.new_password))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_role == null) {
			if (other.user_role != null)
				return false;
		} else if (!user_role.equals(other.user_role))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [slug=" + slug + ", user_id=" + user_id + ", full_name=" + full_name + ", email=" + email
				+ ", password=" + password + ", new_password=" + new_password + ", phone=" + phone + ", address="
				+ address + ", dob=" + dob + ", country=" + country + ", state=" + state + ", city=" + city + ", image="
				+ image + ", user_role=" + user_role + ", date_created=" + date_created + ", date_modified="
				+ date_modified + "]";
	}
	
}
