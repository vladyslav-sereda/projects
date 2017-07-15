package ua.kharkov.knure.kolesnikov.JUnitDemo;


import org.junit.*;

/**
 * Тестовый класс, который тестирует исходную функциональность.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class TaskTest1 {

	/**
	 * Поле, которое будет использовано в тестовых методах (объект класса Task).
	 */
	private Task task;

	/**
	 * Метод поднятия глобальной фикстуры.<br/>
	 * Тестовая фикстура - тестовое окружение. Данный метод будет вызван один
	 * раз перед запуском всех тестовых методов.<br/>
	 * Наличие этого метода в данном примере исключительно для демонстрации.
	 */
	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("setUpGlobal");
	}

	/**
	 * Метод уничтожения глобальной фикстуры. <br/>
	 * Наличие этого метода в данном примере исключительно для демонстрации.
	 */
	@AfterClass
	public static void tearDownGlobal() {
		System.out.println("tearDownGlobal");
	}

	/**
	 * Метод поднятия тестовой фикстуры.<br/>
	 * Будет вызван по одному разу перед каждым вызовом каждого тестового
	 * метода.
	 */
	@Before
	public void setUp() {
		System.out.println("setUp");

		// перед вызовом каждого тестового метода
		// будет создан новый объект task
		task = new Task();
	}

	/**
	 * Метод уничтожения фикстуры.<br/>
	 * Наличие этого метода в данном примере исключительно для демонстрации.
	 */
	@After
	public void tearDown() {
		System.out.println("tearDown");
	}

	/**
	 * Тестовый метод.
	 */
	@Test
	public void testAdd() {
		System.out.println("\ttestAdd");
		int actual = task.add(2, 3);
		Assert.assertEquals(5, actual);
	}

	/**
	 * Тестовый метод. Проверяет работу метода add на граничных значениях.
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
	 * Негативный тест.<br/>
	 * При вызове метода div с делимым равным 0 должен происходить выброс
	 * исключения {@link ArithmeticException}.
	 */
	@Test(expected = ArithmeticException.class)
	public void testDiv3() {
		System.out.println("\ttestDiv3");
		task.div(7, 0);
	}

	/**
	 * Вызывает метод main. Такой вызов нужен, если в классе, который вы
	 * тестируете есть метод main и нужно достичь покрытия кода в 100%. Поэтому
	 * нужно просто вызывать метод main, чтобы он отработал.
	 */
	@Test
	public void testMain() {
		System.out.println("\tmain");
		Task.main(new String[] {});
	}

}