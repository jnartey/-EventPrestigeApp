/**
 * 
 */
package ep.event.utils;

/**
 * @author Jacob Nartey
 */
public class Slugify {
	public String generateSlug(String text) {
	    return text.replaceAll("[^A-Za-z0-9]+", "-").toLowerCase();
	}
}
