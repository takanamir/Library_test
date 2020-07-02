package jp.sf.amateras.javalib.mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.matchers.Equals;

import jp.sf.amateras.javalib.mockito.bean.Sample;

/**
 * Matcherの利用例
 * @author TAKANAMI
 *
 */
public class MatcherTest {
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

	// Matherによる引数の指定
	@Test
	public void matcherTest() {
		@SuppressWarnings("unchecked")
		LinkedList<String> mockedList = mock(LinkedList.class);

		// 1.mockitoが用意するビルトインのMatcherを指定してスタブ設定。
		when(mockedList.get(anyInt())).thenReturn("zzz");

		// 2.カスタム引数Matcherを利用してスタブ設定。
		when(mockedList.contains(argThat(new Equals("aaa")))).thenReturn(true);

		// 3.hamcrestを利用してスタブ設定。
		when(mockedList.contains(argThat(is(not("bbb"))))).thenReturn(true);

		// 「zzz」を返す。
		assertEquals("zzz", mockedList.get(999));
		// 「aaa」は含まれるのでtrue。
		assertTrue(mockedList.contains("aaa"));
		// 「bbb」は含まれないのでfalse。
		assertFalse(mockedList.contains("bbb"));
	}

	@Test
	public void matcherTest2() {
		Sample mock = mock(Sample.class);

		// 正しい：全ての引数をmatcherで指定している
		when(mock.someMethod(anyInt(), eq(3))).thenReturn(0);

		// 間違い：第1引数がmatcherであるのに第2引数をmatcherで指定していない。
//		when(mock.someMethod(anyInt(), 3)).thenReturn(0);
	}
}