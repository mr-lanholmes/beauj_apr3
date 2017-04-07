package day05.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class LoginView implements Serializable {

	private String username;
	private String password;
	private Integer count = 0;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public String performLogout() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();

		HttpSession session = req.getSession();
		session.invalidate();

		return ("/login");
	}

	public String performLogin() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();


		try {
			req.login(username, password);
		} catch (ServletException ex) {
			count++;
			FacesMessage msg = new FacesMessage("Incorrect login");
			ctx.addMessage(null, msg);
			return (null);
		}
		System.out.println(">>> remote user name is " + ctx.getExternalContext().getRemoteUser());

		return ("restricted/topsecret");
	}

	
}
