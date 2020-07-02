package jp.sf.amateras.javalib.mockito;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * モックのシリアライズ
 * @author TAKANAMI
 *
 */
public class SerializeMockTest {
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
	@SuppressWarnings({ "unchecked", "unused" })
	public void serializableTest() {
		// シリアライズ化可能モックの生成
		List<String> serializableMock = mock(List.class, withSettings().serializable());

		// シリアライズ化可能スパイオブジェクトの生成
		List<String> list = new ArrayList<String>();
		List<String> spy = mock(
				ArrayList.class,
				withSettings().spiedInstance(list)
						.defaultAnswer(CALLS_REAL_METHODS).serializable());

	}
}