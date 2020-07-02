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
import org.mockito.InOrder;

import jp.sf.amateras.javalib.mockito.bean.Sample;

/**
 * 連続呼び出しのスタブ化
 * @author TAKANAMI
 *
 */
public class MultiInvokeTest {
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
	public void timesCallTest() {
		Sample mock = mock(Sample.class);

		// 1回目にRuntimeExceptionをなげ、2回目に「second」を返すよう定義
		when(mock.someMethod("arg"))
				.thenThrow(new RuntimeException())
				.thenReturn("second");

		try {
			// 一度目の呼び出し。RuntimeExceptionを投げる
			mock.someMethod("arg");
			fail();
		} catch (RuntimeException e) {

		}
		// 2回目の呼び出し。「second」を返す
		assertEquals("second", mock.someMethod("arg"));
		// 3回目の呼び出し。「second」を返す（最後のスタブ化が有効）
		assertEquals("second", mock.someMethod("arg"));

		//続けて書く書き方（1回目に「one」2回目に「two」3回目に「three」を返す）
		when(mock.someMethod("arg")).thenReturn("one", "two", "three");

		// voidを返すメソッドの場合(1回目になにも起こさず、2回目にRuntimeException)
		doNothing().doThrow(new RuntimeException())
				.when(mock).someVoidMethod();

		assertEquals("one", mock.someMethod("arg"));
		assertEquals("two", mock.someMethod("arg"));
		assertEquals("three", mock.someMethod("arg"));
		assertEquals("three", mock.someMethod("arg"));
		assertEquals("three", mock.someMethod("arg"));
	}

	// 正確な呼び出し数を検証する。at least x / never
	@Test
	public void timesTest() {
		@SuppressWarnings("unchecked")
		LinkedList<String> mockedList = mock(LinkedList.class);
		// モックを使う
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		// モックの検証
		// 次の二つの検証は同意。デフォルトでは、times(1)が適用される。
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// 「twice」を引数に2回呼び出されたことを検証する。
		verify(mockedList, times(2)).add("twice");

		// 1回も呼び出されていないことを検証。never()はtimes(0)と同意。
		verify(mockedList, never()).add("never happened");

		// 少なくとも1回呼び出されていることを検証
		verify(mockedList, atLeastOnce()).add("once");
		// 少なくとも2回呼び出されていることを検証
		verify(mockedList, atLeast(2)).add("twice");
		// 多くとも2回以下の呼び出しであることを検証
		verify(mockedList, atMost(2)).add("twice");
	}

	// 順序の検証
	@Test
	@SuppressWarnings("unchecked")
	public void inOrderTest() {
		// 順序の検証をしたいモック
		List<String> mock1 = mock(List.class);
		List<String> mock2 = mock(List.class);
		// 順序の検証に関係ないモック
		List<String> mock3 = mock(List.class);

		// モックの呼び出し
		mock3.add("aaa");
		mock1.add("first");
		mock2.add("second");
		mock1.add("third");

		// 順序検証が必要なモックのみ指定してInOrderインスタンスを作成。
		InOrder inOrder = inOrder(mock1, mock2);

		// mock1("first"), mock2("second"), mock3("third")
		// の順で呼ばれることを検証する。
		inOrder.verify(mock1).add("first");
		inOrder.verify(mock2).add("second");
		inOrder.verify(mock1).add("third");
	}
}