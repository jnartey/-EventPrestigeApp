/**
 * 
 */
package user.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ep.event.dao.UserProfileDAO;
import ep.event.dao.UserDAO;
import ep.event.model.User;
import ep.event.model.UserProfile;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Jacob Nartey
 *
 */

//Running test cases in order of method names in ascending order
@FixMethodOrder(MethodSorters.JVM)
public class UserProfileDAOTest {
	UserProfileDAO usrProfileDAO;
	UserDAO usrDAO;
	UserProfile usrProfile;
	User usr;
	User actualUser;
	Boolean isSave, isUpdated, isDeleted;
	Integer generatedUserID;
	Integer generatedUserProfileID;
	
	@Before
	public void testUserPrep() throws SQLException {
		usrProfileDAO = new UserProfileDAO();
		usrDAO = new UserDAO();
		isSave = false;
		isUpdated = false;
		generatedUserID = null;
		generatedUserProfileID = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dob = null;
		try {
			dob = sdf.parse("1982-04-17");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		
		actualUser = new User();
		generatedUserID = usrDAO.registerUser(usr);
		actualUser = usrDAO.getUserByID(generatedUserID);
		isSave = true;
		
		usrProfile = new UserProfile();
		usrProfile.setUser_id(generatedUserID);
		usrProfile.setTitle("User banner title");
		usrProfile.setDescription("This is just a test text for user banner description");
		usrProfile.setImage("sample.jpg");
		
	}
	
	@Test
	public void addUserProfileTest() throws SQLException {		
		generatedUserProfileID = usrProfileDAO.addUserProfile(usrProfile);
		UserProfile actualUserProfile = usrProfileDAO.getUserProfileByID(generatedUserProfileID);
		
		assertThat(generatedUserProfileID, not(equalTo(null)));
		assertThat(actualUserProfile, not(equalTo(null)));
		
		assertThat(usrProfile.getTitle(), equalTo(actualUserProfile.getTitle()));
		
		isSave = true;
	}
	
	@Test
	public void getAllUserProfilesTest() throws SQLException {
		List<UserProfile> actualUserProfile = usrProfileDAO.getAllUserProfiles();
		assertThat(actualUserProfile, not(equalTo(null)));
	}
	
	@Test
	public void getUserProfilesByUserTest() throws SQLException {
		
		List<UserProfile> actualEvent = usrProfileDAO.getAllProfileByUser(generatedUserID);
		assertThat(actualEvent, not(equalTo(null)));
		
		isSave = true;
	}
	
	@Test
	public void updateUserProfileTest() throws SQLException {
		
		generatedUserProfileID = usrProfileDAO.addUserProfile(usrProfile);
		UserProfile actualUserProfile = usrProfileDAO.getUserProfileByID(generatedUserProfileID);
		
		assertThat(generatedUserProfileID, not(equalTo(null)));
		assertThat(actualUserProfile, not(equalTo(null)));
		
		assertThat(usrProfile.getTitle(), equalTo(actualUserProfile.getTitle()));
		
		usrProfile.setUser_id(generatedUserID);
		usrProfile.setTitle("User banner title updated");
		usrProfile.setDescription("This is just a test text for user banner description updateed");
		usrProfile.setImage("sample-updated.jpg");
		usrProfile.setUser_profile_id(generatedUserProfileID);
		
		isUpdated = usrProfileDAO.updateUserProfile(usrProfile);
		
		assertThat(usrProfile, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
		
		isSave = true;
	}
	
	@After
	public void cleanUpTest() throws SQLException {
		if(isSave) {
			if(generatedUserProfileID != null){
				isDeleted = usrProfileDAO.deleteUserProfile(generatedUserProfileID);
			}
			
			if(generatedUserID != null){
				usrDAO.deleteUser(generatedUserID);
			}
		}
	}
}
