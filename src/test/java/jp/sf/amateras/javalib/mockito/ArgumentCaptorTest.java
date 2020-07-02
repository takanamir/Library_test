package jp.sf.amateras.javalib.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import jp.sf.amateras.javalib.mockito.bean.Employee;
import jp.sf.amateras.javalib.mockito.bean.Foo;

/**
 * 引数をキャプチャする
 * @author TAKANAMI
 *
 */
public class ArgumentCaptorTest {
	@Before
	public void before() {
		// 前処理
	}

	@After
	public void after() {
		// 後処理
	}

	@BeforeClass
	public static void beforClass() {
		// 前処理
	}

	@AfterClass
	public static void afterClass() {
		// 後処理
	}

	@Test
	public void argumentCaptorTest() {
		Foo mock = mock(Foo.class);
		mock.doSomething(new Employee("taro"));

		// ArgumentoCaptorの生成
		ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);

		// 引数にArgumentoCaptorを指定し、呼び出しの検証を行う。
		// これを実行する前に当該メソッドを呼んでいる前提です。
		verify(mock).doSomething(argument.capture());
		// キャプチャした引数の値でアサーションを行う。
		assertEquals("taro", argument.getValue().getName());
	}
}