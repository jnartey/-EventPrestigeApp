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

import ep.event.model.UserBanner;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class UserBannerDAO {
	private Slugify slug = new Slugify();
	
	public List<UserBanner> getAllBanners() throws SQLException {
		UserBanner banner = null;
		ResultSet result = null;
		List<UserBanner> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLBANNERS);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				banner = new UserBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setUser_id(result.getInt(3));
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
	
	public UserBanner getBannerByID(Integer bannerID) throws SQLException {
		UserBanner banner = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETBANNERBYID);) {
			stmt.setInt(1, bannerID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				banner = new UserBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setUser_id(result.getInt(3));
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
	
	public UserBanner getBannerBySlug(Integer slug) throws SQLException {
		UserBanner banner = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETBANNERBYSLUG);) {
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				banner = new UserBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setUser_id(result.getInt(3));
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
	
	public List<UserBanner> getAllBannersByUser(Integer userID) throws SQLException {
		UserBanner banner = null;
		ResultSet result = null;
		List<UserBanner> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETBANNERSBYUSER);) {
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				banner = new UserBanner();
				banner.setBanner_id(result.getInt(1));
				banner.setSlug(result.getString(2));
				banner.setUser_id(result.getInt(3));
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
	
	public Integer addBanner(UserBanner banner) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEBANNER, columnField);) {
			stmt.setString(1, slug.generateSlug(banner.getTitle()) + "-" + banner.getUser_id());
			stmt.setInt(2, banner.getUser_id());
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
		}finally {
			if(result != null) {
				result.close();
			}
		}
		
		return generatedID;
	}
	
	public Boolean updateBanner(UserBanner banner) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEBANNER);
			stmt.setString(1, slug.generateSlug(banner.getTitle()) + "-" + banner.getUser_id());
			stmt.setString(2, banner.getTitle());
			stmt.setString(3, banner.getDescription());
			stmt.setString(4, banner.getImage());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(6, banner.getBanner_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
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
		
		return isUpdated;
	}
	
	public Boolean deleteBanner(Integer bannerID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEBANNER);
			stmt.setInt(1, bannerID);
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
