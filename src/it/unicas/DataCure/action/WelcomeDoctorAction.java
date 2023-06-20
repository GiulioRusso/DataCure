package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.pojo.Image;
import java.util.List;

/**
 * The WelcomeDoctorAction class is an action class that handles the welcome page for the doctor user.
 * It retrieves the list of images from the database and loads them for display.
 */
public class WelcomeDoctorAction extends ActionSupport{

	private List<Image> imageList;	// Stores the list of images.

	/**
	 * Loads the list of images by retrieving them from the database.
	 */
	public void loadImages() {
		// Retrieve all images
		imageList = ImageDAO.getAllImages();
	}

	/**
	 * Executes the welcome doctor action and loads the list of images.
	 *
	 * @return The status code indicating the success of the action.
	 */
	public String execute() {
		// Load all the images into the database
		loadImages();

		// Check it is not null
		if(imageList != null)
			return "success";
		else {
			System.out.println("An error during fetch has occurred...");
			return "error";
		}
	}

	/**
	 * Prints the information of the images in the specified list.
	 *
	 * @param imageList The list of images to print the information for.
	 */
	public void printImagesInfo(List<Image> imageList) {

		for(Image j : imageList){
			System.out.println(j.getIdImage());
			System.out.println(j.getUploadDate());
			System.out.println(j.isLabeled());
			System.out.println(j.getOperatorDescription());
			System.out.println((j.getDoctorDescription()));
		}
	}

	/**
	 * Gets the list of images.
	 *
	 * @return The list of images.
	 */
	public List<Image> getImageList() {
		return imageList;
	}
	
}
