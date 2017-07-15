package ua.knure.kharkov.kolesnikov.JUnitParameterizedDemo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * �������� ����� � ����������� ��� ������������ Task#div
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Parameterized.class)
public class TaskDivParameterizedTest {

	/**
	 * ������� �������� x ������ Task#div
	 */
	private int passValueX;

	/**
	 * ������� �������� y ������ Task#div
	 */
	private int passValueY;

	/**
	 * ��������� ��������, ������� ������ ������� ����� Task#div
	 */
	private int expectedValue;

	/**
	 * ��� ����������, ������� ������ ��������� ����� Task#div
	 */
	private Class<Throwable> expectedException;

	/**
	 * ����, ������� ����� ������������ � �������� ������� (������ ������ Task).
	 */
	private Task task;

	/**
	 * ������� ������� ������������ �����������.<br/>
	 * JUnit ������� ��������� ������ ��� ������� ������ ����������, ���������
	 * �� � �����������.
	 * 
	 * @param passValueX
	 * @param passValueY
	 * @param expectedValue
	 */
	public TaskDivParameterizedTest(int passValueX, int passValueY,
			int expectedValue, Class<Throwable> expectedException) {
		this.passValueX = passValueX;
		this.passValueY = passValueY;
		this.expectedValue = expectedValue;
		this.expectedException = expectedException;
	}

	/**
	 * �����, ������� ������������ �������� ������� ����������������� junit
	 * ������.<br/>
	 * ������� ������ �����������. ������ ���� ����������� @Parameters<br/>
	 * �������� ������ - �����.
	 * 
	 * @return ��������� (Collection), ������ ������� ������� �������� ������
	 *         �������� ����������, �������, � ���� �������, ����� �������� �
	 *         �����������.
	 */
	@Parameters
	public static Collection<?> getParameters() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 6, 3, 2, null });
		data.add(new Object[] { 7, 3, 2, null });
		data.add(new Object[] { 7, 0, 0, ArithmeticException.class });

		return data;
	}
	
	/**
	 * ����� �������� ���������� ��������.<br/>
	 * �������� �������� - �������� ���������. ������ ����� ����� ������ ����
	 * ��� ����� �������� ���� �������� �������.<br/>
	 */
	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * ����� �������� �������� ��������. ����� ������ �� ������ ���� �����
	 * ������ ������� ������� ��������� ������.
	 */
	@Before
	public void setUp() {
		System.out.print("setUp");

		// ����� ������� ������� ��������� ������
		// ����� ������ ����� ������ task
		task = new Task();
		System.out.println("\t-->\t" + task);
	}

	@Test
	public void testDiv() {
		System.out.println("\ttestDiv\t-->\t[" + passValueX + ", " + passValueY
				+ ", " + expectedValue + ", " + expectedException + "]");

		if (expectedException != null) {
			// ���������������� ����������� �����
			try {				
				// ����� ����� ������ �������� � ������� ����������
				// ������� ����� ��� expectedException.getName()
				task.div(passValueX, passValueY);
				
				// ���� ����� ��������� � ������� ���������� �� ���������
				// �� ���� �� ������� ("������" ����)
				Assert.fail("Expected exception: " + expectedException);
			} catch (Throwable t) {
				// ���� ���������� ��������� ��������� ��� ���
				// � � ������, ���� ���� ��������� ����������
				// �� ��� ��� �� ������ � ���������, �� ���� �� �������
				// ("������" ����)
				if (t.getClass() != expectedException)
					Assert.fail("Expected exception: " + expectedException);
			}
		} else {
			// ���������������� ���������� ������
			int actual = task.div(passValueX, passValueY);
			Assert.assertEquals(expectedValue, actual);
		}
	}
}
