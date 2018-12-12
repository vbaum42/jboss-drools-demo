package rs.devoteam.drivinglicense;

import java.io.Serializable;

public class Applicant implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private boolean valid;
	
	public Applicant(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		this.valid = true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
