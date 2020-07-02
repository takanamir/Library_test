package jp.sf.amateras.javalib.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * スタブ化
 * @author TAKANAMI
 *
 */
public class StubTest {
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

	// スタブの作成
	@Test
	public void stubTest() {
		@SuppressWarnings("unchecked")
		LinkedList<String> mockedList = mock(LinkedList.class);

		// スタブの準備
		// get(0)が呼ばれたときに「first」を返すよう設定。
		when(mockedList.get(0)).thenReturn("first");
		// get(1)が呼ばれたときはRuntimeExceptionを発生させるように設定。
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		// 「first」を返す
		assertEquals("first", mockedList.get(0));

		// RuntimeExceptionが発生
		try {
			mockedList.get(1);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}

		// get(999)は定義されていない為、「null」を出力
		assertNull(mockedList.get(999));

		// スタブの検証(スタブ化したメソッドが呼び出されたかどうかの検証)
		verify(mockedList).get(0);
		verify(mockedList).get(1);
		verify(mockedList).get(999);
	}

	//  voidメソッドの例外スタブ化
	@Test(expected = RuntimeException.class)
	public void voidMethodStubbingTest() {
		@SuppressWarnings("unchecked")
		LinkedList<String> mockedList = mock(LinkedList.class);

		doThrow(new RuntimeException()).when(mockedList).clear();

		// 以下はRuntimeExceptionを投げる
		mockedList.clear();
	}
}