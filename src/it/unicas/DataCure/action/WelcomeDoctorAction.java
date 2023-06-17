package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.dao.ProductManagementDAO;
import it.unicas.DataCure.pojo.Image;
import it.unicas.DataCure.pojo.Login;
import it.unicas.DataCure.pojo.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class WelcomeDoctorAction extends ActionSupport{

	/**
	 * Il dottore dovrebbe vedere tutte le immagini caricate, magari per comodità identificando facilmente quelle già
	 * viste (es circondate da un rettangolo verde o rosso)
	 */

	private List<Image> imageList;

	public void loadImages() {
		imageList = ImageDAO.getAllImages();
		System.out.println("MESSAGE: Image instances:");
		printImagesInfo(imageList);
	}

	public String execute() {
		loadImages();
		return "success";
	}

	public void printImagesInfo(List<Image> imageList) {

		for(Image j : imageList){
			System.out.println(j.getIdImage());
			System.out.println(j.getUploadDate());
			System.out.println(j.isLabeled());
			System.out.println(j.getOperatorDescription());
			System.out.println((j.getDoctorDescription()));
		}
	}

	public List<Image> getImageList() {
		return imageList;
	}
	
}
