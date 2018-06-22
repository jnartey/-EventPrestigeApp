/**
 * 
 */
package ep.event.model;

import java.sql.Timestamp;

/**
 * @author Jacob Nartey
 * Event model
 */
public class Event {
	private String slug;
	private Integer event_id;
	private Integer user_id;
	private String title;
	private String description;
	private Timestamp start_date;
	private Timestamp end_date;
	private String address;
	private String country;
	private String state;
	private String city;
	private String image;
	private Integer feature_event;
	private String user_full_name;
	private String user_email;
	private String user_phone;
	private Integer user_role;
	private Timestamp date_created;
	private Timestamp date_modified;
	/**
	 * @param slug
	 * @param event_id
	 * @param user_id
	 * @param title
	 * @param description
	 * @param start_date
	 * @param end_date
	 * @param address
	 * @param country
	 * @param state
	 * @param city
	 * @param image
	 * @param feature_event
	 * @param user_full_name
	 * @param user_email
	 * @param user_phone
	 * @param user_role
	 * @param date_created
	 * @param date_modified
	 */
	public Event(String slug, Integer event_id, Integer user_id, String title, String description, Timestamp start_date,
			Timestamp end_date, String address, String country, String state, String city, String image,
			Integer feature_event, String user_full_name, String user_email, String user_phone, Integer user_role,
			Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.event_id = event_id;
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.address = address;
		this.country = country;
		this.state = state;
		this.city = city;
		this.image = image;
		this.feature_event = feature_event;
		this.user_full_name = user_full_name;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_role = user_role;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	
	/**
	 * 
	 */
	public Event() {
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
	 * @return the event_id
	 */
	public Integer getEvent_id() {
		return event_id;
	}

	/**
	 * @param event_id the event_id to set
	 */
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the start_date
	 */
	public Timestamp getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public Timestamp getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
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
	 * @return the feature_event
	 */
	public Integer getFeature_event() {
		return feature_event;
	}

	/**
	 * @param feature_event the feature_event to set
	 */
	public void setFeature_event(Integer feature_event) {
		this.feature_event = feature_event;
	}

	/**
	 * @return the user_full_name
	 */
	public String getUser_full_name() {
		return user_full_name;
	}

	/**
	 * @param user_full_name the user_full_name to set
	 */
	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}

	/**
	 * @return the user_email
	 */
	public String getUser_email() {
		return user_email;
	}

	/**
	 * @param user_email the user_email to set
	 */
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	/**
	 * @return the user_phone
	 */
	public String getUser_phone() {
		return user_phone;
	}

	/**
	 * @param user_phone the user_phone to set
	 */
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
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

	/* (non-Javadoc)
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
		Event other = (Event) obj;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (event_id == null) {
			if (other.event_id != null)
				return false;
		} else if (!event_id.equals(other.event_id))
			return false;
		if (feature_event == null) {
			if (other.feature_event != null)
				return false;
		} else if (!feature_event.equals(other.feature_event))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (slug == null) {
			if (other.slug != null)
				return false;
		} else if (!slug.equals(other.slug))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_full_name == null) {
			if (other.user_full_name != null)
				return false;
		} else if (!user_full_name.equals(other.user_full_name))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_phone == null) {
			if (other.user_phone != null)
				return false;
		} else if (!user_phone.equals(other.user_phone))
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
		return "Event [slug=" + slug + ", event_id=" + event_id + ", user_id=" + user_id + ", title=" + title
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", address=" + address + ", country=" + country + ", state=" + state + ", city=" + city + ", image="
				+ image + ", feature_event=" + feature_event + ", user_full_name=" + user_full_name + ", user_email="
				+ user_email + ", user_phone=" + user_phone + ", user_role=" + user_role + ", date_created="
				+ date_created + ", date_modified=" + date_modified + "]";
	}
}
