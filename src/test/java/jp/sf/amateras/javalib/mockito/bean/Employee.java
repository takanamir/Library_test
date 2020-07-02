package jp.sf.amateras.javalib.mockito.bean;

public class Employee {
	private String id;
	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}