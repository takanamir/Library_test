package jp.sf.amateras.javalib.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * モックの作成と検証
 * @author TAKANAMI
 *
 */
public class CreateAndVerifyTest {
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

	// モックの生成サンプル
	@Test
	public void createMockTest() {
		//モックの生成
		@SuppressWarnings("unchecked")
		List<String> mockedList = mock(List.class);

		//モックオブジェクトの利用
		mockedList.add("one");
		mockedList.clear();

		//モックに対してメソッドが呼び出されたかどうかを検証する
		verify(mockedList).add("one"); // メソッドadd()が引数「one」を指定して呼び出されたか
		verify(mockedList).clear(); // メソッドclear()が呼び出されたか
	}
}