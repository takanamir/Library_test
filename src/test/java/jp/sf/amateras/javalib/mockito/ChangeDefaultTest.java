package jp.sf.amateras.javalib.mockito;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import jp.sf.amateras.javalib.mockito.bean.Foo;

/**
 * スタブ化されていない呼び出しを返すデフォルト値の変更
 * @author TAKANAMI
 *
 */
public class ChangeDefaultTest {
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
	@SuppressWarnings("unused")
	public void changeDefaultReturn() {
		Foo mock = mock(Foo.class, Mockito.RETURNS_DEFAULTS); // グローバル定義に従って0やnull、コレクションの場合は空のコレクションを返す
//		Foo mock = mock(Foo.class, Mockito.RETURNS_SMART_NULLS); // メソッドの戻り値がnullの場合、わかりやすいメッセージをスタックとレースに出力する
//		Foo mock = mock(Foo.class, Mockito.RETURNS_MOCKS);       // 空のモックを返す
//		Foo mock = mock(Foo.class, Mockito.RETURNS_DEEP_STUBS);  // 再帰的にスタブを返す
//		Foo mock = mock(Foo.class, Mockito.CALLS_REAL_METHODS);  // 実メソッドを呼び出す
	}
}