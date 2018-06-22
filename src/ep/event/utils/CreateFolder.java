/**
 * 
 */
package ep.event.utils;

import java.io.File;

/**
 * @author Jacob Nartey
 *
 */
public class CreateFolder {
	public String generateDashedString(String string_to_be_dashed) {
		//Using regular expression to replace all symbol with dash
		return string_to_be_dashed.replaceAll("[^A-Za-z0-9]+", "-").toLowerCase();
	}
	
	public Boolean createFolder(String path, String foldername) {
		File dir = new File(path + "/" + this.generateDashedString(foldername));
		Boolean created = false;
	    
	    //Create folder
		if (!dir.exists()) {
			created = dir.mkdir();
		}
	    
	    return created;
	}
	
	public Boolean renameFolder(String path, String old_foldername, String new_foldername) {
		Boolean isRenamed = false;
		File dir = new File(path + "/" + this.generateDashedString(old_foldername));
	    
	    if (dir.exists()) {
	    	File newDir = new File(dir.getParent() + "/" + this.generateDashedString(new_foldername));
	    	isRenamed = dir.renameTo(newDir);
	    }
	    	    
	    return isRenamed;
	}
}
