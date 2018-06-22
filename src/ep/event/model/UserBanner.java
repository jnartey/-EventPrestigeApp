package ep.event.model;

import java.sql.Timestamp;

public class UserBanner {
	private String slug;
	private Integer banner_id;
	private Integer user_id;
	private String title;
	private String description;
	private String image;
	private Timestamp date_created;
	private Timestamp date_modified;
	/**
	 * @param slug
	 * @param banner_id
	 * @param user_id
	 * @param title
	 * @param description
	 * @param image
	 * @param date_created
	 * @param date_modified
	 */
	public UserBanner(String slug, Integer banner_id, Integer user_id, String title, String description, String image,
			Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.banner_id = banner_id;
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	/**
	 * 
	 */
	public UserBanner() {
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
	 * @return the banner_id
	 */
	public Integer getBanner_id() {
		return banner_id;
	}
	/**
	 * @param banner_id the banner_id to set
	 */
	public void setBanner_id(Integer banner_id) {
		this.banner_id = banner_id;
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
		UserBanner other = (UserBanner) obj;
		if (banner_id == null) {
			if (other.banner_id != null)
				return false;
		} else if (!banner_id.equals(other.banner_id))
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
}
