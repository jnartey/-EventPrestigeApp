/**
 * 
 */
package ep.event.model;

import java.sql.Timestamp;

/**
 * @author Jacob Nartey
 *
 */
public class EventImage {
	private String slug;
	private Integer event_image_id;
	private Integer event_gallery_id;
	private String title;
	private String image;
	private Timestamp date_created;
	private Timestamp date_modified;
	/**
	 * @param slug
	 * @param event_image_id
	 * @param event_gallery_id
	 * @param title
	 * @param image
	 * @param date_created
	 * @param date_modified
	 */
	public EventImage(String slug, Integer event_image_id, Integer event_gallery_id, String title, String image,
			Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.event_image_id = event_image_id;
		this.event_gallery_id = event_gallery_id;
		this.title = title;
		this.image = image;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	/**
	 * 
	 */
	public EventImage() {
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
	 * @return the event_image_id
	 */
	public Integer getEvent_image_id() {
		return event_image_id;
	}
	/**
	 * @param event_image_id the event_image_id to set
	 */
	public void setEvent_image_id(Integer event_image_id) {
		this.event_image_id = event_image_id;
	}
	/**
	 * @return the event_gallery_id
	 */
	public Integer getEvent_gallery_id() {
		return event_gallery_id;
	}
	/**
	 * @param event_gallery_id the event_gallery_id to set
	 */
	public void setEvent_gallery_id(Integer event_gallery_id) {
		this.event_gallery_id = event_gallery_id;
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
		EventImage other = (EventImage) obj;
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
		if (event_gallery_id == null) {
			if (other.event_gallery_id != null)
				return false;
		} else if (!event_gallery_id.equals(other.event_gallery_id))
			return false;
		if (event_image_id == null) {
			if (other.event_image_id != null)
				return false;
		} else if (!event_image_id.equals(other.event_image_id))
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
