package jp.sf.amateras.javalib.mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * thenAnswer() メソッドを利用したスタブ化
 * @author TAKANAMI
 *
 */
public class ThenAnswerTest {
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
	public void answerSample() {
		@SuppressWarnings("unchecked")
		List<String> mock = Mockito.mock(List.class);

		// get()が呼ばれたときの挙動を定義する
		when(mock.get(anyInt())).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				Integer i = (Integer) args[0];
				if (i <= 10) {
					// 10以下だったら「aaa」を返す
					return "aaa";
				}
				// それ以外なら「bbb」を返す
				return "bbb";
			}
		});

		// 10以下なので「aaa」が返る
		assertEquals("aaa", mock.get(10));
		// 11以上なので「bbb」が返る
		assertEquals("bbb", mock.get(11));
	}
}