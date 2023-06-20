package it.unicas.DataCure.interceptors;

import it.unicas.DataCure.dbutil.Configuration;
import it.unicas.DataCure.dbutil.DataLoader;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.unicas.DataCure.action.LoginAction;

/**
 * The LoginInterceptor class is an interceptor that performs authentication checks on user login actions.
 * It ensures that only authenticated users can access certain actions and pages.
 */
public class LoginInterceptor implements Interceptor {

	/**
	 * Destroys the interceptor.
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("MESSAGE: destroy() called");
	}

	/**
	 * Destroys the interceptor.
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("MESSAGE: init() called");

		// Load data into the database
		DataLoader.loadDataFromFile(Configuration.getPathVariable("login_init"),"login");
	}

	/**
	 * Intercepts the action invocation and performs authentication checks.
	 *
	 * @param ai The ActionInvocation object.
	 * @return The result of the action invocation.
	 * @throws Exception if an error occurs during interception.
	 */
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		Object user = ServletActionContext.getRequest().getSession().getAttribute("loggedinUser");
		if(user == null) {
			if(ai.getAction().getClass().equals(LoginAction.class)) {
				return ai.invoke();
			}
			return "input";
		}
		return ai.invoke();
	}
}
