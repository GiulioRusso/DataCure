package it.unicas.DataCure.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{

	public String execute( ) {
		ServletActionContext.getRequest().getSession().invalidate();
		return INPUT;
	}
}
