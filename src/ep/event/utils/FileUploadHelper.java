package ep.event.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {
	
	protected FileUploadHelper() {
		super();
	}
	
	public static String singleUpload(MultipartFile file, HttpServletRequest request, String targetFolder) {
		String filename = null;
		BufferedOutputStream stream = null;
		
		try {
			if(!file.isEmpty()) {
				String appPath = request.getServletContext().getRealPath("");
				filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				String rootPath = appPath;
				
				File dir = new File(rootPath + File.separator + targetFolder);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				File uploadFile = new File(dir.getAbsolutePath() + File.separator + filename);
				stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				stream.write(bytes);
				
				//Small thumb
				File thumbdir = new File(rootPath + File.separator + targetFolder + File.separator + "small");
				
				if(!thumbdir.exists()) {
					thumbdir.mkdirs();
				}
				
				File uploadFileThumb = new File(thumbdir.getAbsolutePath() + File.separator + filename);
				
				javaxt.io.Image thumb = new javaxt.io.Image(uploadFile);
				if(thumb.getWidth() > 420 || thumb.getHeight() > 420) {
					thumb.resize(420, 420, true);
				}
				
				if(thumb.getWidth() > 310 || thumb.getHeight() > 250) {
					thumb.crop(0, 0, 310, 250);
				}
				
				thumb.trim();
                thumb.saveAs(uploadFileThumb);
                
                //Passport thumb
				File passportThumbdir = new File(rootPath + File.separator + targetFolder + File.separator + "passport");
				
				if(!passportThumbdir.exists()) {
					passportThumbdir.mkdirs();
				}
				
				File uploadFilePassportThumb = new File(passportThumbdir.getAbsolutePath() + File.separator + filename);
				
				javaxt.io.Image passportThumb = new javaxt.io.Image(uploadFile);
				
				if(passportThumb.getWidth() > 420) {
					passportThumb.setWidth(420);
				}
				
				if(passportThumb.getHeight() > 420) {
					passportThumb.setHeight(420);
				}
				
				if(passportThumb.getWidth() > 200 && passportThumb.getHeight() > 200) {
					int x = (passportThumb.getWidth() - 180)/2;
					int y = (passportThumb.getHeight() - 180)/2;
					passportThumb.crop(x, y, 180, 180);
				}
				
				passportThumb.trim();
				passportThumb.saveAs(uploadFilePassportThumb);
                
                //Medium thumb
				File thumbdirMedium = new File(rootPath + File.separator + targetFolder + File.separator + "medium");
				
				if(!thumbdirMedium.exists()) {
					thumbdirMedium.mkdirs();
				}
				
				File uploadFileThumbMedium = new File(thumbdirMedium.getAbsolutePath() + File.separator + filename);
				
				javaxt.io.Image thumbMedium = new javaxt.io.Image(uploadFile); 
				thumbMedium.resize(800, 600, true);
				thumbMedium.saveAs(uploadFileThumbMedium);
				
				//Banner thumb
				File bannerThumbdir = new File(rootPath + File.separator + targetFolder + File.separator + "banners");
				
				if(!bannerThumbdir.exists()) {
					bannerThumbdir.mkdirs();
				}
				
				File uploadFileBannerThumb = new File(bannerThumbdir.getAbsolutePath() + File.separator + filename);
				
				javaxt.io.Image bannerThumb = new javaxt.io.Image(uploadFile);
				
				if(bannerThumb.getWidth() > 1600) {
					bannerThumb.setWidth(1600);
				}
				
				if(passportThumb.getHeight() > 500) {
					passportThumb.setHeight(500);
				}
				
				if(bannerThumb.getWidth() > 1600 && bannerThumb.getHeight() > 500) {
					int x = (bannerThumb.getWidth() - 1600)/2;
					int y = (bannerThumb.getHeight() - 500)/2;
					bannerThumb.crop(x, y, 1600, 500);
				}
				
				bannerThumb.trim();
				bannerThumb.saveAs(uploadFileBannerThumb);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
		    try {   
		        if(stream != null) stream.close();
		    } catch(IOException e) {
		    	System.out.println(e.getMessage());
		    }
		}
		
		return filename;
	}
	
	public static List<String> uploadMultipleUFiles(List<MultipartFile> files, HttpServletRequest request, String targetFolder) {
		List<String> filenames = new ArrayList<>();
		
		try {
			if(!files.isEmpty()) {
				BufferedOutputStream stream = null;
				String appPath = request.getServletContext().getRealPath("");
				
				for(MultipartFile file : files) {
					String filename = "";
					
					filename = file.getOriginalFilename();
					byte[] bytes = file.getBytes();
					String rootPath = appPath;
					
					File dir = new File(rootPath + File.separator + targetFolder);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					
					File uploadFile = new File(dir.getAbsolutePath() + File.separator + filename);
					stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
					stream.write(bytes);
					
					//Small thumb
					File thumbdir = new File(rootPath + File.separator + targetFolder + File.separator + "small");
					
					if(!thumbdir.exists()) {
						thumbdir.mkdirs();
					}
					
					File uploadFileThumb = new File(thumbdir.getAbsolutePath() + File.separator + filename);
					
					javaxt.io.Image thumb = new javaxt.io.Image(uploadFile);
					thumb.resize(420, 420, true);
					thumb.crop(0, 0, 310, 250);
					thumb.trim();
	                thumb.saveAs(uploadFileThumb);
	                
	                //Passport thumb
					File passportThumbdir = new File(rootPath + File.separator + targetFolder + File.separator + "passport");
					
					if(!passportThumbdir.exists()) {
						passportThumbdir.mkdirs();
					}
					
					File uploadFilePassportThumb = new File(passportThumbdir.getAbsolutePath() + File.separator + filename);
										
					javaxt.io.Image passportThumb = new javaxt.io.Image(uploadFile);
					
					if(passportThumb.getWidth() > 420) {
						passportThumb.setWidth(420);
					}
					
					if(passportThumb.getHeight() > 420) {
						passportThumb.setHeight(420);
					}
					
					if(passportThumb.getWidth() > 200 && passportThumb.getHeight() > 200) {
						int x = (passportThumb.getWidth() - 180)/2;
						int y = (passportThumb.getHeight() - 180)/2;
						passportThumb.crop(x, y, 180, 180);
					}
					
					passportThumb.trim();
					passportThumb.saveAs(uploadFilePassportThumb);
	                
	                //Medium thumb
					File thumbdirMedium = new File(rootPath + File.separator + targetFolder + File.separator + "medium");
					
					if(!thumbdirMedium.exists()) {
						thumbdirMedium.mkdirs();
					}
					
					File uploadFileThumbMedium = new File(thumbdirMedium.getAbsolutePath() + File.separator + filename);
					
					javaxt.io.Image thumbMedium = new javaxt.io.Image(uploadFile); 
					thumbMedium.resize(800, 600, true);
					thumbMedium.saveAs(uploadFileThumbMedium);
					
					stream.close();
					
					filenames.add(filename);
				}
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			filenames = null;
		}
		
		return filenames;
	}
}
