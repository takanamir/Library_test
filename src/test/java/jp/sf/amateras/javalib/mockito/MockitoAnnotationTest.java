package jp.sf.amateras.javalib.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.sf.amateras.javalib.mockito.bean.Foo;

/**
 * アノテーションによるモック生成
 * @author TAKANAMI
 *
 */
public class MockitoAnnotationTest {
	@Mock
	private Foo mock;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test1() {
		mock.someMethod();
	}
}