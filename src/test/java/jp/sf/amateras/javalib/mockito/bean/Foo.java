package jp.sf.amateras.javalib.mockito.bean;

public class Foo {
	public void doSomething(Employee bar) {
		this.doOtherthing(bar);
	}

	void doOtherthing(Employee bar) {
		bar.setId("test1");
		bar.setName("hoge");
	}

	public String toString() {
		return "hoge";
	}

	public void someMethod() {
	}
}