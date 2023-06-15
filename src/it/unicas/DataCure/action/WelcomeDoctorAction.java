package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ProductManagementDAO;
import it.unicas.DataCure.pojo.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WelcomeDoctorAction extends ActionSupport{

	/**
	 * Il dottore dovrebbe vedere tutte le immagini caricate, magari per comodità identificando facilmente quelle già
	 * viste (es circondate da un rettangolo verde o rosso)
	 */

	public String execute() {
		return "success";
	}
	
}
