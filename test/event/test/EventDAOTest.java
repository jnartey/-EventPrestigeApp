/**
 * 
 */
package event.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ep.event.dao.UserDAO;
import ep.event.dao.EventDAO;
import ep.event.model.User;
import ep.event.model.Event;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class EventDAOTest {
	EventDAO eventDAO;
	UserDAO usrDAO;
	Event event;
	User usr;
	User actualUser;
	Boolean isSave, isUpdated, isDeleted;
	Integer generatedUserID;
	Integer generatedEventID;
	List<Event> expected;
	List<Event> actual;
	DateFormat dateFormat;
	Date date;
	GregorianCalendar cal;
	
	@Before
	public void testUserPrep() throws SQLException {
		eventDAO = new EventDAO();
		usrDAO = new UserDAO();
		isSave = false;
		isUpdated = false;
		generatedUserID = null;
		generatedEventID = null;
		expected = new ArrayList<Event>();
		actual = new ArrayList<Event>();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();
		cal = new GregorianCalendar();
		cal.setTime(date);
		
        //convert calendar to date
		cal.add(Calendar.DATE, 1);
        Timestamp start_date = new Timestamp(cal.getTime().getTime());
        
        //Adding 10 days to current date for end_date
        cal.add(Calendar.DATE, 10);
        Timestamp end_date = new Timestamp(cal.getTime().getTime());
		
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
		
		actualUser = new User();
		generatedUserID = usrDAO.registerUser(usr);
		actualUser = usrDAO.getUserByID(generatedUserID);
		isSave = true;
		
		event = new Event();
		event.setTitle("My test event titles 3");
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
	}
	
	@Test
	public void addEventTest() throws SQLException {		
		generatedEventID = eventDAO.addEvent(event);
		Event actualEvent = eventDAO.getEventByID(generatedEventID);
		
		assertThat(generatedEventID, not(equalTo(null)));
		assertThat(actualEvent, not(equalTo(null)));
		
		assertThat(event.getTitle(), equalTo(actualEvent.getTitle()));
		
		isSave = true;
	}
	
	@Test
	public void getAllEventsTest() throws SQLException {
		List<Event> actualEvent = eventDAO.getAllEvents();
		assertThat(actualEvent, not(equalTo(null)));
	}
	
	@Test
	public void getEventsByUserTest() throws SQLException {
		
		List<Event> actualEvent = eventDAO.getEventsByUser(generatedUserID);
		assertThat(actualEvent, not(equalTo(null)));
		
		isSave = true;
	}
	
	@Test
	public void updateEventTest() throws SQLException {
		//convert calendar to date
		cal.add(Calendar.DATE, 5);
        Timestamp start_date = new Timestamp(cal.getTime().getTime());
        
      //Adding 10 days to current date for end_date
        cal.add(Calendar.DATE, 20);
        Timestamp end_date = new Timestamp(cal.getTime().getTime());
				
		generatedEventID = eventDAO.addEvent(event);
		Event actualEvent = eventDAO.getEventByID(generatedEventID);
		
		assertThat(generatedEventID, not(equalTo(null)));
		assertThat(actualEvent, not(equalTo(null)));
		
		assertThat(event.getTitle(), equalTo(actualEvent.getTitle()));
		
		event.setEvent_id(generatedEventID);
		event.setTitle("My test event title updated");
		event.setDescription("<p>" + 
				"Updated Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " + 
				"tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " + 
				"quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " + 
				"consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " + 
				"cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " + 
				"proident, sunt in culpa qui officia deserunt mollit anim id est laborum." + 
				"</p>");
		event.setAddress("2472 Marion Ave. Apt 3B");
		event.setCountry("United States");
		event.setState("New York");
		event.setCity("New York");
		event.setStart_date(start_date);
		event.setEnd_date(end_date);
		event.setImage("sample2.jpg");
		event.setFeature_event(0);
		
		isUpdated = eventDAO.updateEvent(event);
		
		assertThat(event, not(equalTo(null)));
		assertThat(isUpdated, equalTo(true));
		
		isSave = true;
	}
	
	@After
	public void cleanUpTest() throws SQLException {
		if(isSave) {
			if(generatedEventID != null){
				isDeleted = eventDAO.deleteEvent(generatedEventID);
			}
			
			if(generatedUserID != null){
				usrDAO.deleteUser(generatedUserID);
			}
		}
	}
}
