/**
 * 
 */
package user.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ep.event.dao.UserBannerDAO;
import ep.event.dao.UserDAO;
import ep.event.model.User;
import ep.event.model.UserBanner;

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
public class UserBannerDAOTest {
	UserBannerDAO usrBannerDAO;
	UserDAO usrDAO;
	UserBanner usrBanner;
	User usr;
	User actualUser;
	Boolean isSave, isUpdated, isDeleted;
	Integer generatedUserID;
	Integer generatedBannerID;
	
	@Before
	public void testUserPrep() throws SQLException {
		usrBannerDAO = new UserBannerDAO();
		usrDAO = new UserDAO();
		isSave = false;
		isUpdated = false;
		generatedUserID = null;
		generatedBannerID = null;
		
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
		
		usrBanner = new UserBanner();
		usrBanner.setUser_id(generatedUserID);
		usrBanner.setTitle("User banner title");
		usrBanner.setDescription("This is just a test text for user banner description");
		usrBanner.setImage("sample.jpg");
		
	}
	
	@Test
	public void addBannerTest() throws SQLException {		
		generatedBannerID = usrBannerDAO.addBanner(usrBanner);
		UserBanner actualBanner = usrBannerDAO.getBannerByID(generatedBannerID);
		
		assertThat(generatedBannerID, not(equalTo(null)));
		assertThat(actualBanner, not(equalTo(null)));
		
		assertThat(usrBanner.getTitle(), equalTo(actualBanner.getTitle()));
		
		isSave = true;
	}
	
	@Test
	public void getAllBannersTest() throws SQLException {
		List<UserBanner> actualUserBanner = usrBannerDAO.getAllBanners();
		assertThat(actualUserBanner, not(equalTo(null)));
	}
	
	@Test
	public void getBannersByUserTest() throws SQLException {
		
		List<UserBanner> actualEvent = usrBannerDAO.getAllBannersByUser(generatedUserID);
		assertThat(actualEvent, not(equalTo(null)));
		
		isSave = true;
	}
	
	@Test
	public void updateBannerTest() throws SQLException {
		
		generatedBannerID = usrBannerDAO.addBanner(usrBanner);
		UserBanner actualBanner = usrBannerDAO.getBannerByID(generatedBannerID);
		
		assertThat(generatedBannerID, not(equalTo(null)));
		assertThat(actualBanner, not(equalTo(null)));
		
		assertThat(usrBanner.getTitle(), equalTo(actualBanner.getTitle()));
		
		usrBanner.setUser_id(generatedUserID);
		usrBanner.setTitle("User banner title updated");
		usrBanner.setDescription("This is just a test text for user banner description updateed");
		usrBanner.setImage("sample-updated.jpg");
		usrBanner.setBanner_id(generatedBannerID);
		
		isUpdated = usrBannerDAO.updateBanner(usrBanner);
		
		assertThat(usrBanner, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
		
		isSave = true;
	}
	
	@After
	public void cleanUpTest() throws SQLException {
		if(isSave) {
			if(generatedBannerID != null){
				isDeleted = usrBannerDAO.deleteBanner(generatedBannerID);
			}
			
			if(generatedUserID != null){
				usrDAO.deleteUser(generatedUserID);
			}
		}
	}
}
