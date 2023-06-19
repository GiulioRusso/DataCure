package it.unicas.DataCure.interceptors;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ImageInterceptor implements Interceptor {

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
    public String intercept(ActionInvocation invocation) throws Exception {
        if (invocation.getProxy().getActionName().equals("updateImageAction")) {
            // Store the image path in the session before executing the update action
            String imagePath = ServletActionContext.getRequest().getParameter("imgPath");
            ServletActionContext.getRequest().getSession().setAttribute("imagePath", imagePath);
        }

        return invocation.invoke();
    }
}
