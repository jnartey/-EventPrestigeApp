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

import ep.event.model.EventWishlist;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class EventWishlistDAO {
	private Slugify slug = new Slugify();
	
	public List<EventWishlist> getAllWishList() throws SQLException{
		EventWishlist wishlist = null;
		ResultSet result = null;
		List<EventWishlist> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLEVENTWISHLISTS);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				wishlist = new EventWishlist();
				wishlist.setEvent_wishlist_id(result.getInt(1));
				wishlist.setSlug(result.getString(2));
				wishlist.setEvent_id(result.getInt(3));
				wishlist.setTitle(result.getString(4));
				wishlist.setDescription(result.getString(5));
				wishlist.setImage(result.getString(6));
				wishlist.setDate_created(result.getTimestamp(7));
				wishlist.setDate_modified(result.getTimestamp(8));
				list.add(wishlist);
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
	
	public EventWishlist getWishListByID(Integer wishlistID) throws SQLException{
		EventWishlist wishlist = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETWISHLISTBYID);) {
			stmt.setInt(1, wishlistID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				wishlist = new EventWishlist();
				wishlist.setEvent_wishlist_id(result.getInt(1));
				wishlist.setSlug(result.getString(2));
				wishlist.setEvent_id(result.getInt(3));
				wishlist.setTitle(result.getString(4));
				wishlist.setDescription(result.getString(5));
				wishlist.setImage(result.getString(6));
				wishlist.setDate_created(result.getTimestamp(7));
				wishlist.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return wishlist;
	}
	
	public EventWishlist getWishListBySlug(Integer slug) throws SQLException{
		EventWishlist wishlist = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETWISHLISTBYSLUG);) {
			stmt.setInt(1, slug);
			result = stmt.executeQuery();
			
			if(result.next()) {
				wishlist = new EventWishlist();
				wishlist.setEvent_wishlist_id(result.getInt(1));
				wishlist.setSlug(result.getString(2));
				wishlist.setEvent_id(result.getInt(3));
				wishlist.setTitle(result.getString(4));
				wishlist.setDescription(result.getString(5));
				wishlist.setImage(result.getString(6));
				wishlist.setDate_created(result.getTimestamp(7));
				wishlist.setDate_modified(result.getTimestamp(8));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return wishlist;
	}
	
	public List<EventWishlist> getAllWishListByEvent(Integer eventID) throws SQLException{
		EventWishlist wishlist = null;
		ResultSet result = null;
		List<EventWishlist> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETWISHLISTBYEVENT);) {
			stmt.setInt(1, eventID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				wishlist = new EventWishlist();
				wishlist.setEvent_wishlist_id(result.getInt(1));
				wishlist.setSlug(result.getString(2));
				wishlist.setEvent_id(result.getInt(3));
				wishlist.setTitle(result.getString(4));
				wishlist.setDescription(result.getString(5));
				wishlist.setImage(result.getString(6));
				wishlist.setDate_created(result.getTimestamp(7));
				wishlist.setDate_modified(result.getTimestamp(8));
				list.add(wishlist);
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
	
	public Integer addEventWishlist(EventWishlist wishlist) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEWISHLIST, columnField);) {
			stmt.setString(1, slug.generateSlug(wishlist.getTitle()) + "-" + wishlist.getEvent_id());
			stmt.setInt(2, wishlist.getEvent_id());
			stmt.setString(3, wishlist.getTitle());
			stmt.setString(4, wishlist.getDescription());
			stmt.setString(5, wishlist.getImage());
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
	
	public Boolean updateEventWishlist(EventWishlist wishlist) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEWISHLIST);
			stmt.setString(1, slug.generateSlug(wishlist.getTitle()) + "-" + wishlist.getEvent_id());
			stmt.setString(2, wishlist.getTitle());
			stmt.setString(3, wishlist.getDescription());
			stmt.setString(4, wishlist.getImage());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(6, wishlist.getEvent_wishlist_id());
			
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
	
	public Boolean deleteEventWishlist(Integer wishlistID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEWISHLIST);
			stmt.setInt(1, wishlistID);
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
