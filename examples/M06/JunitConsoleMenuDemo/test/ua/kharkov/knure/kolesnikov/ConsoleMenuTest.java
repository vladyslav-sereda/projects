package ua.kharkov.knure.kolesnikov;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import ua.kharkov.knure.kolesnikov.ConsoleMenu;

/**
 * Demonstrates console input testing.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class ConsoleMenuTest {

	@Test
	public void testIllegalInput() {
		ByteArrayInputStream in = new ByteArrayInputStream(
				"illegal input".getBytes());
		System.setIn(in);
		ConsoleMenu.main(new String[0]);
	}

	@Test
	public void testAAA() {
		ByteArrayInputStream in = new ByteArrayInputStream("AAA".getBytes());
		System.setIn(in);
		ConsoleMenu.main(new String[0]);
	}

	@Test
	public void test555() {
		ByteArrayInputStream in = new ByteArrayInputStream("555".getBytes());
		System.setIn(in);
		ConsoleMenu.main(new String[0]);
	}

	/**
	 * Class Demo has the implicit default constructor. We must run this
	 * constructor to get 100% code coverage.
	 */
	@Test
	public void testDefaultConstructor() {
		new ConsoleMenu();
	}

}
