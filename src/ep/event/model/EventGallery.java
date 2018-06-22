/**
 * 
 */
package ep.event.model;

import java.sql.Timestamp;

/**
 * @author Jacob Nartey
 *
 */
public class EventGallery {
	private String slug;
	private Integer event_gallery_id;
	private Integer event_id;
	private String title;
	private String description;
	private Timestamp date_created;
	private Timestamp date_modified;
	private String image;
	
	/**
	 * @param slug
	 * @param event_gallery_id
	 * @param event_id
	 * @param title
	 * @param description
	 * @param date_created
	 * @param date_modified
	 */
	public EventGallery(String slug, Integer event_gallery_id, Integer event_id, String title, String description,
			Timestamp date_created, Timestamp date_modified) {
		super();
		this.slug = slug;
		this.event_gallery_id = event_gallery_id;
		this.event_id = event_id;
		this.title = title;
		this.description = description;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	/**
	 * 
	 */
	public EventGallery() {
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
	
}
