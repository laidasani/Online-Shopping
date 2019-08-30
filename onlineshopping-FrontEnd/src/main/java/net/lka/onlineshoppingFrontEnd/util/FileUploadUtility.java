package net.lka.onlineshoppingFrontEnd.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final Logger logger =LoggerFactory.getLogger(FileUploadUtility.class);
	private static final String ABS_PATH="C:\\Users\\laida\\git\\Online-Shopping\\onlineshopping-FrontEnd\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH="";
	
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		//get the real path
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		
		//to make sure all directories exist, create directories if DNE
		if(!new File(ABS_PATH).exists()) {
			//create directories
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create directories
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload
			file.transferTo(new File(REAL_PATH + code +".jpg"));
			//project directory upload
			file.transferTo(new File(ABS_PATH + code +".jpg"));
		}
		catch(IOException ex) {
			
		}
		
	}
}
