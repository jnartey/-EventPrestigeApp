/**
 * 
 */
package event.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ep.event.dao.EventGalleryDAO;
import ep.event.dao.EventDAO;
import ep.event.dao.UserDAO;
import ep.event.model.Event;
import ep.event.model.EventGallery;
import ep.event.model.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Jacob Nartey
 *
 */

//Running test cases in order of method names in ascending order
@FixMethodOrder(MethodSorters.JVM)
public class EventGalleryDAOTest {
	EventGalleryDAO eventGalleryDAO;
	EventDAO eventDAO;
	UserDAO usrDAO;
	EventGallery eventGallery;
	Event event;
	Event actualEvent;
	User usr;
	User actualUser;
	Boolean isSave, isUpdated, isDeleted;
	Integer generatedUserID;
	Integer generatedEventID;
	Integer generatedGalleryID;
	DateFormat dateFormat;
	Date date;
	GregorianCalendar cal;
	
	@Before
	public void testUserPrep() throws SQLException {
		eventGalleryDAO = new EventGalleryDAO();
		eventDAO = new EventDAO();
		usrDAO = new UserDAO();
		isSave = false;
		isUpdated = false;
		generatedUserID = null;
		generatedEventID = null;
		generatedGalleryID = null;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();
		cal = new GregorianCalendar();
		cal.setTime(date);
		
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
		
		//convert calendar to date
		cal.add(Calendar.DATE, 1);
        Timestamp start_date = new Timestamp(cal.getTime().getTime());
        
        //Adding 10 days to current date for end_date
        cal.add(Calendar.DATE, 10);
        Timestamp end_date = new Timestamp(cal.getTime().getTime());
		
		event = new Event();
		event.setTitle("My test event title");
		event.setDescription("<p>" + 
				"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " + 
				"tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " + 
				"quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " + 
				"consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " + 
				"cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " + 
				"proident, sunt in culpa qui officia deserunt mollit anim id est laborum." + 
				"</p>");
		event.setAddress("72 Seaman Avenue, Apt 5F");
		event.setCountry("United States");
		event.setState("New York");
		event.setCity("New York");
		event.setStart_date(start_date);
		event.setEnd_date(end_date);
		event.setImage("sample.jpg");
		event.setFeature_event(0);
		event.setUser_id(generatedUserID);
		isSave = true;
		
		actualEvent = new Event();
		generatedEventID = eventDAO.addEvent(event);
		actualEvent = eventDAO.getEventByID(generatedEventID);
		
		eventGallery = new EventGallery();
		eventGallery.setEvent_id(generatedEventID);
		eventGallery.setTitle("User gallery title");
		eventGallery.setDescription("This is just a test text for user gallery description");
		
	}
	
	@Test
	public void addGalleryTest() throws SQLException {		
		generatedGalleryID = eventGalleryDAO.addEventGallery(eventGallery);
		EventGallery actualGallery = eventGalleryDAO.getGalleryByID(generatedGalleryID);
		
		assertThat(generatedGalleryID, not(equalTo(null)));
		assertThat(actualGallery, not(equalTo(null)));
		
		assertThat(eventGallery.getTitle(), equalTo(actualGallery.getTitle()));
		
		isSave = true;
	}
	
	@Test
	public void getAllGalleriesTest() throws SQLException {
		List<EventGallery> actualEventGallery = eventGalleryDAO.getAllEventGallery();
		assertThat(actualEventGallery, not(equalTo(null)));
	}
	
	@Test
	public void getGallerysByEventTest() throws SQLException {
		
		List<EventGallery> actualGallery = eventGalleryDAO.getGalleryByEvent(generatedEventID);
		assertThat(actualGallery, not(equalTo(null)));
		
		isSave = true;
	}
	
	@Test
	public void updateGalleryTest() throws SQLException {
		
		generatedGalleryID = eventGalleryDAO.addEventGallery(eventGallery);
		EventGallery actualGallery = eventGalleryDAO.getGalleryByID(generatedGalleryID);
		
		assertThat(generatedGalleryID, not(equalTo(null)));
		assertThat(actualGallery, not(equalTo(null)));
		
		assertThat(eventGallery.getTitle(), equalTo(actualGallery.getTitle()));
		
		eventGallery.setEvent_id(generatedEventID);
		eventGallery.setTitle("User gallery title updated");
		eventGallery.setDescription("This is just a test text for user gallery description updated");
		eventGallery.setEvent_gallery_id(generatedGalleryID);
		
		isUpdated = eventGalleryDAO.updateEventGallery(eventGallery);
		
		assertThat(eventGallery, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
		
		isSave = true;
	}
	
	@After
	public void cleanUpTest() throws SQLException {
		if(isSave) {
			if(generatedGalleryID != null){
				isDeleted = eventGalleryDAO.deleteEventGallery(generatedGalleryID);
			}
			
			if(generatedEventID != null){
				eventDAO.deleteEvent(generatedEventID);
			}
			
			if(generatedUserID != null){
				usrDAO.deleteUser(generatedUserID);
			}
		}
	}
}
