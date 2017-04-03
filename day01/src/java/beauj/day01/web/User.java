package beauj.day01.web;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class User {

	private String name;
	private Integer age;
	private String email;

	@PostConstruct
	private void init() {
		System.out.println(">>>user is created:");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">>> user is destroyed: " + name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public User createCopy() {
		User u = new User();
		u.age = age;
		u.name = name;
		u.email = email;
		return (u);
	}
	
}
