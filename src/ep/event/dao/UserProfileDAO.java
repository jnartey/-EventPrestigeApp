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

import ep.event.model.UserProfile;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class UserProfileDAO {
	private Slugify slug = new Slugify();
	
	public List<UserProfile> getAllUserProfiles() throws SQLException {
		UserProfile profile = null;
		ResultSet result = null;
		List<UserProfile> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLUSERPROFILES);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				profile = new UserProfile();
				profile.setUser_profile_id(result.getInt(1));
				profile.setSlug(result.getString(2));
				profile.setUser_id(result.getInt(3));
				profile.setTitle(result.getString(4));
				profile.setDescription(result.getString(5));
				profile.setImage(result.getString(6));
				profile.setDate_created(result.getTimestamp(7));
				profile.setDate_modified(result.getTimestamp(8));
				list.add(profile);
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
	
	public List<UserProfile> getAllProfileByUser(Integer userID) throws SQLException {
		UserProfile profile = null;
		ResultSet result = null;
		List<UserProfile> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETPROFILESBYUSER);) {
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				profile = new UserProfile();
				profile.setUser_profile_id(result.getInt(1));
				profile.setSlug(result.getString(2));
				profile.setUser_id(result.getInt(3));
				profile.setTitle(result.getString(4));
				profile.setDescription(result.getString(5));
				profile.setImage(result.getString(6));
				profile.setDate_created(result.getTimestamp(7));
				profile.setDate_modified(result.getTimestamp(8));
				list.add(profile);
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
	
	public UserProfile getUserProfileByID(Integer profileID) throws SQLException {
		UserProfile profile = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERPROFILEBYID);) {
			stmt.setInt(1, profileID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				profile = new UserProfile();
				profile.setUser_profile_id(result.getInt(1));
				profile.setSlug(result.getString(2));
				profile.setUser_id(result.getInt(3));
				profile.setTitle(result.getString(4));
				profile.setDescription(result.getString(5));
				profile.setImage(result.getString(6));
				profile.setDate_created(result.getTimestamp(7));
				profile.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return profile;
	}
	
	public UserProfile getUserProfileBySlug(Integer slug) throws SQLException {
		UserProfile profile = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERPROFILEBYSLUG);) {
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				profile = new UserProfile();
				profile.setUser_profile_id(result.getInt(1));
				profile.setSlug(result.getString(2));
				profile.setUser_id(result.getInt(3));
				profile.setTitle(result.getString(4));
				profile.setDescription(result.getString(5));
				profile.setImage(result.getString(6));
				profile.setDate_created(result.getTimestamp(7));
				profile.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return profile;
	}
	
	public Integer addUserProfile(UserProfile profile) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEUSERPROFILE, columnField);) {
			stmt.setString(1, slug.generateSlug(profile.getTitle()) + "-" + profile.getUser_id());
			stmt.setInt(2, profile.getUser_id());
			stmt.setString(3, profile.getTitle());
			stmt.setString(4, profile.getDescription());
			stmt.setString(5, profile.getImage());
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
	
	public Boolean updateUserProfile(UserProfile profile) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEUSERPROFILE);
			stmt.setString(1, slug.generateSlug(profile.getTitle()) + "-" + profile.getUser_id());
			stmt.setString(2, profile.getTitle());
			stmt.setString(3, profile.getDescription());
			stmt.setString(4, profile.getImage());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(6, profile.getUser_profile_id());
			
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
	
	public Boolean deleteUserProfile(Integer profileID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEUSERPROFILE);
			stmt.setInt(1, profileID);
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
