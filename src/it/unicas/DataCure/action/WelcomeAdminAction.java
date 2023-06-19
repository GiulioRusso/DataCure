package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.dbutil.Configuration;
import it.unicas.DataCure.pojo.Login;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class WelcomeAdminAction extends ActionSupport{

	private List<Login> userList;

	private String userID;

	private String userPassword;

	public void initializeUsers() {
		userList = LoginDAO.getAllUsers();
		System.out.println("MESSAGE: Login instances");
		System.out.println(userList);


	}

	public String execute() {
		initializeUsers();

		try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"))) {
			writer.write(LocalDate.now() + " " + userID + " has logged in DataCure");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file: " + e.getMessage());
		}

		return "success";
	}

	public List<Login> getUserList() {
		return userList;
	}

	public void setUserList(List<Login> userList) {
		this.userList = userList;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
