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
 * �������� ����� � ����������� ��� ������������ Task#add
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Parameterized.class)
public class TaskAddParameterizedTest {

	/**
	 * ������� �������� x ������ Task#add
	 */
	private int passValueX;

	/**
	 * ������� �������� y ������ Task#add
	 */
	private int passValueY;

	/**
	 * ��������� ��������, ������� ������ ������� ����� Task#add
	 */
	private int expectedValue;

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
	public TaskAddParameterizedTest(int passValueX, int passValueY,
			int expectedValue) {
		this.passValueX = passValueX;
		this.passValueY = passValueY;
		this.expectedValue = expectedValue;
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
		Collection<Integer[]> data = new ArrayList<Integer[]>();
		data.add(new Integer[] { 2, 3, 5 });
		data.add(new Integer[] { Integer.MAX_VALUE, 1, Integer.MIN_VALUE });
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

	/**
	 * �������� �����.
	 */
	@Test
	public void testAdd() {
		System.out.println("\ttestAdd\t-->\t[" + passValueX + ", " + passValueY + ", " + expectedValue + "]");
		int actual = task.add(passValueX, passValueY);
		Assert.assertEquals(expectedValue, actual);
	}
}
