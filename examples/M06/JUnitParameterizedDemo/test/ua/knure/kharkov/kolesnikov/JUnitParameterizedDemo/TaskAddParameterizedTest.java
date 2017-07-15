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
 * Тестовый класс с параметрами для тестирования Task#add
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Parameterized.class)
public class TaskAddParameterizedTest {

	/**
	 * Входное значение x метода Task#add
	 */
	private int passValueX;

	/**
	 * Входное значение y метода Task#add
	 */
	private int passValueY;

	/**
	 * Ожидаемое значение, которое должен вернуть метод Task#add
	 */
	private int expectedValue;

	/**
	 * Поле, которое будет использовано в тестовых методах (объект класса Task).
	 */
	private Task task;

	/**
	 * Наличие данного конструктора обязательно.<br/>
	 * JUnit создает экземпляр класса для каждого набора параметров, передавая
	 * их в конструктор.
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
	 * Метод, который поддерживает механизм запуска параметризованных junit
	 * тестов.<br/>
	 * Наличие метода обязательно. Обязан быть аннотирован @Parameters<br/>
	 * Название метода - любое.
	 * 
	 * @return коллекцию (Collection), каждый элемент которой содержит массив
	 *         значений параметров, которые, в свою очередь, будут переданы в
	 *         конструктор.
	 */
	@Parameters
	public static Collection<?> getParameters() {
		Collection<Integer[]> data = new ArrayList<Integer[]>();
		data.add(new Integer[] { 2, 3, 5 });
		data.add(new Integer[] { Integer.MAX_VALUE, 1, Integer.MIN_VALUE });
		return data;
	}
	
	/**
	 * Метод поднятия глобальной фикстуры.<br/>
	 * Тестовая фикстура - тестовое окружение. Данный метод будет вызван один
	 * раз перед запуском всех тестовых методов.<br/>
	 */
	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * Метод поднятия тестовой фикстуры. Будет вызван по одному разу перед
	 * каждым вызовом каждого тестового метода.
	 */
	@Before
	public void setUp() {
		System.out.print("setUp");

		// перед вызовом каждого тестового метода
		// будет создан новый объект task
		task = new Task();
		System.out.println("\t-->\t" + task);
	}

	/**
	 * Тестовый метод.
	 */
	@Test
	public void testAdd() {
		System.out.println("\ttestAdd\t-->\t[" + passValueX + ", " + passValueY + ", " + expectedValue + "]");
		int actual = task.add(passValueX, passValueY);
		Assert.assertEquals(expectedValue, actual);
	}
}
