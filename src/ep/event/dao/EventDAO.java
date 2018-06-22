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

import ep.event.model.Event;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */
public class EventDAO {
	private Slugify slug = new Slugify();
	
	public List<Event> getAllEvents() throws SQLException{
		Event event = null;
		ResultSet result = null;
		List<Event> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLEVENTS)) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				event = new Event();
				event.setEvent_id(result.getInt(1));
				event.setSlug(result.getString(2));
				event.setUser_id(result.getInt(3));
				event.setUser_full_name(result.getString(4));
				event.setUser_email(result.getString(5));
				event.setUser_phone(result.getString(6));
				event.setUser_role(result.getInt(7));
				event.setTitle(result.getString(8));
				event.setDescription(result.getString(9));
				event.setAddress(result.getString(10));
				event.setCountry(result.getString(11));
				event.setState(result.getString(12));
				event.setCity(result.getString(13));
				event.setStart_date(result.getTimestamp(14));
				event.setEnd_date(result.getTimestamp(15));
				event.setImage(result.getString(16));
				event.setFeature_event(result.getInt(17));
				event.setDate_created(result.getTimestamp(18));
				event.setDate_modified(result.getTimestamp(19));
				
				list.add(event);
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
	
	public Event getEventByID(Integer eventID) throws SQLException {
		Event event = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETEVENTBYID)) {
			stmt.setInt(1, eventID);
			result = stmt.executeQuery();
			
			while(result.next()) {
				event = new Event();
				event.setEvent_id(result.getInt(1));
				event.setSlug(result.getString(2));
				event.setUser_id(result.getInt(3));
				event.setTitle(result.getString(4));
				event.setDescription(result.getString(5));
				event.setAddress(result.getString(6));
				event.setCountry(result.getString(7));
				event.setState(result.getString(8));
				event.setCity(result.getString(9));
				event.setStart_date(result.getTimestamp(10));
				event.setEnd_date(result.getTimestamp(11));
				event.setImage(result.getString(12));
				event.setFeature_event(result.getInt(13));
				event.setDate_created(result.getTimestamp(14));
				event.setDate_modified(result.getTimestamp(15));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return event;
	}
	
	public Event getLatestEvent(Integer userID) throws SQLException {
		Event event = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETLASTESTEVENT)) {
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			
			if(result.next()) {
				event = new Event();
				event.setEvent_id(result.getInt(1));
				event.setSlug(result.getString(2));
				event.setUser_id(result.getInt(3));
				event.setTitle(result.getString(4));
				event.setDescription(result.getString(5));
				event.setAddress(result.getString(6));
				event.setCountry(result.getString(7));
				event.setState(result.getString(8));
				event.setCity(result.getString(9));
				event.setStart_date(result.getTimestamp(10));
				event.setEnd_date(result.getTimestamp(11));
				event.setImage(result.getString(12));
				event.setFeature_event(result.getInt(13));
				event.setDate_created(result.getTimestamp(14));
				event.setDate_modified(result.getTimestamp(15));
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return event;
	}
	
	public List<Event> getEventsByUser(Integer userID) throws SQLException {
		Event event = null;
		ResultSet result = null;
		List<Event> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETEVENTSBYUSER);) {
			stmt.setInt(1, userID);
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				event = new Event();
				event.setEvent_id(result.getInt(1));
				event.setSlug(result.getString(2));
				event.setUser_id(result.getInt(3));
				event.setTitle(result.getString(4));
				event.setDescription(result.getString(5));
				event.setAddress(result.getString(6));
				event.setCountry(result.getString(7));
				event.setState(result.getString(8));
				event.setCity(result.getString(9));
				event.setStart_date(result.getTimestamp(10));
				event.setEnd_date(result.getTimestamp(11));
				event.setImage(result.getString(12));
				event.setFeature_event(result.getInt(13));
				event.setDate_created(result.getTimestamp(14));
				event.setDate_modified(result.getTimestamp(15));
				list.add(event);
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
	
	public List<Event> getEventsByUserLimit(Integer userID, Integer limitRows) throws SQLException {
		Event event = null;
		ResultSet result = null;
		List<Event> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETEVENTSBYUSERLIMIT);) {
			stmt.setInt(1, userID);
			if(limitRows != null) {
				stmt.setInt(2, limitRows);
			}
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				event = new Event();
				event.setEvent_id(result.getInt(1));
				event.setSlug(result.getString(2));
				event.setUser_id(result.getInt(3));
				event.setTitle(result.getString(4));
				event.setDescription(result.getString(5));
				event.setAddress(result.getString(6));
				event.setCountry(result.getString(7));
				event.setState(result.getString(8));
				event.setCity(result.getString(9));
				event.setStart_date(result.getTimestamp(10));
				event.setEnd_date(result.getTimestamp(11));
				event.setImage(result.getString(12));
				event.setFeature_event(result.getInt(13));
				event.setDate_created(result.getTimestamp(14));
				event.setDate_modified(result.getTimestamp(15));
				list.add(event);
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
	
	public Integer addEvent(Event event) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEEVENT, columnField);) {
			stmt.setString(1, slug.generateSlug(event.getTitle()) + "-" + event.getUser_id());
			stmt.setInt(2, event.getUser_id());
			stmt.setString(3, event.getTitle());
			stmt.setString(4, event.getDescription());
			stmt.setString(5, event.getAddress());
			stmt.setString(6, event.getCountry());
			stmt.setString(7, event.getState());
			stmt.setString(8, event.getCity());
			stmt.setTimestamp(9, event.getStart_date());
			stmt.setTimestamp(10, event.getEnd_date());
			stmt.setString(11, event.getImage());
			stmt.setInt(12, event.getFeature_event());
			stmt.setTimestamp(13, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
			
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
	
	public Boolean updateEvent(Event event) throws SQLException {
		Boolean isUpdated = false;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.UPDATEEVENT);) {
			stmt.setString(1, slug.generateSlug(event.getTitle()) + "-" + event.getUser_id());
			stmt.setString(2, event.getTitle());
			stmt.setString(3, event.getDescription());
			stmt.setString(4, event.getAddress());
			stmt.setString(5, event.getCountry());
			stmt.setString(6, event.getState());
			stmt.setString(7, event.getCity());
			stmt.setTimestamp(8, event.getStart_date());
			stmt.setTimestamp(9, event.getEnd_date());
			stmt.setString(10, event.getImage());
			stmt.setInt(11, event.getFeature_event());
			stmt.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(13, event.getEvent_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}
		
		return isUpdated;
	}
	
	public Boolean deleteEvent(Integer eventID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEEVENT);
			stmt.setInt(1, eventID);
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
