package beauj.day01.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class UserList implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject User user;

	private List<User> users = new LinkedList<>();

	public void addUser() {
		addUser(user.createCopy());
	}

	public void addUser(User user) {
		users.add(user);
	}
	public List<User> getUsers() {
		return (users);
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
