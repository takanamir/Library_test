package jp.sf.amateras.javalib.mockito;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.Timeout;
import org.mockito.verification.VerificationMode;

import jp.sf.amateras.javalib.mockito.bean.Foo;

/**
 * タイムアウト設定
 * @author TAKANAMI
 *
 */
public class TimeoutTest {
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
	public void timeoutTest() {
		Foo mock = mock(Foo.class);

		mock.someMethod();

		// 100msec以内にメソッドが呼び出されていることを検証する
		verify(mock, timeout(100)).someMethod();
//		verify(mock, timeout(100).times(1)).someMethod();

		mock.someMethod();

		// 指定時間内に２回メソッド呼び出されていることを検証する
		verify(mock, timeout(100).times(2)).someMethod();

		// 指定時間内に少なくとも２回呼び出されていることを検証する
		verify(mock, timeout(100).atLeast(2)).someMethod();

		// カスタマイズしたorg.mockito.verification.VerificationModeを指定することも可能
		VerificationMode yourOwnVerificationMode = new VerificationMode() {
			public void verify(VerificationData data) {
				// 独自のロジックを記述
			}
		};
		verify(mock, new Timeout(100, yourOwnVerificationMode)).someMethod();
	}
}