/**
 * 
 */
package ep.event.model;

import java.sql.Timestamp;

/**
 * @author Jacob Nartey
 *
 */
public class EventWishlist {
	private String slug;
	private Integer event_wishlist_id;
	private Integer event_id;
	private String title;
	private String description;
	private String image;
	private Timestamp date_created;
	private Timestamp date_modified;
	/**
	 * @param slug
	 * @param event_wishlist_id
	 * @param event_id
	 * @param title
	 * @param description
	 * @param image
	 * @param date_created
	 * @param date_modified
	 */
	public EventWishlist(String slug, Integer event_wishlist_id, Integer event_id, String title, String description,
			String image, Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.event_wishlist_id = event_wishlist_id;
		this.event_id = event_id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	/**
	 * 
	 */
	public EventWishlist() {
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
	 * @return the event_wishlist_id
	 */
	public Integer getEvent_wishlist_id() {
		return event_wishlist_id;
	}
	/**
	 * @param event_wishlist_id the event_wishlist_id to set
	 */
	public void setEvent_wishlist_id(Integer event_wishlist_id) {
		this.event_wishlist_id = event_wishlist_id;
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
		EventWishlist other = (EventWishlist) obj;
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
		if (event_id == null) {
			if (other.event_id != null)
				return false;
		} else if (!event_id.equals(other.event_id))
			return false;
		if (event_wishlist_id == null) {
			if (other.event_wishlist_id != null)
				return false;
		} else if (!event_wishlist_id.equals(other.event_wishlist_id))
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
		return true;
	}
}
