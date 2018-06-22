package ep.event.utils;

public class OracleQueries {
	
	public static final String GETALLUSERS = "SELECT * FROM USERS ORDER BY ID DESC";	
	public static final String GETUSERBYID ="SELECT * FROM USERS "
			+ "where ID = ?";
	
	public static final String GETUSERBYSLUG ="SELECT * FROM USERS "
			+ "where SLUG = ?";
	
	public static final String GETUSERBYEMAIL = "SELECT * FROM USERS "
			+ "WHERE EMAIL = ?";
	
	public static final String SAVEUSER = "INSERT INTO USERS "
			+ "(SLUG, FULL_NAME, EMAIL, PASSWORD, PHONE, ADDRESS, DOB, COUNTRY, STATE, CITY, IMAGE, USER_ROLE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATEUSER = "UPDATE USERS SET "
			+ "SLUG = ?, FULL_NAME = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, DOB = ?, COUNTRY = ?, STATE = ?, CITY = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String UPDATEUSERPASSWORD = "UPDATE USERS SET "
			+ "PASSWORD = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String UPDATEUSERPHOTO = "UPDATE USERS SET "
			+ "IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEUSER = "DELETE FROM USERS "
			+ "WHERE ID = ?";
	
	public static final String GETALLEVENTS ="SELECT EVENTS.ID, EVENTS.SLUG, EVENTS.USER_ID, USERS.FULL_NAME, USERS.EMAIL, USERS.PHONE, USERS.USER_ROLE, EVENTS.TITLE, "
			+ "EVENTS.DESCRIPTION, EVENTS.ADDRESS, EVENTS.COUNTRY, EVENTS.STATE, EVENTS.CITY, EVENTS.START_DATE, EVENTS.END_DATE, EVENTS.IMAGE, "
			+ "EVENTS.FEATURE_EVENT, EVENTS.DATE_CREATED, EVENTS.DATE_MODIFIED FROM EVENTS "
			+ "INNER JOIN USERS ON USERS.ID = EVENTS.USER_ID ORDER BY EVENTS.START_DATE DESC";
	
	public static final String GETEVENTBYID ="SELECT * FROM EVENTS "
			+ "where id = ?";
	
	public static final String GETEVENTBYSLUG ="SELECT * FROM EVENTS "
			+ "where slug = ?";
	
	public static final String GETEVENTSBYUSER ="SELECT * FROM EVENTS "
			+ "where user_id = ? ORDER BY EVENTS.START_DATE DESC";
	
	public static final String GETEVENTSBYUSERLIMIT ="SELECT * FROM EVENTS "
			+ "where user_id = ? ORDER BY EVENTS.START_DATE DESC FETCH NEXT ? ROWS ONLY";
	
	public static final String GETLASTESTEVENT = "SELECT * FROM EVENTS "
			+ "WHERE USER_ID = ? AND START_DATE = (SELECT MAX(START_DATE) FROM EVENTS)";
	
	public static final String SAVEEVENT = "INSERT INTO EVENTS "
			+ "(SLUG, USER_ID, TITLE, DESCRIPTION, ADDRESS, COUNTRY, STATE, CITY, START_DATE, END_DATE, IMAGE, FEATURE_EVENT, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATEEVENT = "UPDATE EVENTS SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, ADDRESS = ?, COUNTRY = ?, STATE = ?, CITY = ?, START_DATE = ?, END_DATE = ?, IMAGE = ?, FEATURE_EVENT = ?, DATE_MODIFIED = ? "
			+ "WHERE EVENTS.ID = ?";
	
	public static final String DELETEEVENT = "DELETE FROM EVENTS "
			+ "WHERE EVENTS.ID = ?";
	
	public static final String GETALLEVENTGALLERIES = "SELECT * FROM EVENT_GALLERIES ORDER BY ID DESC";
	public static final String GETGALLERYBYID ="SELECT * FROM EVENT_GALLERIES "
			+ "where id = ?";
	
	public static final String GETGALLERYBYSLUG ="SELECT * FROM EVENT_GALLERIES "
			+ "where slug = ?";
	
	public static final String GETGALLERYBYEVENT ="SELECT * FROM EVENT_GALLERIES "
			+ "where event_id = ? ORDER BY ID DESC";
	
//	public static final String GETGALLERYBYEVENTBYFIRSTIMAGE = "SELECT * FROM EVENT_GALLERIES "
//			+ "JOIN EVENT_IMAGES ON EVENT_IMAGES.EVENT_GALLERY_ID = "
//			+ "(SELECT EVENT_IMAGES.EVENT_GALLERY_ID FROM EVENT_IMAGES WHERE EVENT_IMAGES.EVENT_GALLERY_ID = EVENT_GALLERIES.ID ORDER BY EVENT_IMAGES.ID ASC) "
//			+ "WHERE EVENT_GALLERIES.EVENT_ID = ?";
	
	public static final String GETGALLERYBYEVENTBYFIRSTIMAGE = "SELECT * FROM EVENT_GALLERIES "
			+ "JOIN (SELECT * FROM EVENT_IMAGES WHERE ID IN (SELECT MAX(ID) FROM EVENT_IMAGES GROUP_BY EVENT_IMAGES.EVENT_GALLERY_ID)"
			+ ") AS RECENT_IMAGES ON EVENT_GALLERIES.ID = RECENT_IMAGES.EVENT_GALLERY_ID"
			+ "WHERE EVENT_GALLERIES.EVENT_ID = ?";
	
	public static final String SAVEGALLERY = "INSERT INTO EVENT_GALLERIES "
			+ "(SLUG, EVENT_ID, TITLE, DESCRIPTION, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?)";
	
	public static final String UPDATEGALLERY = "UPDATE EVENT_GALLERIES SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEGALLERY = "DELETE FROM EVENT_GALLERIES ORDER BY ID DESC"
			+ "WHERE ID = ?";
	
	public static final String GETALLEVENTIMAGES = "SELECT * FROM EVENT_IMAGES ORDER BY ID DESC";
	
	public static final String GETIMAGEBYID ="SELECT * FROM EVENT_IMAGES "
			+ "where id = ?";
	
	public static final String GETIMAGEBYSLUG ="SELECT * FROM EVENT_IMAGES "
			+ "where slug = ?";
	
	public static final String GETIMAGEBYGALLERY = "SELECT * FROM EVENT_IMAGES "
			+ "where event_gallery_id = ? ORDER BY ID DESC";
	
	public static final String SAVEIMAGE = "INSERT INTO EVENT_IMAGES "
			+ "(SLUG, EVENT_GALLERY_ID, TITLE, IMAGE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?)";
	
	public static final String UPDATEIMAGE = "UPDATE EVENT_IMAGES SET "
			+ "SLUG = ?, TITLE = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEIMAGE = "DELETE FROM EVENT_IMAGES "
			+ "WHERE ID = ?";
	
	public static final String GETALLEVENTWISHLISTS = "SELECT * FROM EVENT_WISHLIST ORDER BY ID DESC";
	
	public static final String GETWISHLISTBYID ="SELECT * FROM EVENT_WISHLIST "
			+ "where ID = ?";
	
	public static final String GETWISHLISTBYSLUG ="SELECT * FROM EVENT_WISHLIST "
			+ "where SLUG = ?";
	
	public static final String GETWISHLISTBYEVENT ="SELECT * FROM EVENT_WISHLIST "
			+ "where EVENT_ID = ? ORDER BY ID DESC";
	
	public static final String SAVEWISHLIST = "INSERT INTO EVENT_WISHLIST "
			+ "(SLUG, EVENT_ID, TITLE, DESCRIPTION, IMAGE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	public static final String UPDATEWISHLIST = "UPDATE EVENT_WISHLIST SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEWISHLIST = "DELETE FROM EVENT_WISHLIST "
			+ "WHERE ID = ?";
	
	public static final String GETALLEVENTBANNERS = "SELECT * FROM EVENT_BANNERS ORDER BY ID DESC";
	
	public static final String GETEVENTBANNERBYID ="SELECT * FROM EVENT_BANNERS "
			+ "where ID = ?";
	
	public static final String GETEVENTBANNERBYSLUG ="SELECT * FROM EVENT_BANNERS "
			+ "where SLUG = ?";
	
	public static final String GETBANNERSBYEVENT ="SELECT * FROM EVENT_BANNERS "
			+ "where EVENT_ID = ?";
	
	public static final String SAVEEVENTBANNER = "INSERT INTO EVENT_BANNERS "
			+ "(SLUG, EVENT_ID, TITLE, DESCRIPTION, IMAGE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	public static final String UPDATEEVENTBANNER = "UPDATE EVENT_BANNERS SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEEVENTBANNER = "DELETE FROM EVENT_BANNERS "
			+ "WHERE ID = ?";
	
	public static final String GETALLBANNERS = "SELECT * FROM BANNERS ORDER BY ID DESC";
	
	public static final String GETBANNERBYID ="SELECT * FROM BANNERS "
			+ "where ID = ?";
	
	public static final String GETBANNERBYSLUG ="SELECT * FROM BANNERS "
			+ "where SLUG = ?";
	
	public static final String GETBANNERSBYUSER ="SELECT * FROM BANNERS "
			+ "where user_id = ? ORDER BY ID DESC";
	
	public static final String SAVEBANNER = "INSERT INTO BANNERS "
			+ "(SLUG, USER_ID, TITLE, DESCRIPTION, IMAGE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	public static final String UPDATEBANNER = "UPDATE BANNERS SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEBANNER = "DELETE FROM BANNERS "
			+ "WHERE ID = ?";
	
	public static final String GETALLUSERPROFILES = "SELECT * FROM USERS_PROFILES ORDER BY ID DESC";
	
	public static final String GETUSERPROFILEBYID ="SELECT * FROM USERS_PROFILES "
			+ "where ID = ?";
	
	public static final String GETUSERPROFILEBYSLUG ="SELECT * FROM USERS_PROFILES "
			+ "where SLUG = ?";
	
	public static final String GETPROFILESBYUSER ="SELECT * FROM USERS_PROFILES "
			+ "where user_id = ?";
	
	public static final String SAVEUSERPROFILE = "INSERT INTO USERS_PROFILES "
			+ "(SLUG, USER_ID, TITLE, DESCRIPTION, IMAGE, DATE_CREATED, DATE_MODIFIED) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	public static final String UPDATEUSERPROFILE = "UPDATE USERS_PROFILES SET "
			+ "SLUG = ?, TITLE = ?, DESCRIPTION = ?, IMAGE = ?, DATE_MODIFIED = ? "
			+ "WHERE ID = ?";
	
	public static final String DELETEUSERPROFILE = "DELETE FROM USERS_PROFILES "
			+ "WHERE ID = ?";
}
