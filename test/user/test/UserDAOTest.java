/**
 * 
 */
package user.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ep.event.dao.UserDAO;
import ep.event.model.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Nartey
 *
 */

//Running test cases in order of method names in ascending order
@FixMethodOrder(MethodSorters.JVM)
public class UserDAOTest {
	UserDAO usrDAO;
	User usr;
	User user;
	Boolean isToBeDeleted, isUpdated, isDeleted;
	Integer generatedID;
	List<User> expected;
	List<User> actual;
	
	@Before
	public void testUserPrep() {
		usrDAO = new UserDAO();
		isToBeDeleted = false;
		isUpdated = false;
		generatedID = null;
		expected = new ArrayList<User>();
		actual = new ArrayList<User>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dob = null;
		try {
			dob = sdf.parse("1982-04-17");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		usr = new User();
		usr.setFull_name("Jacob Nartey Test");
		usr.setEmail("jnarteytest@gmail.com");
		usr.setPhone("+19293042532");
		usr.setPassword("password");
		usr.setAddress("72 Seaman Avenue, Apt 5F");
		usr.setDob(dob);
		usr.setCountry("Ghana");
		usr.setState("Greater Accra");
		usr.setCity("Tema");
		usr.setUser_role(1);
	}
	
	@Test
	public void registerUserTest() throws SQLException {
		generatedID = usrDAO.registerUser(usr);
		
		User actualUser = usrDAO.getUserByID(generatedID);
		
		assertThat(generatedID, not(equalTo(null)));
		assertThat(actualUser, not(equalTo(null)));
		
		assertThat(usr.getEmail(), equalTo(actualUser.getEmail()));
	}
	
	@Test
	public void getAllUsersTest() throws SQLException {
		List<User> actualUser = usrDAO.getAllUsers();
		assertThat(actualUser, not(equalTo(null)));
	}
	
	@Test
	public void getUserByEmailTest() throws SQLException {
		User actualUser = usrDAO.getUserByEmail("jnarteytest@gmail.com");
		assertThat(actualUser, not(equalTo(null)));
		assertThat(usr.getEmail(), equalTo(actualUser.getEmail()));
	}
	
	@Test
	public void loginTest() throws SQLException {
		User actualUser = usrDAO.getUserByEmail("jnarteytest@gmail.com");
		
		user = usrDAO.login(usr.getEmail(), usr.getPassword());
		
		assertThat(actualUser, not(equalTo(null)));
		assertThat(user.getEmail(), equalTo(actualUser.getEmail()));
	}
	
	@Test
	public void updateUserTest() throws SQLException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dob = null;
		try {
			dob = sdf.parse("1983-01-26");
		} catch (ParseException e) {
			e.getMessage();
		}
		
		User actualUser = usrDAO.getUserByEmail("jnarteytest@gmail.com");
		usr.setUser_id(actualUser.getUser_id());
		usr.setFull_name("Esther Ofori Test");
		usr.setEmail("flettotest@gmail.com");
		usr.setPhone("+19293042532");
		usr.setAddress("72 Seaman Avenue, Apt 5F");
		usr.setDob(dob);
		usr.setCountry("USA");
		usr.setState("NY");
		usr.setCity("New York");
		
		isUpdated = usrDAO.updateUser(usr);
		
		assertThat(usr, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
	}
	
	@Test
	public void updatePasswdTest() throws SQLException {
		User actualUser = usrDAO.getUserByEmail("flettotest@gmail.com");
		usr.setUser_id(actualUser.getUser_id());
		usr.getPassword();
		usr.setNew_password("mynewpassword");
		isUpdated = usrDAO.updatePasswd(usr);
		
		assertThat(usr, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
		
		isToBeDeleted = true;
	}
	
	@After
	public void deleteUserTest() throws SQLException {
		if(isToBeDeleted) {
			User actualUser = usrDAO.getUserByEmail("flettotest@gmail.com");
			isDeleted = usrDAO.deleteUser(actualUser.getUser_id());
		}
	}
	
}
