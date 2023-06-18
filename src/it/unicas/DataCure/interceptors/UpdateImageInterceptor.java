package it.unicas.DataCure.interceptors;

import it.unicas.DataCure.dbutil.DataLoader;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.unicas.DataCure.action.LoginAction;

public class UpdateImageInterceptor implements Interceptor {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        System.out.println("MESSAGE: init() called");

    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
