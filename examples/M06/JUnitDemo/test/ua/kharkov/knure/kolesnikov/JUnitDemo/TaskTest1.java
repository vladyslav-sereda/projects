package ua.kharkov.knure.kolesnikov.JUnitDemo;


import org.junit.*;

/**
 * �������� �����, ������� ��������� �������� ����������������.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class TaskTest1 {

	/**
	 * ����, ������� ����� ������������ � �������� ������� (������ ������ Task).
	 */
	private Task task;

	/**
	 * ����� �������� ���������� ��������.<br/>
	 * �������� �������� - �������� ���������. ������ ����� ����� ������ ����
	 * ��� ����� �������� ���� �������� �������.<br/>
	 * ������� ����� ������ � ������ ������� ������������� ��� ������������.
	 */
	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("setUpGlobal");
	}

	/**
	 * ����� ����������� ���������� ��������. <br/>
	 * ������� ����� ������ � ������ ������� ������������� ��� ������������.
	 */
	@AfterClass
	public static void tearDownGlobal() {
		System.out.println("tearDownGlobal");
	}

	/**
	 * ����� �������� �������� ��������.<br/>
	 * ����� ������ �� ������ ���� ����� ������ ������� ������� ���������
	 * ������.
	 */
	@Before
	public void setUp() {
		System.out.println("setUp");

		// ����� ������� ������� ��������� ������
		// ����� ������ ����� ������ task
		task = new Task();
	}

	/**
	 * ����� ����������� ��������.<br/>
	 * ������� ����� ������ � ������ ������� ������������� ��� ������������.
	 */
	@After
	public void tearDown() {
		System.out.println("tearDown");
	}

	/**
	 * �������� �����.
	 */
	@Test
	public void testAdd() {
		System.out.println("\ttestAdd");
		int actual = task.add(2, 3);
		Assert.assertEquals(5, actual);
	}

	/**
	 * �������� �����. ��������� ������ ������ add �� ��������� ���������.
	 */
	@Test
	public void testAdd2() {
		System.out.println("\ttestAdd2");
		int actual = task.add(Integer.MAX_VALUE, 1);
		Assert.assertEquals(Integer.MIN_VALUE, actual);
	}

	@Test
	public void testDiv() {
		System.out.println("\ttestDiv");
		int actual = task.div(6, 3);
		Assert.assertEquals(2, actual);
	}

	@Test
	public void testDiv2() {
		System.out.println("\ttestDiv2");
		int actual = task.div(7, 3);
		Assert.assertEquals(2, actual);
	}

	/**
	 * ���������� ����.<br/>
	 * ��� ������ ������ div � ������� ������ 0 ������ ����������� ������
	 * ���������� {@link ArithmeticException}.
	 */
	@Test(expected = ArithmeticException.class)
	public void testDiv3() {
		System.out.println("\ttestDiv3");
		task.div(7, 0);
	}

	/**
	 * �������� ����� main. ����� ����� �����, ���� � ������, ������� ��
	 * ���������� ���� ����� main � ����� ������� �������� ���� � 100%. �������
	 * ����� ������ �������� ����� main, ����� �� ���������.
	 */
	@Test
	public void testMain() {
		System.out.println("\tmain");
		Task.main(new String[] {});
	}

}