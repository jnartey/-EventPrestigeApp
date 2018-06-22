/**
 * 
 */
package eventprestige.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ep.event.dao.EventBannerDAO;
import ep.event.dao.EventDAO;
import ep.event.dao.EventGalleryDAO;
import ep.event.dao.EventImageDAO;
import ep.event.dao.UserDAO;
import ep.event.model.Event;
import ep.event.model.EventBanner;
import ep.event.model.EventGallery;
import ep.event.model.EventImage;
import ep.event.model.User;
import ep.event.utils.Slugify;

/**
 * @author Jacob Nartey
 *
 */

@Controller
@RequestMapping("/")
@SessionAttributes("userkey")
public class PagesController {
	
    private UserDAO userDAO;
    private EventDAO eventDAO;
    private EventGalleryDAO eventGalleryDAO;
    private EventImageDAO eventImageDAO;
    private EventBannerDAO eventBannerDAO;
    private static final String TIMESTAMPFORMAT = "yyyy-MM-dd HH:mm:ss";
    private Slugify slug = new Slugify();
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        
        CustomDateEditor dob = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, "dob", dob);
        
        SimpleDateFormat dateFormatT = new SimpleDateFormat(TIMESTAMPFORMAT);
        dateFormatT.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatT, false));
        
        CustomDateEditor timeStampStart = new CustomDateEditor(dateFormatT, true);
        binder.registerCustomEditor(Timestamp.class, "start_date", timeStampStart);
    }
	
	@ModelAttribute("userkey")
	public User setUpUserForm() {
		return new User();
	}
	
	@RequestMapping(value= {"/", "/index"})
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("current_page", "home");
		return mav;
	}
	
	@RequestMapping(value= {"/{userRef}", "/index/{userRef}"})
	public ModelAndView index(@PathVariable("userRef")String userRef, final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		eventDAO = new EventDAO();
		userDAO = new UserDAO();
		eventBannerDAO = new EventBannerDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		Event currentEvent = eventDAO.getLatestEvent(user.getUser_id());
		List<EventBanner> banners = eventBannerDAO.getAllBannersByEvent(currentEvent.getEvent_id());
		
		mav.addObject("banners", banners);
		mav.addObject("user_info", user);
		mav.addObject("events", eventDAO.getEventsByUserLimit(user.getUser_id(), 5));
		mav.addObject("current_event", currentEvent);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		long currEventStartDate = currentEvent.getStart_date().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currEventStartDate);
		
		mav.addObject("current_event_day", cal.get(Calendar.DAY_OF_MONTH));
		mav.addObject("current_event_month", new SimpleDateFormat("MMM").format(cal.getTime()));
		mav.addObject("current_event_year", cal.get(Calendar.YEAR));
		mav.addObject("current_event_time", cal.get(Calendar.HOUR) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
		
		mav.setViewName("user_index");
		mav.addObject("current_page", "home");
		return mav;
	}
	
	@RequestMapping("/events")
	public ModelAndView events(ModelAndView mav) {
		mav.setViewName("events");
		mav.addObject("current_page", "events");
		return mav;
	}
	
	@RequestMapping("/events/{userRef}")
	public ModelAndView events(@PathVariable("userRef")String userRef, final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		eventDAO = new EventDAO();
		userDAO = new UserDAO();
		eventBannerDAO = new EventBannerDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		Event currentEvent = eventDAO.getLatestEvent(user.getUser_id());
		List<EventBanner> banners = eventBannerDAO.getAllBannersByEvent(currentEvent.getEvent_id());
		
		mav.addObject("banners", banners);
		mav.addObject("user_info", user);
		mav.addObject("current_event", currentEvent);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		long currEventStartDate = currentEvent.getStart_date().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currEventStartDate);
		
		mav.addObject("current_event_day", cal.get(Calendar.DAY_OF_MONTH));
		mav.addObject("current_event_month", new SimpleDateFormat("MMM").format(cal.getTime()));
		mav.addObject("current_event_year", cal.get(Calendar.YEAR));
		mav.addObject("current_event_time", cal.get(Calendar.HOUR) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
		
		mav.addObject("events", eventDAO.getEventsByUser(user.getUser_id()));
		mav.addObject("user_info", user);
		
		mav.setViewName("events");
		mav.addObject("current_page", "events");
		return mav;
	}
	
	@RequestMapping(value= {"event-details/{userRef}/{eventID}"})
	public ModelAndView eventDetails(@PathVariable("userRef")String userRef, @PathVariable("eventID")int eventID, final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		eventDAO = new EventDAO();
		userDAO = new UserDAO();
		eventGalleryDAO = new EventGalleryDAO();
		eventImageDAO = new EventImageDAO();
		eventBannerDAO = new EventBannerDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		Event currentEvent = eventDAO.getEventByID(eventID);
		
		List<EventGallery> galleries = eventGalleryDAO.getGalleryByEventByFirstImage(eventID);
		
		int countGallery = galleries.size();
		List<EventBanner> banners = eventBannerDAO.getAllBannersByEvent(currentEvent.getEvent_id());
		
		mav.addObject("banners", banners);
		mav.addObject("user_info", user);
		mav.addObject("events", eventDAO.getEventsByUserLimit(user.getUser_id(), 5));
		mav.addObject("current_event", currentEvent);
		mav.addObject("galleries", galleries);
		mav.addObject("user_folder", slug.generateSlug(user.getEmail()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		long currEventStartDate = currentEvent.getStart_date().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currEventStartDate);
		
		mav.addObject("current_event_day", cal.get(Calendar.DAY_OF_MONTH));
		mav.addObject("current_event_month", new SimpleDateFormat("MMM").format(cal.getTime()));
		mav.addObject("current_event_year", cal.get(Calendar.YEAR));
		mav.addObject("current_event_time", cal.get(Calendar.HOUR) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
		
		int eventGalleryID = 0;
		
		if(countGallery == 1) {
			for(EventGallery gallery : galleries) {
				eventGalleryID = gallery.getEvent_gallery_id();
			}
			
			mav.addObject("images", eventImageDAO.getAllEventImageByGallery(eventGalleryID));
		}
		
		System.out.println(countGallery);
		
		mav.setViewName("event_details");
		mav.addObject("current_page", "events");
		return mav;
	}
	
	@RequestMapping(value= {"gallery-details/{userRef}/{eventID}/{galleryID}"})
	public ModelAndView galleryDetails(@PathVariable("userRef")String userRef, @PathVariable("eventID")int eventID, @PathVariable("galleryID")int galleryID, 
			final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		eventDAO = new EventDAO();
		userDAO = new UserDAO();
		eventImageDAO = new EventImageDAO();
		eventGalleryDAO = new EventGalleryDAO();
		eventBannerDAO = new EventBannerDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		Event currentEvent = eventDAO.getEventByID(eventID);
		List<EventBanner> banners = eventBannerDAO.getAllBannersByEvent(currentEvent.getEvent_id());
		
		mav.addObject("banners", banners);
		mav.addObject("user_info", user);
		mav.addObject("event_details", currentEvent);
		mav.addObject("gallery_details", eventGalleryDAO.getGalleryByID(galleryID));
		mav.addObject("images", eventImageDAO.getAllEventImageByGallery(galleryID));
		mav.addObject("user_folder", slug.generateSlug(user.getEmail()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		long currEventStartDate = currentEvent.getStart_date().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currEventStartDate);
		
		mav.addObject("current_event_day", cal.get(Calendar.DAY_OF_MONTH));
		mav.addObject("current_event_month", new SimpleDateFormat("MMM").format(cal.getTime()));
		mav.addObject("current_event_year", cal.get(Calendar.YEAR));
		mav.addObject("current_event_time", cal.get(Calendar.HOUR) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
		
		mav.setViewName("gallery_details");
		return mav;
	}
	
	@RequestMapping("/about-us/{userRef}")
	public ModelAndView aboutUs(@PathVariable("userRef")String userRef, final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		userDAO = new UserDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		mav.addObject("user_info", user);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		mav.setViewName("about_us");
		mav.addObject("current_page", "about us");
		return mav;
	}
	
	@RequestMapping("/contact-us/{userRef}")
	public ModelAndView contactUs(@PathVariable("userRef")String userRef, final RedirectAttributes redirectAttributes, ModelAndView mav) throws SQLException {
		userDAO = new UserDAO();
		
		User user = userDAO.getUserBySlug(userRef);
		mav.addObject("user_info", user);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		mav.setViewName("contact");
		mav.addObject("current_page", "contact us");
		return mav;
	}
	
	//User accounts
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView processLogin(@ModelAttribute User user, ModelAndView mav, HttpServletRequest request, 
			final RedirectAttributes redirectAttributes) throws SQLException, MalformedURLException {
		
		mav.setViewName("login");
				
		if(user.getEmail() != null) {
			String email = user.getEmail();
			String password = user.getPassword();
			
			eventDAO = new EventDAO();
			
			User userDetails = null;
			userDAO = new UserDAO();
			boolean isValidated = userDAO.validateLogin(email, password);
			URL url = new URL(request.getRequestURL().toString());
			
			if(isValidated) {
				userDetails = userDAO.login(email, password);
				mav.addObject("userkey", userDetails);
				mav.addObject("user_link",url.getProtocol() + "://" + url.getHost() + request.getContextPath().toString() + "/" + slug.generateSlug(email));
				List<Event> events = eventDAO.getEventsByUser(userDetails.getUser_id());
				mav.addObject("events", events);
				mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
				
				mav.setViewName("user/dashboard");
			}else {
				redirectAttributes.addFlashAttribute("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Invalid username & password</div>");
		        mav.setViewName("redirect:login");
			}
		}
				
		return mav;
	}
	
	@RequestMapping("/create-account")
	public ModelAndView createAccount() {
		return new ModelAndView("create_account");
	}
	
	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public ModelAndView processAccount(@ModelAttribute User user, ModelAndView mav, 
			final RedirectAttributes redirectAttributes, @ModelAttribute("message") String message) throws SQLException {
		userDAO = new UserDAO();
		Integer userID = userDAO.registerUser(user);
		if(userID != null) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> User account for " + user.getFull_name() + " created successfully</div>");
			mav.setViewName("redirect:login");
		}else {
			mav.setViewName("create_account");
		}
		
		return mav;
	}
	
	@RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, SessionStatus status, ModelAndView mav, 
    		final RedirectAttributes redirectAttributes, @ModelAttribute("message") String message)  {
		
		HttpSession session = request.getSession(false);
        session.removeAttribute("userkey");
        session.invalidate();
        status.setComplete();
        
        redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> You have succesfully logged</div>");
		mav.setViewName("redirect:login");		
		return mav;
    }	
}
