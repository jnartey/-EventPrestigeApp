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

import ep.event.model.EventGallery;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class EventGalleryDAO {
	private Slugify slug = new Slugify();
	
	public List<EventGallery> getAllEventGallery() throws SQLException {
		EventGallery gallery = null;
		ResultSet result = null;
		List<EventGallery> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLEVENTGALLERIES);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				gallery = new EventGallery();
				gallery.setEvent_gallery_id(result.getInt(1));
				gallery.setSlug(result.getString(2));
				gallery.setEvent_id(result.getInt(3));
				gallery.setTitle(result.getString(4));
				gallery.setDescription(result.getString(5));
				gallery.setDate_created(result.getTimestamp(6));
				gallery.setDate_modified(result.getTimestamp(7));
				
				list.add(gallery);
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
	
	public EventGallery getGalleryByID(Integer galleryID) throws SQLException {
		EventGallery gallery = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETGALLERYBYID);) {
			stmt.setInt(1, galleryID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				gallery = new EventGallery();
				gallery.setEvent_gallery_id(result.getInt(1));
				gallery.setSlug(result.getString(2));
				gallery.setEvent_id(result.getInt(3));
				gallery.setTitle(result.getString(4));
				gallery.setDescription(result.getString(5));
				gallery.setDate_created(result.getTimestamp(6));
				gallery.setDate_modified(result.getTimestamp(7));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return gallery;
	}
	
	public EventGallery getGalleryBySlug(Integer slug) throws SQLException {
		EventGallery gallery = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETGALLERYBYSLUG);) {
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				gallery = new EventGallery();
				gallery.setEvent_gallery_id(result.getInt(1));
				gallery.setSlug(result.getString(2));
				gallery.setEvent_id(result.getInt(3));
				gallery.setTitle(result.getString(4));
				gallery.setDescription(result.getString(5));
				gallery.setDate_created(result.getTimestamp(6));
				gallery.setDate_modified(result.getTimestamp(7));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return gallery;
	}
	
	public List<EventGallery> getGalleryByEvent(Integer eventID) throws SQLException {
		EventGallery gallery = null;
		ResultSet result = null;
		List<EventGallery> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETGALLERYBYEVENT);) {
			stmt.setInt(1, eventID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				gallery = new EventGallery();
				gallery.setEvent_gallery_id(result.getInt(1));
				gallery.setSlug(result.getString(2));
				gallery.setEvent_id(result.getInt(3));
				gallery.setTitle(result.getString(4));
				gallery.setDescription(result.getString(5));
				gallery.setDate_created(result.getTimestamp(6));
				gallery.setDate_modified(result.getTimestamp(7));
				list.add(gallery);
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
	
	public List<EventGallery> getGalleryByEventByFirstImage(Integer eventID) throws SQLException {
		EventGallery gallery = null;
		ResultSet result = null;
		List<EventGallery> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETGALLERYBYEVENTBYFIRSTIMAGE);) {
			stmt.setInt(1, eventID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				gallery = new EventGallery();
				gallery.setEvent_gallery_id(result.getInt(1));
				gallery.setSlug(result.getString(2));
				gallery.setEvent_id(result.getInt(3));
				gallery.setTitle(result.getString(4));
				gallery.setDescription(result.getString(5));
				gallery.setDate_created(result.getTimestamp(6));
				gallery.setDate_modified(result.getTimestamp(7));
				gallery.setImage(result.getString(12));
				list.add(gallery);
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
	
	public Integer addEventGallery(EventGallery gallery) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEGALLERY, columnField);) {
			stmt.setString(1, slug.generateSlug(gallery.getTitle()) + "-" + gallery.getEvent_id());
			stmt.setInt(2, gallery.getEvent_id());
			stmt.setString(3, gallery.getTitle());
			stmt.setString(4, gallery.getDescription());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			stmt.executeUpdate();
			
			result = stmt.getGeneratedKeys();
			
			if(result.next()) {
				generatedID = result.getInt(1);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}finally {
			if(result != null) {
				result.close();
			}
		}
		
		return generatedID;
	}
	
	public Boolean updateEventGallery(EventGallery gallery) throws SQLException {
		Boolean isUpdated = false;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.UPDATEGALLERY);) {
			stmt.setString(1, slug.generateSlug(gallery.getTitle()) + "-" + gallery.getEvent_id());
			stmt.setString(2, gallery.getTitle());
			stmt.setString(3, gallery.getDescription());
			stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(5, gallery.getEvent_gallery_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}
		
		return isUpdated;
	}
	
	public Boolean deleteEventGallery(Integer galleryID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEGALLERY);
			stmt.setInt(1, galleryID);
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
