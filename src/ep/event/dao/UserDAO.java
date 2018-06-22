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

import ep.event.utils.BCrypt;

import ep.event.model.User;
import ep.event.utils.OracleQueries;
import ep.event.utils.Slugify;
import ep.event.utils.CreateFolder;
import ep.event.dao.OracleConnection;

/**
 * @author Jacob Nartey
 *
 */
public class UserDAO {
	
	//Workload value for BCrypt to generate random salt value for hashing
	private Slugify slug = new Slugify();
	private int strength = 12;
	
	//Get all the users
	public List<User> getAllUsers() throws SQLException{
		User user = null;
		ResultSet result = null;
		List<User> list = new ArrayList<>();
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETALLUSERS);) {
			result = stmt.executeQuery();
			
			while(result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setSlug(result.getString(2));
				user.setFull_name(result.getString(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setPhone(result.getString(6));
				user.setAddress(result.getString(7));
				user.setDob(result.getDate(8));
				user.setCountry(result.getString(9));
				user.setState(result.getString(10));
				user.setCity(result.getString(11));
				user.setImage(result.getString(12));
				user.setUser_role(result.getInt(13));
				user.setDate_created(result.getTimestamp(14));
				user.setDate_modified(result.getTimestamp(15));
				
				list.add(user);
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
	
	//Get user by id
	public User getUserByID(Integer userID) throws SQLException{
		User user = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERBYID);) {
			stmt.setInt(1, userID);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setSlug(result.getString(2));
				user.setFull_name(result.getString(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setPhone(result.getString(6));
				user.setAddress(result.getString(7));
				user.setDob(result.getDate(8));
				user.setCountry(result.getString(9));
				user.setState(result.getString(10));
				user.setCity(result.getString(11));
				user.setImage(result.getString(12));
				user.setUser_role(result.getInt(13));
				user.setDate_created(result.getTimestamp(14));
				user.setDate_modified(result.getTimestamp(15));
			}
			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return user;		
	}
	
	//Get user by slug
	public User getUserBySlug(String slug) throws SQLException{
		User user = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERBYSLUG);) {
			stmt.setString(1, slug);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setSlug(result.getString(2));
				user.setFull_name(result.getString(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setPhone(result.getString(6));
				user.setAddress(result.getString(7));
				user.setDob(result.getDate(8));
				user.setCountry(result.getString(9));
				user.setState(result.getString(10));
				user.setCity(result.getString(11));
				user.setImage(result.getString(12));
				user.setUser_role(result.getInt(13));
				user.setDate_created(result.getTimestamp(14));
				user.setDate_modified(result.getTimestamp(15));
			}
			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return user;		
	}
	
	//Get user by email
	public User getUserByEmail(String email) throws SQLException{
		User user = null;
		ResultSet result = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERBYEMAIL);) {
			stmt.setString(1, email);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setSlug(result.getString(2));
				user.setFull_name(result.getString(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setPhone(result.getString(6));
				user.setAddress(result.getString(7));
				user.setDob(result.getDate(8));
				user.setCountry(result.getString(9));
				user.setState(result.getString(10));
				user.setCity(result.getString(11));
				user.setImage(result.getString(12));
				user.setUser_role(result.getInt(13));
				user.setDate_created(result.getTimestamp(14));
				user.setDate_modified(result.getTimestamp(15));
			}
			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		} finally {
			if(result != null) {
				result.close();
			}
		}
		
		return user;		
	}
	
	//Login returns User object
	public User login(String email, String password) throws SQLException {
		User user = null;
		ResultSet result = null;
		
		try(Connection conn= OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERBYEMAIL);) {
			stmt.setString(1, email);
			result = stmt.executeQuery();
			
			if(result.next() && this.checkPassword(password, result.getString(5))) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setSlug(result.getString(2));
				user.setFull_name(result.getString(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setPhone(result.getString(6));
				user.setAddress(result.getString(7));
				user.setDob(result.getDate(8));
				user.setCountry(result.getString(9));
				user.setState(result.getString(10));
				user.setCity(result.getString(11));
				user.setImage(result.getString(12));
				user.setUser_role(result.getInt(13));
				user.setDate_created(result.getTimestamp(14));
				user.setDate_modified(result.getTimestamp(15));
			}	
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}finally {
			if(result != null) {
				result.close();
			}
		}
		
		return user;
	}
	
	//Validate login
	public boolean validateLogin(String email, String password) throws SQLException {
		ResultSet result = null;
		boolean isValid = false;
		
		try(Connection conn= OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.GETUSERBYEMAIL);) {
			stmt.setString(1, email);
			result = stmt.executeQuery();
			
			if(result.next() && this.checkPassword(password, result.getString(5))) {
				isValid = true;
			}	
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.getMessage();
		}finally {
			if(result != null) {
				result.close();
			}
		}
		
		return isValid;
	}
	
	//Add user to database
	public Integer registerUser(User user) throws SQLException {
		Integer generatedID = null;
		ResultSet result = null;
		String [] columnField = {"id"};
		CreateFolder folder = null;
		java.sql.Date sqlDOB = null;
		
		try(Connection conn = OracleConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(OracleQueries.SAVEUSER, columnField);) {
			
			//Converting to date of birth to sql date
			sqlDOB = new java.sql.Date(user.getDob().getTime());
			
			stmt.setString(1, slug.generateSlug(user.getEmail()));
			stmt.setString(2, user.getFull_name());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, this.hashPassword(user.getPassword()));
			stmt.setString(5, user.getPhone());
			stmt.setString(6, user.getAddress());
			stmt.setDate(7, sqlDOB);
			stmt.setString(8, user.getCountry());
			stmt.setString(9, user.getState());
			stmt.setString(10, user.getCity());
			stmt.setString(11, user.getImage());
			stmt.setInt(12, user.getUser_role());
			stmt.setTimestamp(13, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
			
			stmt.executeUpdate();
			
			result = stmt.getGeneratedKeys();
			
			if(result.next()) {
				generatedID = result.getInt(1);
				folder = new CreateFolder();
				folder.createFolder("WebContent/uploads", user.getEmail());
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
	
	public Boolean updateUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		CreateFolder folder = null;
		java.sql.Date sqlDOB = null;
		
		try {
			String oldFoldername = this.getUserByID(user.getUser_id()).getEmail();
			//Converting to date of birth to sql date
			sqlDOB = new java.sql.Date(user.getDob().getTime());
			
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEUSER);
			stmt.setString(1, slug.generateSlug(user.getEmail()));
			stmt.setString(2, user.getFull_name());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getAddress());
			stmt.setDate(6, sqlDOB);
			stmt.setString(7, user.getCountry());
			stmt.setString(8, user.getState());
			stmt.setString(9, user.getCity());
			stmt.setString(10, user.getImage());
			stmt.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(12, user.getUser_id());
			
			isUpdated = stmt.executeUpdate() > 0;
			
			if(isUpdated) {
				folder = new CreateFolder();
				folder.renameFolder("WebContent/uploads", oldFoldername, user.getEmail());
			}
			
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
	
	public Boolean updatePasswd(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		User queryUser = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEUSERPASSWORD);
			
			queryUser = this.getUserByID(user.getUser_id());
			
			if(this.checkPassword(user.getPassword(), queryUser.getPassword())) {
				stmt.setString(1, this.hashPassword(user.getNew_password()));
				stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				stmt.setInt(3, user.getUser_id());
				isUpdated = stmt.executeUpdate() > 0;
			}
						
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
	
	public Boolean updatePhoto(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isUpdated = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATEUSERPHOTO);
						
			stmt.setString(1, user.getImage());
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(3, user.getUser_id());
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
	
	public Boolean deleteUser(Integer userID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Boolean isDeleted = false;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETEUSER);
			stmt.setInt(1, userID);
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
	
	//Hash password using bcrypt
	public String hashPassword(String passwordPlaintext) {
		String salt = BCrypt.gensalt(strength);
		return BCrypt.hashpw(passwordPlaintext, salt);
	}
	
	//Check password
	public Boolean checkPassword(String passwordPlaintext, String storedHash) {
		if(storedHash == null || !storedHash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided");

			return BCrypt.checkpw(passwordPlaintext, storedHash);
	}
}
