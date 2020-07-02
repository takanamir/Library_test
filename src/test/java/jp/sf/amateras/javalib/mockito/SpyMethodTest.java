package jp.sf.amateras.javalib.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * spy() メソッドの利用例
 * @author TAKANAMI
 *
 */
public class SpyMethodTest {
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
	public void spyTest() {
		List<String> list = new LinkedList<String>();
		// spyの引数に実オブジェクトを渡す
		List<String> spy = spy(list);

		// size()メソッドをスタブ化する
		when(spy.size()).thenReturn(100);

		// 実メソッド呼び出し
		spy.add("one");
		spy.add("two");

		// 「one」が返る
		assertEquals("one", spy.get(0));

		// スタブ化されたsize()は「100」が返る
		assertEquals(100, spy.size());

		// 検証も可能
		verify(spy).add("one");
		verify(spy).add("two");
		verify(spy).size();
	}

	@Test
	public void spyTest2() {
		List<String> list = new LinkedList<String>();
		List<String> spy = spy(list);

		// 実メソッドが呼び出されてしまうため、IndexOutOfBoundsExceptionを投げる
//		when(spy.get(0)).thenReturn("foo");

		// スタブ化するにはdoReturn()を使う必要がある。
		doReturn("foo").when(spy).get(0);
	}

	@Test
	public void particalMockTest() {
		// 通常のモック作成
		@SuppressWarnings("unchecked")
		List<String> mock = mock(LinkedList.class);

		// size()メソッドは実メソッドを呼び出すよう設定する
		when(mock.size()).thenCallRealMethod();

		assertEquals(0, mock.size());
	}
}