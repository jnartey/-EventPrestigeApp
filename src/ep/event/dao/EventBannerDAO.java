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

import ep.event.model.EventBanner;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class EventBannerDAO {
	private Slugify slug = new Slugify();
	
	public List<EventBanner> getAllBanners() throws SQLException {
		EventBanner banner = null;
		ResultSet result = null;
		List<EventBanner> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLEVENTBANNERS)) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				banner = new EventBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setEvent_id(result.getInt(3));
				banner.setTitle(result.getString(4));
				banner.setDescription(result.getString(5));
				banner.setImage(result.getString(6));
				banner.setDate_created(result.getTimestamp(7));
				banner.setDate_modified(result.getTimestamp(8));
				list.add(banner);
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
	
	public EventBanner getBannerByID(Integer bannerID) throws SQLException {
		EventBanner banner = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETEVENTBANNERBYID)) {
			stmt.setInt(1, bannerID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				banner = new EventBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setEvent_id(result.getInt(3));
				banner.setTitle(result.getString(4));
				banner.setDescription(result.getString(5));
				banner.setImage(result.getString(6));
				banner.setDate_created(result.getTimestamp(7));
				banner.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return banner;
	}
	
	public EventBanner getBannerBySlug(Integer slug) throws SQLException {
		EventBanner banner = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETEVENTBANNERBYSLUG)) {
			
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				banner = new EventBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setEvent_id(result.getInt(3));
				banner.setTitle(result.getString(4));
				banner.setDescription(result.getString(5));
				banner.setImage(result.getString(6));
				banner.setDate_created(result.getTimestamp(7));
				banner.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return banner;
	}
	
	public List<EventBanner> getAllBannersByEvent(Integer eventID) throws SQLException {
		EventBanner banner = null;
		ResultSet result = null;
		List<EventBanner> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETBANNERSBYEVENT)) {
			stmt.setInt(1, eventID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				banner = new EventBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setEvent_id(result.getInt(3));
				banner.setTitle(result.getString(4));
				banner.setDescription(result.getString(5));
				banner.setImage(result.getString(6));
				banner.setDate_created(result.getTimestamp(7));
				banner.setDate_modified(result.getTimestamp(8));
				list.add(banner);
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
	
	public Integer addBanner(EventBanner banner) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEEVENTBANNER, columnField);) {
			stmt.setString(1, slug.generateSlug(banner.getTitle()) + "-" + banner.getEvent_id());
			stmt.setInt(2, banner.getEvent_id());
			stmt.setString(3, banner.getTitle());
			stmt.setString(4, banner.getDescription());
			stmt.setString(5, banner.getImage());
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
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
	
	public Boolean updateBanner(EventBanner banner) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEEVENTBANNER);
			stmt.setString(1, slug.generateSlug(banner.getTitle()) + "-" + banner.getEvent_id());
			stmt.setString(2, banner.getTitle());
			stmt.setString(3, banner.getDescription());
			stmt.setString(4, banner.getImage());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(6, banner.getBanner_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}finally {
			if(stmt!=null ) {
				stmt.close();
				
			}
			if(conn !=null) {
				conn.close();
			}
		}
		
		return isUpdated;
	}
	
	public Boolean deleteBanner(Integer bannerID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEEVENTBANNER);
			stmt.setInt(1, bannerID);
			stmt.executeUpdate();
			isDeleted = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}finally {
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
