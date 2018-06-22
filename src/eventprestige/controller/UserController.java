package eventprestige.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
import ep.event.utils.FileUploadHelper;
import ep.event.utils.Slugify;

@Controller
@RequestMapping("/user/")
@SessionAttributes("userkey")
public class UserController {
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
	
	@RequestMapping(value= {"/user/", "/user/index"})
	public ModelAndView index() {
		return new ModelAndView("user/index");
	}
	
	@RequestMapping("/banners")
	public ModelAndView banners(@SessionAttribute("userkey") User userkey, ModelAndView mav) throws SQLException {
		
		eventDAO = new EventDAO();
	
		List<Event> events = eventDAO.getEventsByUser(userkey.getUser_id());
		mav.addObject("events", events);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/banners");
		
		return mav;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(@SessionAttribute("userkey") User userkey, ModelAndView mav, HttpServletRequest request, 
			final RedirectAttributes redirectAttributes) throws SQLException, MalformedURLException {
		
		mav.setViewName("user/dashboard");
		eventDAO = new EventDAO();
		
		URL url = new URL(request.getRequestURL().toString());
		
		List<Event> events = eventDAO.getEventsByUser(userkey.getUser_id());
		mav.addObject("events", events);
		mav.addObject("user_link",url.getProtocol() + "://" + url.getHost() + request.getContextPath().toString() + "/" + slug.generateSlug(userkey.getEmail()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.addObject("events", eventDAO.getEventsByUserLimit(userkey.getUser_id(), 10));
		
		return mav;
	}
	
	@RequestMapping("/account-settings")
	public ModelAndView accountSettings(@SessionAttribute("userkey") User userkey, ModelAndView mav) throws SQLException {
		
		userDAO = new UserDAO();
		mav.addObject("user_details", userDAO.getUserByID(userkey.getUser_id()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/account_settings");
		
		return mav;
	}
	
	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public ModelAndView updateAccount(@ModelAttribute User user, @SessionAttribute("userkey") User userkey, 
			HttpServletRequest request, ModelAndView mav) throws SQLException {
		
		userDAO = new UserDAO();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    java.util.Date parsedDob = null;
	    
		try {
			parsedDob = dateFormat.parse(request.getParameter("dob"));
		} catch (ParseException e) {
			e.getMessage();
		}
		
		user.setUser_id(userkey.getUser_id());
		user.setDob(parsedDob);
		
		boolean isUpdated = userDAO.updateUser(user);
		
		if(isUpdated) {
			mav.addObject("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + user.getFull_name() + "'s account updated successfully</div>");
		}else {
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error " + user.getFull_name() + "'s account could not be updated</div>");
		}
		
		userDAO = new UserDAO();
		mav.addObject("user_details", userDAO.getUserByID(userkey.getUser_id()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/account_settings");
				
		return mav;
	}
	
	@RequestMapping(value = "/update-password", method = RequestMethod.POST)
	public ModelAndView updatePassword(@ModelAttribute User user, @SessionAttribute("userkey") User userkey, 
			HttpServletRequest request, ModelAndView mav) throws SQLException {
		
		userDAO = new UserDAO();
		
		user.setUser_id(userkey.getUser_id());
		
		boolean isUpdated = userDAO.updatePasswd(user);
		
		if(isUpdated) {
			mav.addObject("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + userkey.getFull_name() + "'s password updated successfully</div>");
		}else {
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error " + userkey.getFull_name() + "'s password could not be updated</div>");
		}
		
		mav.addObject("user_details", userDAO.getUserByID(userkey.getUser_id()));
		mav.setViewName("user/account_settings");
				
		return mav;
	}
	
	@RequestMapping(value = "/upload-photo", method = RequestMethod.POST)
	public ModelAndView uploadPhoto(@ModelAttribute User user, @SessionAttribute("userkey") User userkey, 
			HttpServletRequest request, ModelAndView mav, @RequestParam("profile_photo") MultipartFile file) throws SQLException {
		
		userDAO = new UserDAO();
				
		user.setUser_id(userkey.getUser_id());
		user.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail())));
		boolean isUpdated = userDAO.updatePhoto(user);
		
		if(isUpdated) {
			mav.addObject("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + userkey.getFull_name() + "'s photo updated successfully</div>");
		}else {
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error " + userkey.getFull_name() + "'s photo could not be updated</div>");
		}
		
		mav.addObject("user_details", userDAO.getUserByID(userkey.getUser_id()));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/account_settings");
				
		return mav;
	}
	
	@RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, SessionStatus status, ModelAndView mav, 
    		final RedirectAttributes redirectAttributes, @ModelAttribute("message") String message)  {
		
		//HttpSession session = request.getSession(false);
        //session.removeAttribute("userkey");
        //session.invalidate();
        status.setComplete();
        
        redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> You have succesfully logged</div>");
		mav.setViewName("redirect:/login");		
		return mav;
    }	
	
	//User Events
	@RequestMapping("/user-events")
	public ModelAndView userEvents(@SessionAttribute("userkey") User userkey, ModelAndView mav) throws SQLException {
		
		eventDAO = new EventDAO();
	
		List<Event> events = eventDAO.getEventsByUser(userkey.getUser_id());
		mav.addObject("events", events);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("/user/user_events");
		
		return mav;
	}
	
	@RequestMapping("/create-event")
	public ModelAndView createEvent(@SessionAttribute("userkey") User user, ModelAndView mav) {
		if(user != null) {
			mav.setViewName("/user/create_event");
		}else {
			mav.setViewName("redirect:/login");
		}
		
		mav.addObject("user_path", "uploads/" + slug.generateSlug(user.getEmail()));
		
		return mav;
	}
	
	@RequestMapping(value = "/create-event", method = RequestMethod.POST)
	public ModelAndView createEvent(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ 
			HttpServletRequest request, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @RequestParam("image") MultipartFile file) throws SQLException {
		
		eventDAO = new EventDAO();
		Event event = new Event();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMPFORMAT);

	    java.util.Date parsedStartTimeStamp = null;
	    
		try {
			parsedStartTimeStamp = dateFormat.parse(request.getParameter("start_date"));
		} catch (ParseException e) {
			e.getMessage();
		}
				
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(parsedStartTimeStamp);
		
        //convert calendar to date
        Timestamp starttimestamp = new Timestamp(cal.getTime().getTime());
				
		event.setUser_id(userkey.getUser_id());
		event.setTitle(request.getParameter("title"));
		event.setDescription(request.getParameter("description"));
		event.setAddress(request.getParameter("address"));
		event.setCountry(request.getParameter("country"));
		event.setCity(request.getParameter("city"));
		event.setState(request.getParameter("state"));
		event.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail())));
		event.setStart_date(starttimestamp);
		event.setEnd_date(starttimestamp);
		event.setFeature_event(1);
				
		Integer eventID = eventDAO.addEvent(event);
		
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		
		if(eventID != null) {
			mav.addObject("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event " + event.getTitle() + " created successfully</div>");
			mav.setViewName("user/create_event");
		}else {
			mav.setViewName("user/create_event");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/edit-event/{event_id}", method = {RequestMethod.GET})
	public ModelAndView editEvent(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID) throws SQLException {
		
		eventDAO = new EventDAO();
		Event evenDetails = eventDAO.getEventByID(eventID);
		mav.addObject("event_details", evenDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/edit_event");
		
		return mav;
	}
	
	@RequestMapping(value = "/view-event/{event_id}", method = {RequestMethod.GET})
	public ModelAndView viewEvent(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID) throws SQLException {
		
		eventDAO = new EventDAO();
		Event eventDetails = eventDAO.getEventByID(eventID);
		mav.addObject("event_details", eventDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		
		mav.setViewName("user/view_event");
		
		return mav;
	}
	
	@RequestMapping(value = "/delete-event/{event_id}", method = {RequestMethod.GET})
	public ModelAndView deleteEvent(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID, final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventDAO = new EventDAO();
		Event eventDetails = eventDAO.getEventByID(eventID);
		boolean isDeleted = eventDAO.deleteEvent(eventID);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event " + eventDetails.getTitle() + " deleted successfully</div>");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event " + eventDetails.getTitle() + " deleted successfully</div>");
		}
		
		mav.setViewName("redirect:../user/user-events");
		
		return mav;
	}
	
	@RequestMapping(value = "/update-event", method = {RequestMethod.POST})
	public ModelAndView updateEvent(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ 
			HttpServletRequest request, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @RequestParam("image") MultipartFile file) throws SQLException {
		
		eventDAO = new EventDAO();
		Event event = new Event();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMPFORMAT);

	    java.util.Date parsedStartTimeStamp = null;
	    
		try {
			parsedStartTimeStamp = dateFormat.parse(request.getParameter("start_date"));
		} catch (ParseException e) {
			e.getMessage();
		}
				
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(parsedStartTimeStamp);
		
        //convert calendar to date
        Timestamp starttimestamp = new Timestamp(cal.getTime().getTime());
        
		event.setUser_id(userkey.getUser_id());
		event.setTitle(request.getParameter("title"));
		event.setDescription(request.getParameter("description"));
		event.setAddress(request.getParameter("address"));
		event.setCountry(request.getParameter("country"));
		event.setCity(request.getParameter("city"));
		event.setState(request.getParameter("state"));
		event.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail())));
		event.setStart_date(starttimestamp);
		event.setEnd_date(starttimestamp);
		event.setFeature_event(1);
		event.setEvent_id(Integer.parseInt(request.getParameter("event_id")));
				
		boolean isUpdated = eventDAO.updateEvent(event);
		
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		
		if(isUpdated) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event " + event.getTitle() + " updated successfully</div>");
			mav.setViewName("redirect:/user/user-events");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event " + event.getTitle() + " could not be updated</div>");
			mav.setViewName("redirect:/user/edit-event");
		}
		
		return mav;
	}
	
	//Gallery
	@RequestMapping(value = "/event-galleries/{event_id}", method = {RequestMethod.GET})
	public ModelAndView eventGalleries(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		List<EventGallery> galleries = eventGalleryDAO.getGalleryByEvent(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("galleries", galleries);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/event_galleries");
		
		return mav;
	}
	
	@RequestMapping(value = "/create-gallery/{event_id}", method = {RequestMethod.GET})
	public ModelAndView createGallery(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @ModelAttribute EventGallery eventGallery,
			@ModelAttribute("message") String message) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/create_gallery");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-create-gallery/{event_id}", method = RequestMethod.POST)
	public ModelAndView processCreateGallery(@SessionAttribute("userkey") User userkey, 
			@ModelAttribute EventGallery eventGallery, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @PathVariable("event_id")int eventID) throws SQLException {
		eventGalleryDAO = new EventGalleryDAO();
		
		eventGallery.setEvent_id(eventID);
		
		if(eventGalleryDAO.addEventGallery(eventGallery) != null) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + eventGallery.getTitle() + " created successfully</div>");
			mav.setViewName("redirect:/user/event-galleries/" + eventID);
		}else {
			Event eventDetails = eventDAO.getEventByID(eventID);
			mav.addObject("event_details", eventDetails);
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Gallery " + eventGallery.getTitle() + " could not be created</div>");
			mav.setViewName("user/create_gallery");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/update-gallery/{event_id}/{gallery_id}", method = {RequestMethod.GET})
	public ModelAndView updateGallery(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID, @ModelAttribute EventGallery eventGallery,
			@ModelAttribute("message") String message) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("event_gallery", eventGalleryDAO.getGalleryByID(galleryID));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/edit_gallery");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-update-gallery/{event_id}/{gallery_id}", method = RequestMethod.POST)
	public ModelAndView processUpdateGallery(@SessionAttribute("userkey") User userkey, 
			@ModelAttribute EventGallery eventGallery, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @PathVariable("event_id")int eventID, 
			@PathVariable("gallery_id")int galleryID, HttpServletRequest request) throws SQLException {
		eventGalleryDAO = new EventGalleryDAO();
		
		eventGallery.setEvent_id(eventID);
		eventGallery.setEvent_gallery_id(galleryID);
		boolean isUpdated = eventGalleryDAO.updateEventGallery(eventGallery);
		
		if(isUpdated) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + eventGallery.getTitle() + " updated successfully</div>");
			mav.setViewName("redirect:/user/event-galleries/" + eventID);
		}else {
			Event eventDetails = eventDAO.getEventByID(eventID);
			mav.addObject("event_details", eventDetails);
			mav.addObject("event_gallery", eventGalleryDAO.getGalleryByID(galleryID));
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Gallery " + eventGallery.getTitle() + " could not be updated</div>");
			mav.setViewName("user/edit_gallery");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/delete-gallery/{event_id}/{gallery_id}", method = {RequestMethod.GET})
	public ModelAndView deleteGallery(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID, 
			final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		EventGallery galleryDetails = eventGalleryDAO.getGalleryByID(galleryID);
		boolean isDeleted = eventGalleryDAO.deleteEventGallery(galleryID);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event Gallery " + galleryDetails.getTitle() + " deleted successfully</div>");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event Gallery " + galleryDetails.getTitle() + " deleted successfully</div>");
		}
		
		mav.setViewName("redirect:/user/event-galleries/" + eventID);
		
		return mav;
	}
	
	//Image
	@RequestMapping(value = "/view-gallery/{event_id}/{gallery_id}", method = {RequestMethod.GET})
	public ModelAndView viewGallery(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, 
			@PathVariable("gallery_id")int galleryID) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventImageDAO = new EventImageDAO();
		eventDAO = new EventDAO();
		List<EventImage> images = null;
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		EventGallery galleryDetails = eventGalleryDAO.getGalleryByID(galleryID);
		
		if(eventImageDAO.checkIfImagesExist(galleryID)) {
			images = eventImageDAO.getAllEventImageByGallery(galleryID);
		}
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("gallery_details", galleryDetails);
		mav.addObject("images", images);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.addObject("image_path", "uploads/" + slug.generateSlug(userkey.getEmail()) + "/" + "galleries");
		mav.setViewName("user/view_gallery");
		
		return mav;
	}
	
	@RequestMapping(value = "/add-images/{event_id}/{gallery_id}", method = {RequestMethod.GET})
	public ModelAndView addImages(@SessionAttribute("userkey") User userkey, @ModelAttribute EventImage eventImage,  
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID,
			final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventImageDAO = new EventImageDAO();
		eventDAO = new EventDAO();
				
		Event eventDetails = eventDAO.getEventByID(eventID);		
		mav.addObject("event_details", eventDetails);
		mav.addObject("event_gallery", eventGalleryDAO.getGalleryByID(galleryID));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/add_images");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-add-images/{event_id}/{gallery_id}", method = {RequestMethod.POST})
	public ModelAndView processAddImages(@SessionAttribute("userkey") User userkey, @ModelAttribute EventImage eventImage,  
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID,
			final RedirectAttributes redirectAttributes, MultipartHttpServletRequest files, HttpServletRequest request) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventImageDAO = new EventImageDAO();
		eventDAO = new EventDAO();
		
		EventGallery eventGallery = eventGalleryDAO.getGalleryByID(galleryID);
		Event eventDetails = eventDAO.getEventByID(eventID);
		
		List<String> fileList = new ArrayList<>();
		Map<String, MultipartFile> fileMap = files.getFileMap();

		for (MultipartFile file : fileMap.values()) {
			if (file.isEmpty()) {
                continue; //next pls
            }
            	
			eventImage.setTitle(file.getOriginalFilename());
        	eventImage.setEvent_gallery_id(galleryID);
        	String uploadedFile = FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail()) + "/" + "galleries");
        	eventImage.setImage(uploadedFile);
        	fileList.add(uploadedFile);
        	eventImageDAO.addEventImage(eventImage);
		}
		
		if(fileList.isEmpty()) {
			mav.addObject("event_details", eventDetails);
			mav.addObject("event_gallery", eventGallery);
			mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Gallery " + eventGallery.getTitle()  + " could not be updated</div>");
			mav.setViewName("user/add_images");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + eventGallery.getTitle() + " updated successfully</div>");
			mav.setViewName("redirect:/user/view-gallery/" + eventID + "/" + galleryID);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/edit-image/{event_id}/{gallery_id}/{image_id}", method = {RequestMethod.GET})
	public ModelAndView editImage(@SessionAttribute("userkey") User userkey, @ModelAttribute EventImage eventImage, 
			ModelAndView mav, @PathVariable("event_id")int eventID, 
			@PathVariable("gallery_id")int galleryID, @PathVariable("image_id")int imageID) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventImageDAO = new EventImageDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		EventGallery galleryDetails = eventGalleryDAO.getGalleryByID(galleryID);
		EventImage imageDetails = eventImageDAO.getImageByID(imageID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("gallery_details", galleryDetails);
		mav.addObject("image_details", imageDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.addObject("image_path", "uploads/" + slug.generateSlug(userkey.getEmail()) + "/" + "galleries");
		mav.setViewName("user/edit_image");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-update-image/{event_id}/{gallery_id}/{image_id}", method = {RequestMethod.POST})
	public ModelAndView updateImage(@SessionAttribute("userkey") User userkey, @ModelAttribute EventImage eventImage,  
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID, 
			@PathVariable("image_id")int imageID, @RequestParam("imageupload") MultipartFile file, 
			HttpServletRequest request, @ModelAttribute("message") String message, final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventImageDAO = new EventImageDAO();
		
		EventImage imageDetails = eventImageDAO.getImageByID(imageID);
		
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		
		eventImage.setEvent_image_id(imageDetails.getEvent_image_id());
		eventImage.setTitle(request.getParameter("title"));
		eventImage.setEvent_gallery_id(galleryID);
		
		if(!file.isEmpty()) {
			eventImage.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail()) + "/" + "galleries"));
		}else {
			eventImage.setImage(imageDetails.getImage());
		}
		
		boolean isUpdated = eventImageDAO.updateEventImage(eventImage);
				
		if(isUpdated) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + request.getParameter("title") + " updated successfully</div>");
			mav.setViewName("redirect:/user/view-gallery/" + eventID + "/" + galleryID);
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Gallery " + request.getParameter("title") + " could not be updated</div>");
			mav.setViewName("redirect:/user/edit-image/" + eventID + "/" + galleryID + "/" + imageID);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/delete-image/{event_id}/{gallery_id}/{image_id}", method = {RequestMethod.GET})
	public ModelAndView deleteImage(@SessionAttribute("userkey") User userkey, /*BindingResult result,*/ HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("gallery_id")int galleryID, @PathVariable("image_id")int imageID,  
			final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventImageDAO = new EventImageDAO();
		EventImage image = eventImageDAO.getImageByID(imageID);
		boolean isDeleted = eventImageDAO.deleteEventImage(imageID);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + image.getTitle() + " deleted successfully</div>");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + image.getTitle() + " deleted successfully</div>");
		}
		
		mav.setViewName("redirect:/user/view-gallery/" + eventID + "/" + galleryID);
		
		return mav;
	}
	
	//Banners
	@RequestMapping(value = "/event-banners/{event_id}", method = {RequestMethod.GET})
	public ModelAndView eventBanners(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		List<EventBanner> banners = eventBannerDAO.getAllBannersByEvent(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("banners", banners);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/event_banners");
		
		return mav;
	}
	
	@RequestMapping(value = "/create-banner/{event_id}", method = {RequestMethod.GET})
	public ModelAndView createBanner(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @ModelAttribute EventBanner eventBanner,
			@ModelAttribute("message") String message) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/create_banner");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-create-banner/{event_id}", method = RequestMethod.POST)
	public ModelAndView processCreateBanner(@SessionAttribute("userkey") User userkey, 
			@ModelAttribute EventBanner eventBanner, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @PathVariable("event_id")int eventID, 
			@RequestParam("bannerimage") MultipartFile file, HttpServletRequest request) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		
		eventBanner.setEvent_id(eventID);
		eventBanner.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail())));
		
		if(eventBannerDAO.addBanner(eventBanner) != null) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + eventBanner.getTitle() + " created successfully</div>");
			mav.setViewName("redirect:/user/event-banners/" + eventID);
		}else {
			Event eventDetails = eventDAO.getEventByID(eventID);
			mav.addObject("event_details", eventDetails);
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Banner " + eventBanner.getTitle() + " could not be created</div>");
			mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
			mav.setViewName("user/create_banner");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/update-banner/{event_id}/{banner_id}", method = {RequestMethod.GET})
	public ModelAndView updateBanner(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("banner_id")int bannerID, @ModelAttribute EventBanner eventBanner,
			@ModelAttribute("message") String message) throws SQLException {
		
		eventGalleryDAO = new EventGalleryDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("event_banner", eventBannerDAO.getBannerByID(bannerID));
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/edit_banner");
		
		return mav;
	}
	
	@RequestMapping(value = "/process-update-banner/{event_id}/{banner_id}", method = RequestMethod.POST)
	public ModelAndView processUpdateBanner(@SessionAttribute("userkey") User userkey, 
			@ModelAttribute EventBanner eventBanner, ModelAndView mav, final RedirectAttributes redirectAttributes, 
			@ModelAttribute("message") String message, @PathVariable("event_id")int eventID, 
			@PathVariable("banner_id")int bannerID, HttpServletRequest request, @RequestParam("bannerimage") MultipartFile file) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		EventBanner banner = eventBannerDAO.getBannerByID(bannerID);
		
		eventBanner.setEvent_id(eventID);
		eventBanner.setBanner_id(bannerID);
		
		if(!file.isEmpty()) {
			eventBanner.setImage(FileUploadHelper.singleUpload(file, request, "uploads/" + slug.generateSlug(userkey.getEmail())));
		}else {
			eventBanner.setImage(banner.getImage());
		}
		
		if(eventBannerDAO.updateBanner(eventBanner) != null) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> " + eventBanner.getTitle() + " updated successfully</div>");
			mav.setViewName("redirect:/user/event-banners/" + eventID);
		}else {
			Event eventDetails = eventDAO.getEventByID(eventID);
			mav.addObject("event_details", eventDetails);
			mav.addObject("event_banner", eventBannerDAO.getBannerByID(bannerID));
			mav.addObject("message", "<div class=\"callout alert\"><i class=\"fas fa-exclamation-triangle\"></i> Error Event Banner " + eventBanner.getTitle() + " could not be updated</div>");
			mav.setViewName("user/edit_banner");
		}
		
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		
		return mav;
	}
	
	@RequestMapping(value = "/view-banner/{event_id}/{banner_id}", method = {RequestMethod.GET})
	public ModelAndView viewBanner(@SessionAttribute("userkey") User userkey, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("banner_id")int bannerID) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		eventDAO = new EventDAO();
		
		Event eventDetails = eventDAO.getEventByID(eventID);
		EventBanner bannerDetails = eventBannerDAO.getBannerByID(bannerID);
		
		mav.addObject("event_details", eventDetails);
		mav.addObject("banner_details", bannerDetails);
		mav.addObject("user_path", "uploads/" + slug.generateSlug(userkey.getEmail()));
		mav.setViewName("user/view_banner");
		
		return mav;
	}
	
	@RequestMapping(value = "/delete-banner/{event_id}/{banner_id}", method = {RequestMethod.GET})
	public ModelAndView deleteBanner(@SessionAttribute("userkey") User userkey, HttpServletRequest request, 
			ModelAndView mav, @PathVariable("event_id")int eventID, @PathVariable("banner_id")int bannerID, 
			final RedirectAttributes redirectAttributes) throws SQLException {
		
		eventBannerDAO = new EventBannerDAO();
		EventBanner bannerDetails = eventBannerDAO.getBannerByID(bannerID);
		boolean isDeleted = eventBannerDAO.deleteBanner(bannerID);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event Banner " + bannerDetails.getTitle() + " deleted successfully</div>");
		}else {
			redirectAttributes.addFlashAttribute("message", "<div class=\"callout success\"><i class=\"far fa-check-circle\"></i> Event Banner " + bannerDetails.getTitle() + " deleted successfully</div>");
		}
		
		mav.setViewName("redirect:/user/event-banners/" + eventID);
		
		return mav;
	}
}
