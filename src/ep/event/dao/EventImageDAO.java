/**
 * 
 */
package ep.event.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ep.event.model.EventImage;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class EventImageDAO {
	private Slugify slug = new Slugify();
	
	public List<EventImage> getAllEventImage() throws SQLException {
		EventImage image = null;
		ResultSet result = null;
		List<EventImage> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLEVENTIMAGES);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				image = new EventImage();
				image.setEvent_image_id(result.getInt(1));
				image.setSlug(result.getString(2));
				image.setEvent_gallery_id(result.getInt(3));
				image.setTitle(result.getString(4));
				image.setImage(result.getString(5));
				image.setDate_created(result.getTimestamp(6));
				image.setDate_modified(result.getTimestamp(7));
				
				list.add(image);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return list;
	}
	
	public EventImage getImageByID(Integer imageID) throws SQLException {
		EventImage image = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETIMAGEBYID);) {
			stmt.setInt(1, imageID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				image = new EventImage();
				image.setEvent_image_id(result.getInt(1));
				image.setSlug(result.getString(2));
				image.setEvent_gallery_id(result.getInt(3));
				image.setTitle(result.getString(4));
				image.setImage(result.getString(5));
				image.setDate_created(result.getTimestamp(6));
				image.setDate_modified(result.getTimestamp(7));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return image;
	}
	
	public EventImage getImageBySlug(Integer slug) throws SQLException {
		EventImage image = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETIMAGEBYSLUG);) {
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				image = new EventImage();
				image.setEvent_image_id(result.getInt(1));
				image.setSlug(result.getString(2));
				image.setEvent_gallery_id(result.getInt(3));
				image.setTitle(result.getString(4));
				image.setImage(result.getString(5));
				image.setDate_created(result.getTimestamp(6));
				image.setDate_modified(result.getTimestamp(7));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return image;
	}
	
	public List<EventImage> getAllEventImageByGallery(Integer eventGalleryId) throws SQLException {
		EventImage image = null;
		ResultSet result = null;
		List<EventImage> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETIMAGEBYGALLERY);) {
			stmt.setInt(1, eventGalleryId);
			result = stmt.executeQuery();
			
			while(result.next()) {
				image = new EventImage();
				image.setEvent_image_id(result.getInt(1));
				image.setSlug(result.getString(2));
				image.setEvent_gallery_id(result.getInt(3));
				image.setTitle(result.getString(4));
				image.setImage(result.getString(5));
				image.setDate_created(result.getTimestamp(6));
				image.setDate_modified(result.getTimestamp(7));
				list.add(image);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return list;
	}
	
	public boolean checkIfImagesExist(Integer eventGalleryId) throws SQLException {
		ResultSet result = null;
		boolean isExisting = false;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETIMAGEBYGALLERY)) {
			stmt.setInt(1, eventGalleryId);
			result = stmt.executeQuery();
						
			if(result.next()) {
				isExisting = true;
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return isExisting;
	}
	
	public Integer addEventImage(EventImage image) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEIMAGE, columnField);) {
			stmt.setString(1, slug.generateSlug(image.getTitle()) + "-" + image.getEvent_gallery_id());
			stmt.setInt(2, image.getEvent_gallery_id());
			stmt.setString(3, image.getTitle());
			stmt.setString(4, image.getImage());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			stmt.executeUpdate();
			
			result = stmt.getGeneratedKeys();
			
			if(result.next()) {
				generatedID = result.getInt(1);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return generatedID;
	}
	
	public Boolean updateEventImage(EventImage image) throws SQLException {
		Boolean isUpdated = false;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.UPDATEIMAGE);) {
			stmt.setString(1, slug.generateSlug(image.getTitle()) + "-" + image.getEvent_gallery_id());
			stmt.setString(2, image.getTitle());
			stmt.setString(3, image.getImage());
			stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(5, image.getEvent_image_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}
		
		return isUpdated;
	}
	
	public Boolean deleteEventImage(Integer imageID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEIMAGE);
			stmt.setInt(1, imageID);
			stmt.executeUpdate();
			isDeleted = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(stmt!=null ) {
				stmt.close();
				
			}
			if(conn !=null) {
				conn.close();
			}
		}
		
		return isDeleted;
	}
}
